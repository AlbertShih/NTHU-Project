package nthu.cs.excelsior.recommender.analysis;

import java.io.Reader;

import nthu.cs.excelsior.recommender.document.Document;


/**
 * Builds {@link TokenStream} from a text {@link Reader reader}. Typical
 * implementations first build a {@link Tokenizer}, which breaks the text into
 * raw {@link Token}s. One or more {@link TokenFilter}s may be built upon the
 * {@link Tokenizer} which filter the {@link Tokenizer}'s output.
 * 
 */
public abstract class Analyzer {
	/**
	 * Creates a {@link TokenStream} which tokenizes a text reader.
	 * 
	 * @param input
	 *            reader of a text, which is typically a field value in a
	 *            {@link Document} or a query string
	 * @return
	 */
	public abstract TokenStream tokenStream(Reader input);
}
