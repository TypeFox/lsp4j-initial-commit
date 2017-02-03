/*******************************************************************************
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.lsp4j.jsonrpc.messages;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;


/**
 * 
 * An either type maps union types in protocol specifications.
 *
 * @param <L>
 * @param <R>
 */
public class Either<L, R> {
	
	public static <L,R> Either<L,R> forLeft(@NonNull L left) {
		return new Either<L,R>(left, null);
	}
	
	public static <L,R> Either<L,R> forRight(@NonNull R right) {
		return new Either<L,R>(null, right);
	}

	private final L left;
	private final R right;
	
	protected Either(L left, R right) {
		super();
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}
	
	public R getRight() {
		return right;
	}
	
	public boolean isLeft() {
		return left != null;
	}
	
	public boolean isRight() {
		return right != null;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("Either [").append(System.lineSeparator());
		builder.append("  left = ").append(left).append(System.lineSeparator());
		builder.append("  right = ").append(right).append(System.lineSeparator());
		return builder.append("]").toString();
	}
	
	/**
	 * Return all disjoint types.
	 */
	public static Collection<Type> getDisjoinTypes(Type type) {
		return collectDisjoinTypes(type, new ArrayList<>());
	}
	
	protected static Collection<Type> collectDisjoinTypes(Type type, Collection<Type> types) {
		if (isEither(type)) {
			final ParameterizedType parameterizedType = (ParameterizedType) type;
			for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
				collectDisjoinTypes(typeArgument, types);
			}
		} else {
			types.add(type);
		}
		return types;
	}
	
	/**
	 * Test whether the given type is Either.
	 */
	public static boolean isEither(Type type) {
		if (type instanceof ParameterizedType) {
			return isEither((ParameterizedType) type);
		}
		if (type instanceof Class) {
			return isEither((Class<?>) type);
		}
		return false;
	}
	
	/**
	 * Test whether the given type is Either.
	 */
	public static boolean isEither(ParameterizedType type) {
		return isEither(type.getRawType());
	}
	
	/**
	 * Test whether the given class is Either.
	 */
	public static boolean isEither(Class<?> cls) {
		return Either.class.isAssignableFrom(cls);
	}
	
}
