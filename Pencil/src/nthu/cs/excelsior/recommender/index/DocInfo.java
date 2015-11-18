package nthu.cs.excelsior.recommender.index;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nthu.cs.excelsior.recommender.document.*;

/**
 * Stores the statistics of a document.
 */
public class DocInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Document doc;
	protected Map<String, FieldInfo> fieldInfos;
	protected Set<TermInfo> termInfos;
	protected double normSqr;
	
	public DocInfo(Document doc) {
		this.doc = doc;
		fieldInfos = new HashMap<String, FieldInfo>();
		termInfos = new HashSet<TermInfo>();
		normSqr = 0.0;
	}
	
	public void populateFieldInfo(String name, FieldInfo fi) {
		fieldInfos.put(name, fi);
	}
	
	public void populateTermInfo(TermInfo ti) {
		termInfos.add(ti);
	}
	
	public void populateNorm(int docNum) {
		for(TermInfo ti : termInfos) {
			double tf = ti.termFreq(docNum); 
			normSqr += tf * tf;
		}
	}
	
	public Document document() {
		return doc;
	}
	
	public FieldInfo fieldInfo(String name) {
		return fieldInfos.get(name);
	}

	public Set<TermInfo> termInfos() {
		return termInfos;
	}
	
	public double norm() {
		return Math.sqrt(normSqr);
	}
	
}
