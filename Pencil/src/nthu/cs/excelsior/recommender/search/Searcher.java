package nthu.cs.excelsior.recommender.search;


/**
 * Searches docs that hit the queries.
 */
public interface Searcher {
	
	/**
	 * Finds the top <code>n</code> hits for a <code>query</code>, sorted by
	 * relevance.
	 * 
	 * @param query
	 * @param n
	 * @return the hits
	 */
	TopDocs search(Query query, int n) throws Exception;
	
	/**
	 * Finds the top <code>n</code> hits for a <code>query</code>, sorted by
	 *  a <code>sort</code> comparator.
	 * 
	 * @param query
	 * @param n
	 * @param sort
	 * @return the hits
	 */
	TopDocs search(Query query, int n, SortComparator sort) throws Exception;
	
}
