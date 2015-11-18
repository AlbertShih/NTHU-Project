package nthu.cs.excelsior.recommender.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nthu.cs.excelsior.recommender.index.*;

/**
 * Specifies a doc in an index as the reference vector.
 */
public class MoreLikeThisQuery implements Query {
	protected final int refNum;

	/**
	 * 
	 * @param refNum
	 *            the number of a doc (in the index to be read by the
	 *            <code>reader</code> argument passed to
	 *            {@link #createScorer(IndexReader)}) whose terms will be used
	 *            as a reference vector
	 */
	public MoreLikeThisQuery(int refNum) {
		this.refNum = refNum;
	}

	@Override
	public Scorer createScorer(final IndexReader reader) 
			throws IndexAccessException {
		// return an instance of anonymous subclass
		return new Scorer(reader) { 
			private List<ScoreDoc> scoreDocs; 
			
			@Override
			public List<ScoreDoc> score() throws IndexAccessException {
				if(scoreDocs != null)
					return scoreDocs;
				
				Map<Integer, ScoreDoc> scores = 
						new HashMap<Integer, ScoreDoc>();
				// calculate the doc scores
				int total = reader.numDocs();
				DocInfo refDi = reader.docInfo(refNum);
				for(TermInfo ti : refDi.termInfos()) {
					double refTf = ti.termFreq(refNum);
					for(int docNum : ti.docNums()) {
						if(docNum != refNum) {
							DocInfo di = reader.docInfo(docNum);
							double tf = ti.termFreq(docNum);
							/*
							 * The magnitude of each vector in each dimension
							 * equals (tf * idf / norm). 
							 * 
							 * Note since both total and ti.docFreq are of type 
							 * int, the result of division will be an int and 
							 * the fraction will be truncated. This is not what 
							 * we want. The solution is to cast total to double 
							 * first such that the division operator will 
							 * automatically convert ti.docFreq() to double. 
							 * The result of the division will be a double then.
							 * 
							 * Recall: Java operators automatically widens the 
							 * narrower operands based on the widest one.
							 */
							double idf = Math.log((double) total / ti.docFreq());
							double termScore = 
									(refTf * idf / refDi.norm()) 
									* (tf * idf / di.norm());
							ScoreDoc scoreDoc = scores.get(docNum);
							if(scoreDoc == null) {
								scoreDoc = new ScoreDoc(docNum);
								scores.put(docNum, scoreDoc);
							}
							scoreDoc.addScore(termScore);
						}
					}
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
