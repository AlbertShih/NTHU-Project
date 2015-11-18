package nthu.cs.excelsior.control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nthu.cs.excelsior.control.ResourceController;
import javax.servlet.http.HttpSession;

import nthu.cs.excelsior.object.Note;
import nthu.cs.excelsior.service.json.JsonService;



@SuppressWarnings("serial")
public class NoteController extends ResourceController<Note>{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		HttpSession session = req.getSession();
		if(session.getAttribute("id")!=null){
			include(req, resp, "/model/business/note-dao");
		}
		else{
			forward(req, resp, "/view/401-not-login-view");
			return;
		}
		// dispatch to view
		if (!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/note-json-view");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Note note; 
		try{
			String body = getRequestBody(req);	
			note = JsonService.deserialize(body, Note.class);		
			setModel(req, note);
		} catch (Exception e) {
			forward(req, resp, "/view/400-bad-request-view");	
			return;
		}
		// invoke business logics
		HttpSession session = req.getSession();
		if(session.getAttribute("id").equals(note.getId())){
			include(req, resp, "/model/business/note-dao");
			forward(req, resp, "/view/note-json-view");
		}
		else{
			forward(req, resp, "/view/401-not-login-view");
			return;
		}
	}
}
