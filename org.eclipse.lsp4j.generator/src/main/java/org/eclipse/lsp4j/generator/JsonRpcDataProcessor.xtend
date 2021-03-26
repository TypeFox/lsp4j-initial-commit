/******************************************************************************
 * Copyright (c) 2016 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 ******************************************************************************/
package org.eclipse.lsp4j.generator

import com.google.common.base.MoreObjects
import com.google.gson.annotations.JsonAdapter
import org.eclipse.lsp4j.jsonrpc.validation.NonNull
import org.eclipse.xtend.lib.annotations.AccessorsProcessor
import org.eclipse.xtend.lib.annotations.EqualsHashCodeProcessor
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type
import org.eclipse.xtend.lib.macro.declaration.Visibility

class JsonRpcDataProcessor extends AbstractClassProcessor {

	override doTransform(MutableClassDeclaration annotatedClass, TransformationContext context) {
		generateImpl(annotatedClass, context)
	}

	protected def generateImpl(MutableClassDeclaration impl, extension TransformationContext context) {
		impl.removeAnnotation(impl.annotations.findFirst [
			annotationTypeDeclaration == JsonRpcData.findTypeGlobally
		])
		impl.generateImplMembers(new JsonRpcDataTransformationContext(context))

		generateToString(impl, context)

		val shouldIncludeSuper = impl.extendedClass.type != Object.newTypeReference.type
		val equalsHashCodeUtil = new EqualsHashCodeProcessor.Util(context)
		val fields = impl.declaredFields.filter[!static]
		equalsHashCodeUtil.addEquals(impl, fields, shouldIncludeSuper)
		equalsHashCodeUtil.addHashCode(impl, fields, shouldIncludeSuper)
		impl.getDeclaredMethods.forEach [ method | 
			val purified = method.findAnnotation(Pure.findTypeGlobally)
			if (purified !== null) {
				method.removeAnnotation(purified)
			}
		]
		return impl
	}

	protected def void generateImplMembers(MutableClassDeclaration impl,
		extension JsonRpcDataTransformationContext context) {
		impl.declaredFields.filter [
			!static
		].forEach [ field |
			val accessorsUtil = new AccessorsProcessor.Util(context)
			val deprecated = field.findAnnotation(Deprecated.findTypeGlobally)
			accessorsUtil.addGetter(field, Visibility.PUBLIC)
			val hasNonNull = field.findAnnotation(NonNull.newTypeReference.type) !== null
			val hasJsonAdapter = field.findAnnotation(JsonAdapter.newTypeReference.type) !== null
			impl.findDeclaredMethod(accessorsUtil.getGetterName(field)) => [
				docComment = field.docComment
				if (hasNonNull) {
					addAnnotation(newAnnotationReference(NonNull))
				}
				if (deprecated !== null)
					addAnnotation(newAnnotationReference(Deprecated))
			]

			if (!field.type.inferred) {
				accessorsUtil.addSetter(field, Visibility.PUBLIC)
				val setterName = accessorsUtil.getSetterName(field)
				impl.findDeclaredMethod(setterName, field.type) => [
					docComment = field.docComment
					if (hasNonNull) {
						val parameter = parameters.head
						parameter.addAnnotation(newAnnotationReference(NonNull))
						body = '''
							this.«field.simpleName» = «getPreconditionsUtil(impl, context)».checkNotNull(«parameter.simpleName», "«field.simpleName»");
						'''
					}
					if (deprecated !== null)
						addAnnotation(newAnnotationReference(Deprecated))
				]
				val childTypes = field.type.childTypes
				if (!childTypes.empty) {
					val jsonTypes = childTypes.map[type.jsonType].toList
					if (jsonTypes.size !== jsonTypes.toSet.size) {
						// If there is a JsonAdapter on the field, the warning is expected to be unneeded because
						// the adapter will handle the resolution of the either
						if (!hasJsonAdapter) {
							field.addWarning('''The json types of an Either must be distinct.''')
						}
					} else {
						for (childType : childTypes) {
							field.addEitherSetter(setterName, childType, context)
						}
					}
				}
			}
		]
	}

	protected def void addEitherSetter(
		MutableFieldDeclaration field,
		String setterName,
		EitherTypeArgument argument,
		extension JsonRpcDataTransformationContext context
	) {
		field.declaringType.addMethod(setterName) [ method |
			method.primarySourceElement = field.primarySourceElement
			method.addParameter(field.simpleName, argument.type)
			method.static = field.static
			method.visibility = Visibility.PUBLIC
			method.returnType = primitiveVoid
			method.body = [ctx|compileEitherSetterBody(field, argument, field.simpleName, ctx, context)]
		]
	}

	protected def CharSequence compileEitherSetterBody(
		MutableFieldDeclaration field,
		EitherTypeArgument argument,
		String variableName,
		extension CompilationContext compilationContext,
		extension JsonRpcDataTransformationContext context
	) {
		val hasNonNull = field.findAnnotation(NonNull.newTypeReference.type) !== null
		val newVariableName = '_' + variableName
		val CharSequence compileNewEither = '''«eitherType.toJavaCode».for«IF argument.right»Right«ELSE»Left«ENDIF»(«variableName»)'''
		'''
			if («variableName» == null) {
			  «IF hasNonNull»
			  	«getPreconditionsUtil(field.declaringType, context)».checkNotNull(«variableName», "«field.simpleName»");
			  «ENDIF»
			  this.«field.simpleName» = null;
			  return;
			}
			«IF argument.parent !== null»
				final «argument.parent.type.toJavaCode» «newVariableName» = «compileNewEither»;
				«compileEitherSetterBody(field, argument.parent, newVariableName, compilationContext, context)»
			«ELSE»
				this.«field.simpleName» = «compileNewEither»;
			«ENDIF»
		'''
	}

	protected def generateToString(MutableClassDeclaration impl, extension TransformationContext context) {
		val toStringFields = newArrayList
		var ClassDeclaration c = impl
		do {
			toStringFields += c.declaredFields
			c = c.extendedClass?.type as ClassDeclaration
		} while (c !== null && c != object)
		impl.addMethod("toString") [
			returnType = string
			addAnnotation(newAnnotationReference(Override))
			val accessorsUtil = new AccessorsProcessor.Util(context)
			body = '''
				«MoreObjects.ToStringHelper» b = «MoreObjects».toStringHelper(this);
				«FOR field : toStringFields»
					b.add("«field.simpleName»", «IF field.declaringType == impl»this.«field.simpleName»«ELSE»«
						accessorsUtil.getGetterName(field)»()«ENDIF»);
				«ENDFOR»
				return b.toString();
			'''
		]
	}
	
	private def getPreconditionsUtil(Type type, extension TransformationContext context) {
		if (type.qualifiedName.startsWith('org.eclipse.lsp4j.debug'))
			newTypeReference('org.eclipse.lsp4j.debug.util.Preconditions')
		else
			newTypeReference('org.eclipse.lsp4j.util.Preconditions')
	}

}
