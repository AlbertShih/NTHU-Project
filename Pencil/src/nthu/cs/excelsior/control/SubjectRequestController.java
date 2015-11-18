package nthu.cs.excelsior.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.object.Video;

@SuppressWarnings("serial")
public class SubjectRequestController extends ResourceController<Video>{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		
	    include(req, resp, "/model/business/subjectRequest-dao");
		// dispatch to view
		if (!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/video-json-view");
	}
}
