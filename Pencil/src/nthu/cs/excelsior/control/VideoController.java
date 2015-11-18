package nthu.cs.excelsior.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.control.ResourceController;
import nthu.cs.excelsior.object.Video;
import nthu.cs.excelsior.service.json.JsonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class VideoController extends ResourceController<Video>{
	private static final Log log = LogFactory
			.getLog(VideoController.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// invoke business logics
		if (log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/video-dao");
		// dispatch to view
		if (log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		if (!req.getHeader("Accept").contains("application/json")) {
			forward(req, resp, "/view/406-not-acceptable-view");
			return;
		}
		forward(req, resp, "/view/video-json-view");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Setting up model in request");
			}
			String body = getRequestBody(req);	
			Video video = JsonService.deserialize(body, Video.class);		
			setModel(req, video);
		} catch (Exception e) {
			forward(req, resp, "/view/400-bad-request-view");
			if (log.isInfoEnabled()) {
				log.info("Bad request: " + e.getMessage());
			}
			return;
		}

		// invoke business logics
		if (log.isDebugEnabled()) {
			log.debug("Invoking business logics");
		}
		include(req, resp, "/model/business/video-dao");
		
		if (log.isDebugEnabled()) {
			log.debug("Dispatching to view");
		}
		forward(req, resp, "/view/video-json-view");
	}
}
