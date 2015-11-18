package nthu.cs.excelsior.recommender.index;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nthu.cs.excelsior.recommender.util.*;

/**
 * Index file maintained by {@link TrieIndexWriter} and read by
 * {@link TrieIndexReader}.
 */
public class TrieIndexFile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Trie<TermInfo> terms;
	protected Map<Integer, DocInfo> docs;

	public TrieIndexFile() {
		terms = new Trie<TermInfo>();
		docs = new HashMap<Integer, DocInfo>();
	}
}
