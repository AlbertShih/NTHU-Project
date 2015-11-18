package nthu.cs.excelsior.service.json;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonService {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String serialize(Object obj) {
		try {
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, obj);
			return writer.toString();
		} catch (Exception e) {
			throw new JsonServiceException(e);
		}
	}

	public static <T> T deserialize(String str, Class<T> cls) {
		try {
			return mapper.readValue(str, cls);
		} catch (Exception e) {
			throw new JsonServiceException(e);
		}
	}

}
