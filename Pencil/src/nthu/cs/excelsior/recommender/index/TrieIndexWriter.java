package nthu.cs.excelsior.recommender.index;

import java.util.Map;

import nthu.cs.excelsior.recommender.analysis.*;
import nthu.cs.excelsior.recommender.document.*;
import nthu.cs.excelsior.recommender.store.*;


/**
 * Maintains index as a trie.
 */
public class TrieIndexWriter extends IndexWriter {
	public static final String INDEX_FILE_NAME = "trie-index";
	
	protected TrieIndexFile index;

	public TrieIndexWriter(Directory dir, Analyzer analyzer) throws Exception {
		this(dir, analyzer, false);
	}

	/**
	 * 
	 * @param dir
	 * @param analyzer
	 * @param overwrite
	 *            whether to overwrite an existing index
	 * @throws IndexAccessException
	 */
	public TrieIndexWriter(Directory dir, Analyzer analyzer, boolean overwrite)
			throws IndexAccessException {
		super(dir, analyzer);
		try {
			if(overwrite || 
					(index = (TrieIndexFile) dir.read(INDEX_FILE_NAME)) == null)
				index = new TrieIndexFile();
		} catch(Exception e) {
			throw new IndexAccessException(e);
		}
	}

	@Override
	public int addDocument(Document doc) 
			throws IndexAccessException, IllegalStateException {
		if(index == null)
			throw new IllegalStateException();
		
		int docNum = index.docs.size();
		DocInfo di = new DocInfo(doc);
		index.docs.put(index.docs.size(), di);
		try {
			for(Map.Entry<String, Field> entry : doc.fields()) {
				String name = entry.getKey();
				Field f = entry.getValue();
				if(!f.indexed())
					continue;
				TokenStream stream = analyzer.tokenStream(f.readerValue());
				Token token = stream.getToken(); // flyweight
				while(stream.incrementToken()) {
					char[] buf = token.getTermBuffer();
					int len = token.getTermLength();
					// populate term info
					TermInfo ti = index.terms.get(buf, len);
					if(ti == null) {
						ti = new TermInfo();
						index.terms.add(buf, len, ti);
					}
					ti.populateTerm(docNum, f.boost());
					// populate field info
					FieldInfo fi = new FieldInfo();
					fi.populateField(f);
					// populate doc info
					di.populateFieldInfo(name, fi);
					di.populateTermInfo(ti);
				}
				stream.close();
			}
			di.populateNorm(docNum);
		} catch(Exception e) {
			throw new IndexAccessException(e);
		}
		return docNum;
	}

	@Override
	public void commit() throws IndexAccessException {
		if(index == null)
			return;
		try {
			dir.updateOrCreate(INDEX_FILE_NAME, index);
		} catch(Exception e) {
			throw new IndexAccessException(e);
		}
	}

	@Override
	public void close() throws IndexAccessException {
		commit();
		index = null; // allows garbage collection
		dir = null;
	}

}
