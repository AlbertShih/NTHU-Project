package nthu.cs.excelsior.recommender.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A search tree that traverses a sequence of characters to find the target 
 * node. Note that this is not a general purpose class. For example, it cannot 
 * remove elements.
 * 
 * @param <E> the element type
 */
public class Trie<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	protected class Node implements Serializable {
		private static final long serialVersionUID = 1L;
		
		E e;
		Map<Character, Node> children;
		
		Node(E e) {
			this.e = e;
			children = new HashMap<Character, Node>();
		}
	}
	
	protected Node root;
	
	public Trie() {
		root = new Node(null);
	}
	
	/**
	 * Gets the node corresponding to the last of a sequence of characters. 
	 * Adds nodes corresponding to characters during the traveling if they do
	 * not exist.
	 * 
	 * @param k
	 * @param len
	 * @return
	 */
	protected Node getOrAddNode(char[] k, int len) {
		Node ret = root;
		for(int i = 0; i < len; i++) {
			Node child = ret.children.get(k[i]);
			if(child == null) {
				child = new Node(null);
				ret.children.put(k[i], child);
			}
			ret = child;
		}
		return ret;
	}
	
	protected Node getNode(char[] k, int len) {
		Node ret = root;
		for(int i = 0; i < len; i++) {
			Node child = ret.children.get(k[i]);
			if(child == null) {
				return null;
			}
			ret = child;
		}
		return ret;
	}
	
	/**
	 * Adds an element corresponding to a sequence of characters into this trie.
	 * 
	 * @param k
	 * @param len
	 * @param e
	 * @return
	 */
	public E add(char[] k, int len, E e) {
		Node node = getOrAddNode(k, len);
		E ret = node.e;
		node.e = e;
		return ret;
	}
	
	/**
	 * Checks if there is an element corresponding to a sequence of characters 
	 * in this trie.
	 * 
	 * @param k
	 * @param len
	 * @return
	 */
	public boolean contains(char[] k, int len) {
		Node node = getNode(k, len);
		return node != null && node.e != null;
	}
	
	/**
	 * Gets the element corresponding to a sequence of characters.
	 * 
	 * @param k
	 * @param len
	 * @return
	 */
	public E get(char[] k, int len) {
		Node node = getNode(k, len);
		return node == null ? null : node.e;
	}
	
}
