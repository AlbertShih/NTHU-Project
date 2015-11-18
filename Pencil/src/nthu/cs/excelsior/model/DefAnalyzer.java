package nthu.cs.excelsior.model;

import java.io.Reader;

import nthu.cs.excelsior.recommender.analysis.*;

/**
 * An {@link Analyzer} implementation specialized for the definition objects
 * having the "title" and "description" fields.
 */
public class DefAnalyzer extends Analyzer {

	@Override
	public TokenStream tokenStream(Reader input) {
		Tokenizer tokenizer = new LetterTokenizer(input);
		TokenFilter lowerCaseFilter = new LowerCaseFilter(tokenizer);
		TokenFilter stopFilter = new StopFilter(lowerCaseFilter);
		TokenFilter stemFilter = new StemFilter(stopFilter);
		return stemFilter;
	}

}
