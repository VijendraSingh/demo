package com.sample.jsonvalidator;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.sample.exception.CustomerExceptionClass;

/**
 * This class is used to validate json data.
 * @param <T>
 */
@Component
public class JsonValidator<T> {

	@SuppressWarnings("unchecked")
	public final T validateJson(final Map<String, Object> jsonMap, final T temmplateClass)
			throws CustomerExceptionClass {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//convert JSON string to Map
			String jsonString = objectMapper.writeValueAsString(jsonMap);
			return objectMapper.readValue(jsonString, (Class<T>) temmplateClass);
		} catch (JsonMappingException e) {
			throw new CustomerExceptionClass("Invalid Json for input filed : "
					+ e.getPath().get(e.getPath().size() - 1).getFieldName());
		} catch (IOException e) {
			throw new CustomerExceptionClass(e.getMessage(), e);
		}
	}
}

