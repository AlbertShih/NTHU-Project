package nthu.cs.excelsior.recommender.index;

import java.io.Serializable;

import nthu.cs.excelsior.recommender.document.*;


/**
 * Stores the statistics of a field in a document.
 */
public class FieldInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Stores a local copy of the field value for fast retrieval.
	 */
	protected String strValue;
	
	public void populateField(Field f) {
		this.strValue = f.stringValue();
	}
	
	public String stringValue() {
		return strValue;
	}

}
