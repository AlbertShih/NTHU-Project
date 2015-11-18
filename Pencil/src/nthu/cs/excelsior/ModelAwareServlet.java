package nthu.cs.excelsior;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


/**
 * A base serlvet that knows how to access the model in a request. 
 * 
 * @param <D> type of domain object
 */
@SuppressWarnings("serial")
public class ModelAwareServlet<D> extends HttpServlet {
	public static final String MODEL_ATTRIBUTE_NAME = "model";

	@SuppressWarnings("unchecked")
	public D getModel(HttpServletRequest req) {
		return (D) req.getAttribute(MODEL_ATTRIBUTE_NAME);
	}

	public void setModel(HttpServletRequest req, D obj) {
		req.setAttribute(MODEL_ATTRIBUTE_NAME, obj);
	}

	public void setModel(HttpServletRequest req, List<D> obj) {
		req.setAttribute(MODEL_ATTRIBUTE_NAME, obj);
	}


}

