package nthu.cs.excelsior.recommender.search;

import nthu.cs.excelsior.recommender.index.*;
import nthu.cs.excelsior.recommender.document.*;
/**
 * Sorts docs lexicographically by a single field. The field must be
 * {@link Field#indexed() indexed}.
 */
public class SingleFieldSortComparator extends SortComparator {
	protected IndexReader reader;
	protected String fieldName;
	protected boolean asc;

	/**
	 * 
	 * @param reader
	 * @param fieldName
	 * @param asc
	 *            <code>true</code> for ascending comparison, <code>false</code>
	 *            otherwise
	 */
	public SingleFieldSortComparator(IndexReader reader, String fieldName,
			boolean asc) {
		this.reader = reader;
		this.fieldName = fieldName;
		this.asc = asc;
	}

	@Override
	public int compare(ScoreDoc d1, ScoreDoc d2) {
		int ret = 0;
		FieldInfo fi1 = reader.docInfo(d1.docNum()).fieldInfo(fieldName);
		FieldInfo fi2 = reader.docInfo(d2.docNum()).fieldInfo(fieldName);
		if(fi1 != null && fi2 != null) {
			ret =  fi1.stringValue().compareTo(fi2.stringValue());
		} else if(fi1 == null && fi2 == null) {
			ret = 0;
		} else if(fi1 != null) {
			ret = -1;
		} else { // fi2 != null
			ret = 1;
		}
		return asc ? ret : -ret;
	}

}
