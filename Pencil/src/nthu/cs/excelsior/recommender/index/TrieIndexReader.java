package nthu.cs.excelsior.recommender.index;

import nthu.cs.excelsior.recommender.store.*;

/**
 * Reads from a trie.
 * 
 */
public class TrieIndexReader extends IndexReader {
	protected TrieIndexFile index;
	
	public TrieIndexReader(Directory dir) throws IndexAccessException {
		super(dir);
		try {
			index = (TrieIndexFile) dir.read(TrieIndexWriter.INDEX_FILE_NAME);
		} catch(Exception e) {
			throw new IndexAccessException(e);
		}
		if(index == null)
			throw new IndexAccessException();
	}
	
	@Override
	public void close() throws IndexAccessException {
		index = null; // allows garbage collection
		dir = null;
	}

	@Override
	public int numDocs() throws IllegalStateException {
		if(index == null)
			throw new IllegalStateException();
		
		return index.docs.size();
	}

	@Override
	public DocInfo docInfo(int docNum) throws IllegalStateException {
		if(index == null)
			throw new IllegalStateException();
		
		return index.docs.get(docNum);
	}

	@Override
	public TermInfo termInfo(char[] termBuf, int len) 
			throws IllegalStateException {
		if(index == null)
			throw new IllegalStateException();
		
		return index.terms.get(termBuf, len);
	}

}
