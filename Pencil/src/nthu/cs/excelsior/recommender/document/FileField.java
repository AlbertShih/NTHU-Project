package nthu.cs.excelsior.recommender.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Stores value in a file.
 */
public class FileField extends Field {
	private static final long serialVersionUID = 1L;
	
	private static final String DEAFULT_CHAR_SET = "UTF-8";
	
	protected File file;
	protected String charSet;
	protected String value;
	
	public FileField(String name, String path) {
		this(name, true, 1.00, path, DEAFULT_CHAR_SET);
	}
	
	public FileField(String name, boolean indexed, double boost, 
			String path, String charSet) {
		super(name, indexed, boost);
		this.file = new File(path);
		if (!file.exists() || !file.isFile()) {
			throw new IllegalArgumentException();	
		}	
		this.charSet = charSet;
	}
	
	@Override
	public String stringValue() {
		if (value != null) {
			return value;
		}	
		StringBuffer valueBuf = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(readerValue());
			String s;
			while ((s = br.readLine()) != null) {
				valueBuf.append(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value = valueBuf.toString();
	}

	@Override
	public Reader readerValue() {
		Reader ret = null;
		try {
			ret = new InputStreamReader(new FileInputStream(file), charSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
