package nthu.cs.excelsior.recommender.search;

import nthu.cs.excelsior.recommender.index.*;
/**
 * Provides a {@link Scorer} implementation by modeling a reference vector 
 * from, for example, a doc or arbitrary terms.
 * 
 * <p>
 * Note a query is reusable. That is, it can be used repeatedly by different 
 * {@link Searcher}s.
 * </p>
 */
public interface Query {
	
	/**
	 * Creates a {@link Scorer}.
	 * 
	 * @return
	 */
	Scorer createScorer(IndexReader reader) throws IndexAccessException;
	
}
