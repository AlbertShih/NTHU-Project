package nthu.cs.excelsior.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nthu.cs.excelsior.ModelAwareServlet;

/**
 * A base controller that provides utility methods for processing a request over a resource. 
 */
@SuppressWarnings("serial")
public abstract class ResourceController<D> extends ModelAwareServlet<D> {

	public String getRequestBody(HttpServletRequest req) throws IOException {
		StringBuffer buf = new StringBuffer();
		char[] c = new char[1024];
		int len;
		while ((len = req.getReader().read(c)) != -1) {
			buf.append(c, 0, len);
		}
		return buf.toString();
	}

	public void forward(HttpServletRequest req, HttpServletResponse resp,
			String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		if (dispatcher == null)
			throw new IllegalArgumentException();
		dispatcher.forward(req, resp);
	}

	public void include(HttpServletRequest req, HttpServletResponse resp,
			String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		if (dispatcher == null)
			throw new IllegalArgumentException();
		dispatcher.include(req, resp);
	}
	
}
