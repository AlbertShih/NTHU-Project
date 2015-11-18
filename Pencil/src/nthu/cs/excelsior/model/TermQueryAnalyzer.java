package nthu.cs.excelsior.model;

import java.io.Reader;

import nthu.cs.excelsior.recommender.analysis.*;

/**
 * An analyzer of query strings. Note the current implementation does not
 * support advanced query syntax such as "...", *, ?, or ~, etc.
 */
public class TermQueryAnalyzer extends Analyzer {

	@Override
	public TokenStream tokenStream(Reader input) {
		Tokenizer tokenizer = new LetterTokenizer(input);
		TokenFilter lowerCaseFilter = new LowerCaseFilter(tokenizer);
		TokenFilter stopFilter = new StopFilter(lowerCaseFilter);
		TokenFilter stemFilter = new StemFilter(stopFilter);
		return stemFilter;
	}

}
