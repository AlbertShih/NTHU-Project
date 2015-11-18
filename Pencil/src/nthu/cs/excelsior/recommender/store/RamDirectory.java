package nthu.cs.excelsior.recommender.store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nthu.cs.excelsior.recommender.index.*;



/**
 * Keeps files in memory.
 * 
 * <p>
 * Note this class does not implement any locking mechanism, so it allows only
 * one operation from {@link IndexWriter} or {@link IndexReader} a time. In 
 * production, care should be taken to handle concurrent operations. 
 * </p>
 */
public class RamDirectory implements Directory {
	protected Map<String, byte[]> files;
	
	public RamDirectory() {
		files = new HashMap<String, byte[]>();
	}

	@Override
	public void updateOrCreate(String name, Serializable file) 
			throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(file);
		files.put(name, b.toByteArray());
		o.close();
		b.close();
	}

	@Override
	public Serializable read(String name)
			throws IOException, ClassNotFoundException {
		byte[] file = files.get(name);
		if(file == null)
			return null;
		ByteArrayInputStream b = new ByteArrayInputStream(file);
		ObjectInputStream o = new ObjectInputStream(b);
		Serializable ret = (Serializable) o.readObject();
		o.close();
		b.close();
		return ret;
	}

}
