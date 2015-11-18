package nthu.cs.excelsior.recommender.document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A document contains one or more fields.
 */
public class Document implements Iterable<Field>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Field> fields;
	
	public Document() {
		fields = new HashMap<String, Field>();
	}
	
	public Document(Field... fields) {
		this.fields = new HashMap<String, Field>();
		for(Field f : fields) {
			if(f != null) add(f);
		}
	}
	
	public void add(Field field) {
		fields.put(field.name(), field);
	}
	
	/**
	 * Gets the string value of the field with the specified name.
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name) {
		Field f = fields.get(name);
		return f == null ? null : f.stringValue();
	}
	
	public Field getField(String name) {
		return fields.get(name);
	}
	
	public Set<Map.Entry<String, Field>> fields() {
		return fields.entrySet();
	}

	@Override
	public Iterator<Field> iterator() {
		return fields.values().iterator();
	}
	
}
