package nthu.cs.excelsior.recommender.document;

import java.io.Reader;
import java.io.StringReader;

/**
 * Stores value as a {@link String} in memory.
 */
public class RamField extends Field {
	private static final long serialVersionUID = 1L;
	
	protected String value;

	public RamField(String name, String value) {
		this(name, true, 1.00, value);
	}
	
	public RamField(String name, boolean indexed, double boost, 
			String value) {
		super(name, indexed, boost);
		this.value = value;
	}
	
	@Override
	public String stringValue() {
		return value;
	}

	@Override
	public Reader readerValue() {
		return new StringReader(value);
	}
}
