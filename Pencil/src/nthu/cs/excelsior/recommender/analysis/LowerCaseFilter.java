package nthu.cs.excelsior.recommender.analysis;

/**
 * Normalizes token text to lower case.
 */
public final class LowerCaseFilter extends TokenFilter {

	public LowerCaseFilter(TokenStream input) {
		super(input);
	}

	@Override
	public final boolean incrementToken() throws Exception {
		if(input.incrementToken()) {
			char[] buffer = token.getTermBuffer();
			int length = token.getTermLength();
			for(int i = 0; i < length; i++)
				buffer[i] = Character.toLowerCase(buffer[i]);
			return true;
		} else
			return false;
	}
}
