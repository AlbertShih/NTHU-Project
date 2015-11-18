package nthu.cs.excelsior.recommender.index;

/**
 * Indicates difficulties in accessing an index.
 */
public class IndexAccessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IndexAccessException() {
		super();
	}
	
	public IndexAccessException(String message) {
		super(message);
	}

	public IndexAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexAccessException(Throwable cause) {
		super(cause);
	}

}
