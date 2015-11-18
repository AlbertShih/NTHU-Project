package nthu.cs.excelsior.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.object.Video;
import nthu.cs.excelsior.object.VideoId;
import nthu.cs.excelsior.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class VideoJsonView extends ResourceView {
	private static final Log log = LogFactory.getLog(VideoJsonView.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Responsing 200 OK");
		}
		@SuppressWarnings("unchecked")
		List<Video> vl = (List<Video>)getModel(req);
		List<VideoId> vIl = videoToVideoId(vl);
		resp.setContentType("application/json");
		// make sure no intermediate node caches the result
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(vIl));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(log.isDebugEnabled()) {
			log.debug("Responsing 201 Created");
		}
		//Video v = (Video) getModel(req);
		resp.setStatus(201);
//		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")
//				+ "/" + v.getPath());
	}
	private List<VideoId> videoToVideoId(List<Video> vl){
		List<VideoId> vIl=new ArrayList<VideoId>();
		for(int i=0;i<vl.size();i++){
			VideoId t = new VideoId(vl.get(i).getId());
			vIl.add(t);
		}
		return vIl;
	}
}
