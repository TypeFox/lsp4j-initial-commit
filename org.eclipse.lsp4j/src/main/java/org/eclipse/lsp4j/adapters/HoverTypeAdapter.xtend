/******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 ******************************************************************************/
package org.eclipse.lsp4j.adapters

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.util.List
import org.eclipse.lsp4j.Hover
import org.eclipse.lsp4j.MarkedString
import org.eclipse.lsp4j.MarkupContent
import org.eclipse.lsp4j.generator.TypeAdapterImpl
import org.eclipse.lsp4j.jsonrpc.messages.Either
import java.util.ArrayList

/**
 * A type adapter for the Hover protocol type.
 */
@TypeAdapterImpl(Hover)
class HoverTypeAdapter {
	
	static val LIST_STRING_MARKEDSTRING = new TypeToken<List<Either<String, MarkedString>>>() {}
	static val STRING_MARKEDSTRING = new TypeToken<Either<String, MarkedString>>() {}
	
	protected def readContents(JsonReader in) throws IOException {
		val nextToken = in.peek()
		if (nextToken == JsonToken.STRING) {
			val List<Either<String, MarkedString>> value = new ArrayList<Either<String, MarkedString>>()
			value.add(Either.forLeft(in.nextString))
			return Either.forLeft(value)
		} else if (nextToken == JsonToken.BEGIN_ARRAY) {
			val value = gson.fromJson(in, LIST_STRING_MARKEDSTRING.type)
			return Either.forLeft(value)
		} else {
			val object = JsonParser.parseReader(in) as JsonObject
			if (object.has("language")) {
				val List<Either<String, MarkedString>> value = new ArrayList<Either<String, MarkedString>>()
				value.add(Either.forRight(gson.fromJson(object, MarkedString)))
				return Either.forLeft(value)
			} else {
				return Either.forRight(gson.fromJson(object, MarkupContent))
			}
		}
	}
	
	protected def writeContents(JsonWriter out, Either<List<Either<String, MarkedString>>, MarkupContent> contents) throws IOException {
		if (contents.isLeft) {
			val list = contents.getLeft
			if (list.size == 1) {
				gson.toJson(list.get(0), STRING_MARKEDSTRING.type, out)
			} else {
				gson.toJson(list, LIST_STRING_MARKEDSTRING.type, out)
			}
		} else {
			gson.toJson(contents.getRight, MarkupContent, out)
		}
	}
	
}