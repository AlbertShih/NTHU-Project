package nthu.cs.excelsior.control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.object.Profile;

@SuppressWarnings("serial")
public class Oauth2callbackController extends  ResourceController<Profile>{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		 include(req, resp, "/model/business/oauth2callback-dao"); //->login HttpSession
//		 include(req, resp, "/model/business/fb-oauth2callback-dao"); //->login HttpSession
		 forward(req, resp, "/view/loginPage-json-view");
		 
		 //resp.sendRedirect("http://localhost:8888/main"); 
		 //resp.sendRedirect("http://vitalon-p.appspot.com/main"); 
	}
}
