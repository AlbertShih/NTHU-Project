package nthu.cs.excelsior.recommender.analysis;

import java.io.Reader;

/**
 * Tokenizes text using white spaces as the boundaries. 
 * <p>Works well for European languages but not for Asian languages where words 
 * are not separated by white spaces.</p>
 */
public class WhiteSpaceTokenizer extends CharTokenizer {
	StringBuilder b;

	public WhiteSpaceTokenizer(Reader input) {
		super(input);
	}

	/**
	 * Collects only characters which do not satisfy
	 * {@link Character#isWhitespace(char)}.
	 */
	@Override
	protected boolean isTokenChar(char c) {
		return !Character.isWhitespace(c);
	}

}
