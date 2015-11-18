package nthu.cs.excelsior.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nthu.cs.excelsior.object.Note;

@SuppressWarnings("serial")
public class NoteDeleteController extends ResourceController<Note>{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	// invoke business logics
	HttpSession session = req.getSession();
	if(session.getAttribute("id")!=null){
		include(req, resp, "/model/business/note-delete-dao");
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
	forward(req, resp, "/view/note-delete-json-view");
	}
}
