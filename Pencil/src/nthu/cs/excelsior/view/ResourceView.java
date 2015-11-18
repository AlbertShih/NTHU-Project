package nthu.cs.excelsior.view;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import nthu.cs.excelsior.ModelAwareServlet;

@SuppressWarnings("serial")
public class ResourceView extends HttpServlet {
	public Object getModel(HttpServletRequest req) {
		return req.getAttribute(ModelAwareServlet.MODEL_ATTRIBUTE_NAME);
	}
}
