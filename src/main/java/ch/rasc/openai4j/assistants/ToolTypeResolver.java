/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.assistants;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class ToolTypeResolver extends TypeIdResolverBase {

	private JavaType superType;

	@Override
	public void init(JavaType baseType) {
		this.superType = baseType;
	}

	@Override
	public Id getMechanism() {
		return Id.NAME;
	}

	@Override
	public String idFromValue(Object obj) {
		return idFromValueAndType(obj, obj.getClass());
	}

	@Override
	public String idFromValueAndType(Object obj, Class<?> subType) {
		String typeId = null;
		switch (subType.getSimpleName()) {
		case "CodeInterpreterTool":
			typeId = "code_interpreter";
			break;
		case "RetrievalTool":
			typeId = "retrieval";
			break;
		case "FunctionTool":
			typeId = "function";
			break;
		}
		return typeId;
	}

	@Override
	public JavaType typeFromId(DatabindContext context, String id) {
		Class<?> subType = null;
		switch (id) {
		case "code_interpreter":
			subType = CodeInterpreterTool.class;
			break;
		case "retrieval":
			subType = RetrievalTool.class;
			break;
		case "function":
			subType = FunctionTool.class;
			break;
		}
		return context.constructSpecializedType(this.superType, subType);
	}

}
