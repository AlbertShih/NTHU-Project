package nthu.cs.excelsior.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Video;


import com.googlecode.objectify.ObjectifyService;

	@SuppressWarnings("serial")
	public class VideoDeleteDao extends ModelAwareServlet<Video>{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
//				String id=null; 
//				HttpSession session = req.getSession();
//				id = session.getAttribute("id").toString();	
				String videoId = req.getParameter("videoId");
				List<Video> nl=null;
				if(videoId!=null){
					nl=(List<Video>)ObjectifyService.begin().query(Video.class).filter("id", videoId).list();
					if(nl!=null)
						ObjectifyService.begin().delete(nl);
				}

					//setModel(req, nl);
		}

}
