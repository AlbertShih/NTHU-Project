package nthu.cs.excelsior.recommender.search;

public class ScoreDoc {
	protected int docNum;
	protected double score;
	
	public ScoreDoc(int docNum) {
		this.docNum = docNum;
		score = 0.0;
	}
	
	public void addScore(double score) {
		this.score += score;
	}
	
	public int docNum() {
		return docNum;
	}
	
	public double score() {
		return score;
	}
}
