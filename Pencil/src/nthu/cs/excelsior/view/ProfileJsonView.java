package nthu.cs.excelsior.view;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nthu.cs.excelsior.object.Profile;
import nthu.cs.excelsior.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("serial")
public class ProfileJsonView  extends ResourceView{
	private static final Log log = LogFactory.getLog(ProfileJsonView.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{		
		if (log.isDebugEnabled()) {
			log.debug("Responsing 200 OK");
		}
		HttpSession session = req.getSession();
		Object p = new Profile(session.getAttribute("id").toString(),session.getAttribute("displayName").toString()
				,session.getAttribute("imageUrl").toString(),session.getAttribute("laguage").toString(),
				session.getAttribute("ageRange").toString());
		resp.setContentType("application/json");
		// make sure no intermediate node caches the result
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(p));
		
//		resp.setCharacterEncoding("UTF-8");
//		PrintWriter out = resp.getWriter();
//		//Document doc = Jsoup.connect("http://vitalon-p.appspot.com/main_login.html").get();
//		Document doc = Jsoup.connect("http://localhost:8888/main_login.html").get();
//		Elements UserPic = doc.select("#UserPic");
//		UserPic.attr("src",session.getAttribute("imageUrl").toString());
//		Elements name = doc.select("#name");
//		name.html(session.getAttribute("displayName").toString());
//		out.println(doc.toString());
	}
}