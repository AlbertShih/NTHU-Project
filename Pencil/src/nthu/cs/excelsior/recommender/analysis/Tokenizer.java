package nthu.cs.excelsior.recommender.analysis;

import java.io.IOException;
import java.io.Reader;

/**
 * A {@link TokenStream} that tokenizes a text reader. Works at character level.  
 */
public abstract class Tokenizer extends TokenStream {
	protected Reader input;
	
	protected Tokenizer(Reader input) {
		super();
		this.input = input;
	}
	
	@Override
	public void close() throws IOException {
		input.close();
	}
}

