package nthu.cs.excelsior.recommender.search;

import java.util.Comparator;

/**
 * Specifies how to sort docs. 
 */
public abstract class SortComparator implements Comparator<ScoreDoc> {
	
	/**
	 * Compares two documents given their respective {@link ScoreDoc}s.
	 */
	@Override
	public abstract int compare(ScoreDoc d1, ScoreDoc d2);
}
