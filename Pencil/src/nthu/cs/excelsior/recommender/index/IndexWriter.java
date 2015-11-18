package nthu.cs.excelsior.recommender.index;

import nthu.cs.excelsior.recommender.analysis.*;
import nthu.cs.excelsior.recommender.document.*;
import nthu.cs.excelsior.recommender.store.*;
/**
 * Creates and maintains an index in a {@link Directory}. A {@link Document} can
 * be added to the index by {@link #addDocument(Document)}. When adding a
 * document, the content of the document is first analyzed by an
 * {@link Analyzer}. Then its term statistics are kept inside the
 * {@link TermInfo}s and {@link DocInfo}s, which can be retrieved later using
 * {@link IndexReader}.
 * 
 * <p>
 * Note that for performance, the changes of index caused by
 * {@link #addDocument(Document)} may not be reflected to directory immediately.
 * Users must call {@link #commit()} after a series of adds. Any further add
 * after the commitment is not allowed.
 * </p>
 */
public abstract class IndexWriter {
	protected Directory dir;
	protected Analyzer analyzer;

	public IndexWriter(Directory dir, Analyzer analyzer) 
			throws IndexAccessException {
		this.dir = dir;
		this.analyzer = analyzer;
	}

	/**
	 * Adds a document to this index.
	 * 
	 * @param doc
	 * @return a unique document number in this index
	 * @throws IllegalStateException
	 *             if this writer has been {@link #commit() committed}
	 */
	public abstract int addDocument(Document doc)
			throws IndexAccessException, IllegalStateException;

	/**
	 * Commits all pending changes to the directory such that a new reader will 
	 * see the changes.
	 */
	public abstract void commit() throws IndexAccessException;
	
	/**
	 * {@link #commit Commits} and release associated resources.
	 * 
	 * @throws IndexAccessException
	 */
	public abstract void close() throws IndexAccessException;

}
