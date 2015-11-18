package nthu.cs.excelsior.recommender.analysis;

/**
 * Carries a term. Note when the flyweight pattern is used in
 * {@link TokenStream}, the {@link #clear()} must be called before reusing this
 * instance to carry a new term.
 */
public class Token {
	private static int MIN_BUFFER_SIZE = 16;

	private char[] termBuffer;
	private int termLength;

	protected Token() {
		termBuffer = new char[MIN_BUFFER_SIZE];
		termLength = 0;
	}

	/**
	 * Grows the termBuffer to at least size newSize, preserving the existing
	 * content.
	 * 
	 * @param newSize
	 *            minimum size of the new termBuffer
	 * @return newly created termBuffer with length >= newSize
	 */
	public char[] resizeTermBuffer(int newSize) {
		int curSize = termBuffer.length;
		if(curSize >= newSize)
			return termBuffer;
		
		while(curSize < newSize) {
			curSize <<= 1;
		}
		char[] newBuffer = new char[curSize];
		System.arraycopy(termBuffer, 0, newBuffer, 0, termBuffer.length);
		termBuffer = newBuffer;
		return termBuffer;
	}

	/**
	 * Allocates a buffer char[] of at least <code>newSize</code>, without
	 * preserving the existing content.
	 * 
	 * @param newSize
	 *            minimum size of the buffer
	 */
	protected void growTermBuffer(int newSize) {
		int curSize = termBuffer.length;
		if(curSize >= newSize)
			return;
		
		while(curSize < newSize) {
			curSize <<= 1;
		}
		termBuffer = new char[curSize];
	}

	/**
	 * Copies the contents of <code>buffer</code>, starting at
	 * <code>offset</code> for <code>length</code> characters, into the
	 * <code>termBuffer</code> array.
	 * 
	 * @param buffer
	 *            the buffer to copy
	 * @param offset
	 *            the index in the buffer of the first character to copy
	 * @param length
	 *            the number of characters to copy
	 */
	protected void setTermBuffer(char[] buffer, int offset, int length) {
		growTermBuffer(length);
		System.arraycopy(buffer, offset, termBuffer, 0, length);
		termLength = length;
	}

	/**
	 * Returns the internal termBuffer character array which a {@link Tokenizer}
	 * can directly alter. If the array is too small to carry a term, use
	 * {@link #resizeTermBuffer(int)} to increase it. After altering the buffer
	 * be sure to call {@link #setTermLength} to record the number of valid
	 * characters that were placed into the termBuffer.
	 */
	public char[] getTermBuffer() {
		return termBuffer;
	}
	
	public int getTermLength() {
		return termLength;
	}

	/**
	 * Set number of valid characters (length of the term) in the termBuffer
	 * array. Use this to truncate the termBuffer or to synchronize with
	 * external manipulation of the termBuffer.
	 * 
	 * @param length
	 *            the truncated length
	 */
	protected void setTermLength(int length) {
		if(length > termBuffer.length)
			throw new IllegalArgumentException();
		termLength = length;
	}

	/**
	 * Gets the carrying term as a String.
	 * 
	 * @return
	 */
	public String getTerm() {
		return new String(termBuffer, 0, termLength);
	}

	/**
	 * Clears the carrying token. Must be called before this instance is reused
	 * to carry an another term.
	 */
	public void clear() {
		termLength = 0;
	}
}
