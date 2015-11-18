package nthu.cs.excelsior.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.googlecode.objectify.ObjectifyService;

import nthu.cs.excelsior.ModelAwareServlet;
import nthu.cs.excelsior.object.Video;

@SuppressWarnings("serial")
public class SubjectRequestDao  extends ModelAwareServlet<Video>{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		if(session.getAttribute("id")!=null){
//			String edu=ageMapEducation(Integer.valueOf(session.getAttribute("age").toString()));
//			String subject1=edu+"/7thGrader/math/numberAndQuantity/sub1";
//		}
//		else{
//			String subject1="math";
//			String subject2="english";
//			String subject3="chinese";
			List<Video> nl=(List<Video>)ObjectifyService.begin().query(Video.class).list();
			setModel(req, nl);
		//}
	}
	private String ageMapEducation(int age){
		if(age>=7 && age <=12) return "EL";
		else if (age>12 && age <=14) return "JH";
		else if (age>14 && age <=18) return "SH";
		else if (age>18 && age<=21) return "UN";
		else return "UN";
	}
}
