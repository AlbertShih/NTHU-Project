package nthu.cs.excelsior.model;

import java.io.IOException;

import java.net.URLDecoder;

import java.security.MessageDigest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Note;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.googlecode.objectify.ObjectifyService;



@SuppressWarnings("serial")
public class NoteDao extends ModelAwareServlet<Note>{
	private static final Log log = LogFactory.getLog(VideoDao.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String id=null; 
			HttpSession session = req.getSession();
			id = session.getAttribute("id").toString();	
			String videoId = req.getParameter("videoId");
			List<Note> nl=null;
			if(!videoId.equals("ALL")){
				nl=(List<Note>)ObjectifyService.begin().query(Note.class).filter("id", id).filter("videoId", videoId).list();
			}
			else{
				nl=(List<Note>)ObjectifyService.begin().query(Note.class).filter("id", id).list();
			}
				setModel(req, nl);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Creating a domain object");
		}
			HttpSession session = req.getSession();
			String id = session.getAttribute("id").toString();
			Note note = getModel(req);
			List<Note> nl=(List<Note>)ObjectifyService.begin().query(Note.class).filter("id", id).filter("title", note.getTitle()).filter("videoId",note.getVideoId()).list();
			if(nl.isEmpty()){
				int n = ObjectifyService.begin().query(Note.class).count()+1;
				//System.out.println(encrypt(String.valueOf(n), "MD5"));
				note.setKey(encrypt(String.valueOf(n), "MD5"));
				note.setTitle(URLDecoder.decode(note.getTitle(), "UTF-8"));
				note.setContent(URLDecoder.decode(note.getContent(), "UTF-8"));
				ObjectifyService.begin().put(note);
			}
			else{
				ObjectifyService.begin().delete(nl.get(0));
				note.setKey(nl.get(0).getKey());
				note.setTitle(URLDecoder.decode(note.getTitle(), "UTF-8"));
				note.setContent(URLDecoder.decode(note.getContent(), "UTF-8"));
				ObjectifyService.begin().put(note);
			}
	}
	public static String encrypt(String str, String encType) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(encType);
			md.update(str.getBytes());
			result = toHexString(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
 
	private static String toHexString(byte[] in) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < in.length; i++){
			String hex = Integer.toHexString(0xFF & in[i]);
			if (hex.length() == 1){
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}