package nthu.cs.excelsior.recommender.index;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nthu.cs.excelsior.recommender.document.*;

/**
 * Stores the statistics of a term.
 */
public class TermInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Stores the statistics of a term inside a {@link Field} of a
	 * {@link Document}.
	 */
	protected class DocFieldInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		
		int termFreq;
	}

	public static final double BOOST_MULTIPLIER = 1e4;

	/**
	 * A two-layer map. The first-layer keys are doc numbers, and the
	 * second-layer keys are the boost values. Note the boost values are
	 * transformed into integers (by multiplying {@link #BOOST_MULTIPLIER} first
	 * and then truncate the fraction) to allow quick equality check.
	 */
	protected Map<Integer, Map<Integer, DocFieldInfo>> dfInfos;

	public TermInfo() {
		dfInfos = new HashMap<Integer, Map<Integer, DocFieldInfo>>();
	}

	public void populateTerm(int docNum, double boost) {
		Map<Integer, DocFieldInfo> dfs = dfInfos.get(docNum);
		if(dfs == null) {
			dfs = new HashMap<Integer, DocFieldInfo>();
			dfInfos.put(docNum, dfs);
		}
		int iBoost = (int) (boost * BOOST_MULTIPLIER);
		DocFieldInfo df = dfs.get(iBoost);
		if(df == null) {
			df = new DocFieldInfo();
			dfs.put(iBoost, df);
		}
		df.termFreq++;
	}
	
	/**
	 * Get the numbers of the docs containing this term.
	 * 
	 * @return
	 */
	public Set<Integer> docNums() {
		return dfInfos.keySet();
	}

	/**
	 * Gets the term frequency adjusted by field boosts.
	 * 
	 * @param docNum
	 * @return
	 */
	public double termFreq(int docNum) {
		Map<Integer, DocFieldInfo> dfs = dfInfos.get(docNum);
		if(dfs == null)
			return 0.0;

		double ret = 0.0;
		for(Map.Entry<Integer, DocFieldInfo> dfi : dfs.entrySet()) {
			ret += dfi.getKey() * dfi.getValue().termFreq / BOOST_MULTIPLIER;
		}
		return ret;
	}

	/**
	 * Gets the doc frequency of this term.
	 * 
	 * @return
	 */
	public int docFreq() {
		return dfInfos.size();
	}

}
