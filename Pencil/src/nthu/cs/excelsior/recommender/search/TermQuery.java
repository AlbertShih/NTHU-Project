package nthu.cs.excelsior.recommender.search;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nthu.cs.excelsior.model.TermQueryAnalyzer;
import nthu.cs.excelsior.recommender.index.*;
import nthu.cs.excelsior.recommender.analysis.*;

public class TermQuery implements Query {
	private Analyzer analyzer;
	private String query;

	public TermQuery(String queryString) {
		this.query = queryString;
	}

	@Override
	public Scorer createScorer(final IndexReader reader)
			throws IndexAccessException {
		// return an instance of anonymous subclass
		return new Scorer(reader) {
			private List<ScoreDoc> scoreDocs;

			@Override
			public List<ScoreDoc> score() {
				if (scoreDocs != null)
					return scoreDocs;

				Map<Integer, ScoreDoc> scores = new HashMap<Integer, ScoreDoc>();
				// calculate the doc scores
				int total = reader.numDocs();
				analyzer = new TermQueryAnalyzer();
				TokenStream stream = analyzer.tokenStream(new StringReader(
						query));
				Token token = stream.getToken();
				try {
					while (stream.incrementToken()) {
						TermInfo ti = reader.termInfo(token.getTermBuffer(),
								token.getTermLength());
						if (ti == null)
							continue;
						for (int docNum : ti.docNums()) {
							DocInfo di = reader.docInfo(docNum);
							double tf = ti.termFreq(docNum);
							double idf = Math
									.log((double) total / ti.docFreq());
							double termScore = (tf * idf / di.norm());
							/*
							 * The terms in a query string is equally important,
							 * and the length of the query string does not
							 * affect the importance of each term. So, the score
							 * of a document is the sum of the scores of all its
							 * matching terms to the query. The score of a
							 * matching term is computed by taking only the tf,
							 * idf, and norm of the document into account.
							 */
							ScoreDoc scoreDoc = scores.get(docNum);
							if (scoreDoc == null) {
								scoreDoc = new ScoreDoc(docNum);
								scores.put(docNum, scoreDoc);
							}
							scoreDoc.addScore(termScore);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}

				// sort doc based on their scores
				List<ScoreDoc> ret = new ArrayList<ScoreDoc>(scores.values());
				Collections.sort(ret, new Comparator<ScoreDoc>() {
					/**
					 * Sorts scoreDocs based on their scores in an descending
					 * order.
					 */
					@Override
					public int compare(ScoreDoc d1, ScoreDoc d2) {
						return (int) Math.signum(d2.score() - d1.score());
					}
				});
				// caches and preserve ordering of ret for future returns
				scoreDocs = new ArrayList<ScoreDoc>(ret);
				return ret;
			}
		};
	}
}
