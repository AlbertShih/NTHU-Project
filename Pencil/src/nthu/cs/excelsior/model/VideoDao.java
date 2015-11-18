package nthu.cs.excelsior.model;

import java.io.IOException;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Video;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.googlecode.objectify.ObjectifyService;


@SuppressWarnings("serial")
public class VideoDao extends ModelAwareServlet<Video>{
	private static final Log log = LogFactory.getLog(VideoDao.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String videoId=null;
			String queryString=null;
			String userId=null;
			try{
				    queryString = URLDecoder.decode(
						req.getParameter("queryString"), "UTF-8");		
					String path[]=new String[10];
					Object o[]=queryString.split("/");
						for(int i=0;i<o.length;i++)
							path[i]=(String) o[i];	
					
					List<Video>vl= (List<Video>) ObjectifyService.begin().query(Video.class)
							.filter("path1", path[0])
							.filter("path2", path[1])
							.filter("path3", path[2])
							.filter("path4", path[3])
							.filter("path5", path[4])
							.filter("path6", path[5])
							.filter("path7", path[6])
							.filter("path8", path[7])
							.filter("path9", path[8])
							.filter("path10", path[9])
							.list();	
					setModel(req, vl);
			}catch(Exception e1){
				try{
					videoId=URLDecoder.decode(
							req.getParameter("videoId"), "UTF-8");
					List<Video>vl= (List<Video>) ObjectifyService.begin().query(Video.class).filter("id", videoId).list();
					setModel(req, vl);
				}catch(Exception e2){
					userId=URLDecoder.decode(
							req.getParameter("userId"), "UTF-8");
					List<Video>vl= (List<Video>) ObjectifyService.begin().query(Video.class).filter("userId", userId).list();
					setModel(req, vl);
				}
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) {
			log.debug("Creating a domain object");
		}
		Video video = getModel(req);
		video.setTitle(URLDecoder.decode(video.getTitle(), "UTF-8"));
		video.setDescription(URLDecoder.decode(video.getDescription(), "UTF-8"));
		System.out.println(video.getTitle());
		ObjectifyService.begin().put(video);
	}
}
