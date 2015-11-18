package nthu.cs.excelsior.recommender.store;

import java.io.Serializable;

/**
 * A flat list of files (i.e., byte arrays). Implementations can decide where
 * (e.g., RAM, file system, or remote machine) are the files stored physically.
 * 
 */
public interface Directory {

	void updateOrCreate(String name, Serializable file) throws Exception;

	Serializable read(String name) throws Exception;
	
}
