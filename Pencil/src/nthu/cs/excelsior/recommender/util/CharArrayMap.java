package nthu.cs.excelsior.recommender.util;

/**
 * Stores Strings as char[]'s in a hash table. Note that this is not a general
 * purpose class. For example, it cannot remove items from the set, nor does it
 * resize its hash table to be smaller, etc. It is designed to be quick to test
 * if a char[] is in the set without the necessity of converting it to a String
 * first.
 */
public class CharArrayMap {
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    /**
	 * Class for the entries kept inside this map.
	 */
	private class Entry {
		final char[] key;
		int len;
		Object value;
		Entry next;

		Entry(char[] k, int l, Object v, Entry n) {
			key = k;
			len = l;
			value = v;
			next = n;
		}
	}
    
    /**
     * Each element represents a bucket of entries. The length of this array is 
     * doubled when size >= threshold.
     */
    private Entry[] table;
    
    private int size;
    
    private final float loadFactor;
    
    /**
     * The next size to double the table. Equals (capacity * load factor).
     */
    private int threshold;
    
    public CharArrayMap() {
    	this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    
    public CharArrayMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    
	public CharArrayMap(int initCapacity, float loadFactor) {
		if(initCapacity < 1 || loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException();

		// finds a power of 2 which is >= initialCapacity
		int capacity = 1;
		while(capacity < initCapacity)
			capacity <<= 1;

		this.loadFactor = loadFactor;
		threshold = (int) (capacity * loadFactor);
		table = new Entry[capacity];
		size = 0;
	}
	
	/**
	 * Obtains an index for the specified key. The <code>null</code> key is 
	 * hashed to index 0.
	 * 
	 * @param key
	 * @param capacity
	 * @return
	 */
	private static int indexFor(char[] key, int len, int capacity) {
		if(key == null)
			return 0;
		int index = 0;
		for(int i = 0; i < len; i++) {
			index = index * 31 + key[i];
		}
		return (index >>> 1) % capacity; // >>> ensures positive index 
    }
	
	private void addEntry(int bucketIndex, char[] k, int len, Object v) {
		Entry entry = table[bucketIndex];
		table[bucketIndex] = new Entry(k, len, v, entry);
		if(size++ >= threshold)
			resize(2 * table.length);
	}
	
	/**
	 * @param k key, may be <code>null</code>
	 * @param len
	 * @return
	 */
	private Entry getEntry(char[] k, int len) {
		if(k == null && len != 0)
			throw new IllegalArgumentException();
		
		int i = indexFor(k, len, table.length);
		for(Entry entry = table[i]; entry != null; entry = entry.next) {
			if((entry.key == k && entry.len == len) 
					|| (k != null && entryEquals(entry, k, len)))
				return entry;
		}
		return null;
	}
	
	/**
	 * 
	 * @param entry the <code>key</code> field cannot be null
	 * @param k cannot be null
	 * @param len
	 * @return
	 */
	private boolean entryEquals(Entry entry, char[] k, int len) {
		if(entry.len != len)
			return false;
		for(int i = 0; i < len; i++) {
			if(entry.key[i] != k[i])
				return false;
		}
		return true;
	}

	
	/**
	 * @param k key, may be <code>null</code>
	 * @param len
	 */
	public Object put(char[] k, int len, Object v) {
		if(k == null && len != 0)
			throw new IllegalArgumentException();
		
		int i = indexFor(k, len, table.length);
		for(Entry entry = table[i]; entry != null; entry = entry.next) {
			if((entry.key == k && entry.len == len) 
					|| (k != null && entryEquals(entry, k, len))) {
				Object oldValue = entry.value;
				entry.value = v;
				return oldValue;
			}
		}
		// no existing value
		addEntry(i, k, len, v);
		return null;
	}
	
	/**
	 * @param k key, may be <code>null</code>
	 * @param len
	 */
	public boolean containsKey(char[] k, int len) {
		if(k == null && len != 0)
			throw new IllegalArgumentException();
		
		return getEntry(k, len) != null;
	}
	
	/**
	 * @param k key, may be <code>null</code>
	 * @param len
	 */
	public Object get(char[] k, int len) {
		Entry entry = getEntry(k, len);
		return (entry == null ? null : entry.value);
	}

	public int size() {
		return size;
	}
	
	private void resize(int newCapacity) {
		Entry[] newTable = new Entry[newCapacity];
		// rehash all entries into newTable
		for(int j = 0; j < table.length; j++) {
			Entry e = table[j];
			while(e != null) { 
				Entry next = e.next;
				// put e
				int i = indexFor(e.key, e.len, newCapacity);
				e.next = newTable[i];
				newTable[i] = e;
				e = next;
			}
		}
		table = newTable; // oldTable will be garbage collected
		threshold = (int) (newCapacity * loadFactor);
	}
}
