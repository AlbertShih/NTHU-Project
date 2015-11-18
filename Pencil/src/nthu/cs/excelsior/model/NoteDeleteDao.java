package nthu.cs.excelsior.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.ObjectifyService;

import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Note;

@SuppressWarnings("serial")
public class NoteDeleteDao  extends ModelAwareServlet<Note>{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			HttpSession session = req.getSession();
			String id = session.getAttribute("id").toString();	
			String key = req.getParameter("key");
			
			List<Note> nl=null;
			if(key!=null){
				nl=(List<Note>)ObjectifyService.begin().query(Note.class).filter("id", id).filter("key", key).list();
				if(nl!=null)
					ObjectifyService.begin().delete(nl);
			}

				//setModel(req, nl);
	}
}
