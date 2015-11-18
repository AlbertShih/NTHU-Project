package nthu.cs.excelsior.recommender.document;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;

/**
 * A field of a document having a name, value, indexed indicator, and boost. The
 * value may be stored locally in the memory or file system, or remotely on a
 * machine. The value of this field will be indexed if the indexed indicator is
 * true. Boost is default to <code>1.0</code>, and can be increased/decreased
 * if some field is more/less important than others during the indexing.
 */
public abstract class Field implements Comparable<Field>, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected boolean indexed;
	protected double boost;

	protected Field(String name) {
		this(name, true, 1.0);
	}

	protected Field(String name, boolean indexed, double boost) {
		this.name = name;
		this.indexed = indexed;
		this.boost = boost;
	}

	/**
	 * Compares to another field based on the alphabetic ordering.
	 */
	@Override
	public int compareTo(Field f) {
		Reader reader1 = readerValue();
		Reader reader2 = f.readerValue();
		int ret = 0;
		int c1 = -1;
		try {
			while((c1 = reader1.read()) != -1) {
				int c2 = reader2.read();
				if(c2 == -1) {
					ret = 1;
					break;
				} else if(c1 < c2) {
					ret = -1;
					break;
				} else if(c1 > c2) {
					ret = 1;
					break;
				}
				// c1 == c2, continue
			}
			ret = reader2.read() == -1 ? 0 : -1;
			reader1.close();
			reader2.close();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	/**
	 * Gets the name of this field.
	 * 
	 * @return the name of this field
	 */
	public String name() {
		return name;
	}

	/**
	 * Gets the boost of this field.
	 * 
	 * @return
	 */
	public double boost() {
		return boost;
	}

	/**
	 * Checks if this field is indexable.
	 */
	public boolean indexed() {
		return indexed;
	}

	/**
	 * Gets the value of this field as a String. This method may be expansive if
	 * the value is large or transmitted from remote machine.
	 * 
	 * @return
	 */
	public abstract String stringValue();

	/**
	 * Gets the value of this field as a {@link Reader}.
	 * 
	 * @return
	 */
	public abstract Reader readerValue();

}
