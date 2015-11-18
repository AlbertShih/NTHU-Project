package nthu.cs.excelsior.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.object.Note;
import nthu.cs.excelsior.service.json.JsonService;

@SuppressWarnings("serial")
public class NoteJsonView extends ResourceView{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object g = getModel(req);
		resp.setContentType("application/json");
		// make sure no intermediate node caches the result
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(g));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Note n = (Note) getModel(req);
		resp.setStatus(201);
		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")
				+ "/" + n.getKey());
	}

}
