package nthu.cs.excelsior.recommender.search;

import java.util.List;

public class TopDocs {
	protected List<ScoreDoc> scoreDocs;
	protected int totalHits;
	
	public TopDocs(List<ScoreDoc> scoreDocs, int totalHits) {
		this.scoreDocs = scoreDocs;
		this.totalHits = totalHits;
	}
	
	public List<ScoreDoc> scoreDocs() {
		return scoreDocs;
	}
	public int totalHits() {
		return totalHits;
	}
	
}
