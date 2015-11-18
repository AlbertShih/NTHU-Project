package nthu.cs.excelsior.recommender.index;


import nthu.cs.excelsior.recommender.store.*;
/**
 * Provides access to an index and retrieves {@link TermInfo}s and
 * {@link DocInfo}s.
 * 
 * <p>
 * Users must call {@link #close()} after a series of reads.
 * </p>
 * 
 */
public abstract class IndexReader {
	protected Directory dir;

	public IndexReader(Directory dir) throws IndexAccessException {
		this.dir = dir;
	}
	
	/**
	 * Release associated resources.
	 * 
	 * @throws IndexAccessException
	 */
	public abstract void close() throws IndexAccessException;

	/**
	 * Gets the number of documents inside this index.
	 * 
	 * @return
	 */
	public abstract int numDocs();

	/**
	 * Gets the {@link DocInfo} of a document.
	 * 
	 * @param docNum
	 * @return
	 */
	public abstract DocInfo docInfo(int docNum);

	/**
	 * Gets the {@link TermInfo} of a term.
	 * 
	 * @param termBuf
	 * @param len
	 * @return
	 */
	public abstract TermInfo termInfo(char[] termBuf, int len);

}
