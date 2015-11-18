package nthu.cs.excelsior.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nthu.cs.excelsior.object.Profile;
@SuppressWarnings("serial")
public class Logout extends  ResourceController<Profile>{
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {	
		 HttpSession session = req.getSession();
		 if(session.getAttribute("id")!=null){
			 req.getSession().invalidate();
		 }
		 else{
			 forward(req, resp, "/view/406-not-acceptable-view");
			//resp.sendRedirect("http://localhost:8888");
			 //resp.sendRedirect("http://vitalon-p.appspot.com");
		 }
		}
}

