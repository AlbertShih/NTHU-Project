package nthu.cs.excelsior.recommender.util;

/**
 * A simple wrapper of {@link CharArrayMap}.
 */
public class CharArraySet {
	private static final Object DEFAULT_VALUE = new Object();
	
	protected CharArrayMap map;
	
	public CharArraySet() {
		map = new CharArrayMap();
	}
	
	public CharArraySet(int initialCapacity) {
		map = new CharArrayMap(initialCapacity);
	}
	
	public CharArraySet(int initCapacity, float loadFactor) {
		map = new CharArrayMap(initCapacity, loadFactor);
	}
	
	public void add(char[] k, int len) {
		map.put(k, len, DEFAULT_VALUE);
	}
	
	public boolean contains(char[] k, int len) {
		return map.containsKey(k, len);
	}
}
