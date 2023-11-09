package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class CodeInterpreterOutputTypeResolver extends TypeIdResolverBase {

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
		case "CodeInterpreterLogOutput":
			typeId = "logs";
			break;
		case "CodeInterpreterImageOutput":
			typeId = "image";
			break;
		}
		return typeId;
	}

	@Override
	public JavaType typeFromId(DatabindContext context, String id) {
		Class<?> subType = null;
		switch (id) {
		case "logs":
			subType = CodeInterpreterOutputLog.class;
			break;
		case "image ":
			subType = CodeInterpreterOutputImage.class;
			break;
		}
		return context.constructSpecializedType(this.superType, subType);
	}

}
