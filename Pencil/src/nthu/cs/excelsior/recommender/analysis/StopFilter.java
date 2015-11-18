package nthu.cs.excelsior.recommender.analysis;

import  nthu.cs.excelsior.recommender.util.*;

/**
 * Removes stop words from a token stream. Tokens are expected to be lowercase.
 */
public final class StopFilter extends TokenFilter {
	private final CharArraySet stopSet;

	public StopFilter(TokenStream input, String... stopWords) {
		super(input);
		this.stopSet = makeStopSet(stopWords == null || stopWords.length == 0 ? 
				getDefaultStopWords() : stopWords);
	}

	protected CharArraySet makeStopSet(String... stopWords) {
		CharArraySet set = new CharArraySet(stopWords.length);
		for(String w : stopWords) {
			if(w != null) set.add(w.toCharArray(), w.length());
		}
		return set;
	}
	
	protected String[] getDefaultStopWords() {
		return new String[] { "a", "an", "and", "are", "as", "at", "be", "but",
				"by", "for", "if", "in", "into", "is", "it", "no", "not", "of",
				"on", "or", "s", "such", "that", "the", "their", "then",
				"there", "these", "they", "this", "to", "was", "will", "with" };
	}

	/**
	 * Returns the next input Token whose term is not a stop word.
	 */
	@Override
	public final boolean incrementToken() throws Exception {
		while(input.incrementToken()) {
			if(!stopSet.contains(
					token.getTermBuffer(), token.getTermLength()))
				return true;
			// else, the loop continues
		}
		// end of stream
		return false;
	}
}
