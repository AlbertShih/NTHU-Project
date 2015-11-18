package nthu.cs.excelsior.recommender.analysis;

import java.io.Reader;

/**
 * Tokenizes text using non-letter characters as the boundaries. See 
 * <a href="http://www.fileformat.info/info/unicode/category/index.htm">Unicode Character Categories</a>
 * for more details about what is a letter. 
 * <p>Works well for European languages but not for Asian languages where words 
 * are not separated by white spaces.</p>
 */
public class LetterTokenizer extends CharTokenizer {

	public LetterTokenizer(Reader input) {
		super(input);
	}

	@Override
	protected boolean isTokenChar(char c) {
		return Character.isLetter(c);
	}

}
