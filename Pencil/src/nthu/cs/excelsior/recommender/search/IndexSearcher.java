package nthu.cs.excelsior.recommender.search;

import java.util.Collections;
import java.util.List;


import nthu.cs.excelsior.recommender.index.*;

/**
 * Searches hits from an index by using an {@link IndexReader}.
 */
public class IndexSearcher implements Searcher {
	protected IndexReader reader;

	public IndexSearcher(IndexReader reader) {
		this.reader = reader;
	}

	/**
	 * Release associated resources and closes the underlying reader.
	 * 
	 * @throws IndexAccessException
	 */
	public void close() throws IndexAccessException {
		reader.close();
		reader = null; // allows garbage collection
	}

	@Override
	public TopDocs search(Query query, int n) throws IllegalStateException,
			IndexAccessException {
		if(reader == null)
			throw new IllegalStateException();
		
		List<ScoreDoc> hits = query.createScorer(reader).score();
		int totalHits = hits.size();
		return new TopDocs(hits.subList(0, Math.min(n, totalHits)), totalHits);
	}

	@Override
	public TopDocs search(Query query, int n, SortComparator sort)
			throws IllegalStateException, IndexAccessException {
		if(reader == null)
			throw new IllegalStateException();
		
		List<ScoreDoc> hits = query.createScorer(reader).score();
		Collections.sort(hits, sort);
		int totalHits = hits.size();
		return new TopDocs(hits.subList(0, Math.min(n, totalHits)), totalHits);
	}

}
