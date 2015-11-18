package nthu.cs.excelsior.recommender.analysis;

import nthu.cs.excelsior.recommender.document.*;

/**
 * Iterates the sequence of {@link Token}s, either from {@link Field}s 
 * of a {@link Document} or from a query text. Due to the potentially large 
 * number of tokens, the flyweight pattern should be used to ensure that only 
 * only one internal {@link Token} object is used during the iteration.   
 * 
 * <p>The process of extracting the tokens from a text is achieved by two
 * subclasses: {@link Tokenizer} and {@link TokenFilter}.</p>
 * 
 */
public abstract class TokenStream {
	protected static Token token;
	
	protected TokenStream() {
		token = new Token();
	}
	
	/**
	 * Advances the stream to the next token. Implementations must call 
	 * {@link Token#clear() clear()} of the flyweight token object before 
	 * reusing it.
	 * 
	 * @return <code>false</code> for end of stream; <code>true</code> otherwise 
	 */
	public abstract boolean incrementToken() throws Exception;
	
	/**
	 * Releases resources associated with this stream.
	 */
	public abstract void close() throws Exception;
	
	/**
	 * Obtains the token reference.
	 * 
	 * @return
	 */
	public Token getToken() {
		return token;
	}
}