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
