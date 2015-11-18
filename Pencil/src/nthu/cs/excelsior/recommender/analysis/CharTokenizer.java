package nthu.cs.excelsior.recommender.analysis;

import java.io.IOException;
import java.io.Reader;

/**
 * Generates tokens as adjacent sequences of characters which satisfy
 * {@link #isTokenChar}.
 */
public abstract class CharTokenizer extends Tokenizer {
	private static final int MAX_WORD_LEN = 255;
	private static final int IO_BUFFER_SIZE = 4096;
	
	protected final char[] ioBuffer;
	
	// index of character in the ioBuffer that has been examined for token inclusion
	protected int bufferIndex = 0;
	// length of characters read into the ioBuffer
	protected int dataLen = 0;

	public CharTokenizer(Reader input) {
		super(input);
		ioBuffer = new char[IO_BUFFER_SIZE];
	}

	/**
	 * Returns true iff a character should be included in a token. Characters
	 * for which this is false are used to define token boundaries and are not
	 * included in tokens.
	 * 
	 * @param c
	 */
	protected abstract boolean isTokenChar(char c);

	@Override
	public boolean incrementToken() throws IOException {
		token.clear();
		
		// manipulate token's termBuffer externally, must set termLength later
		char[] buffer = token.getTermBuffer();
		int length = 0;
		while(true) {
			if(bufferIndex >= dataLen) { // all read characters has been examined
				dataLen = input.read(ioBuffer);
				if(dataLen == -1) { // no more character
					dataLen = 0; // reset
					if(length > 0)
						break;
					else
						return false;
				}
				bufferIndex = 0;// reset
			}

			final char c = ioBuffer[bufferIndex++];

			if(isTokenChar(c)) { // examine whether c should be included in token
				if(length == buffer.length)
					buffer = token.resizeTermBuffer(1 + length);
				buffer[length++] = c;
				if(length == MAX_WORD_LEN) // buffer overflow
					break;

			} else if(length > 0)
				break;
			// skip successive non-token characters
		}

		token.setTermLength(length);
		return true;
	}

}
