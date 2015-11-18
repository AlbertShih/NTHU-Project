package nthu.cs.excelsior.recommender.analysis;

/**
 * Eliminates plurals, e.g.,
 * 
 * <pre>
 * caresses  ->  caress
 * ponies    ->  pony
 * class     ->  class
 * cats      ->  cat
 * </pre>
 * 
 * Note the current implement is simplistic. For example, it does not handle
 * 
 * <pre>
 * ties      ->  tie
 * </pre>
 * 
 * correctly, nor does it stem tokens ending with "ed", "ing", "ize", "ion",
 * "full", "ness", "ant", and "ence", etc. For more rigorous implementation, see
 * the <a
 * href="http://snowball.tartarus.org/algorithms/porter/stemmer.html">Porter
 * stemming algorithm</a>.
 */
public class StemFilter extends TokenFilter {

	public StemFilter(TokenStream input) {
		super(input);
	}

	@Override
	public boolean incrementToken() throws Exception {
		while (input.incrementToken()) {
			char[] term = token.getTermBuffer();
			int len = token.getTermLength();
			if (len > 2 && term[len - 1] == 's') {
				// ignore "s", "as", "is", "us", etc.
				if (term[len - 2] == 'e') {
					if (term[len - 3] == 'i') {
						// end with "ies" -> "y"
						term[len - 3] = 'y';
						token.setTermLength(len - 2);
					} else if (len > 3 && term[len - 3] == 's'
							&& term[len - 4] == 's') {
						// end with "sses" -> "ss"
						token.setTermLength(len - 2);
					} else {
						// end with "es" -> "e"
						token.setTermLength(len - 1);
					}
				} else if (term[len - 2] == 's') {
					// end with "ss", do nothing
				} else {
					// end with "s" -> ""
					token.setTermLength(len - 1);
				}
			}
			if (token.getTermLength() > 0)
				return true;
			// else, the loop continues
		}
		// end of stream
		return false;
	}

}
