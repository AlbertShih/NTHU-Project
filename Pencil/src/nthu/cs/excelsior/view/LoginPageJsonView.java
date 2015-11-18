package nthu.cs.excelsior.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class LoginPageJsonView extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<script>");
        out.println("window.location = 'http://education-star.appspot.com'");
        //out.println("window.location = 'http:///vitalon-p.appspot.com'");
//        out.println("window.location = 'http://localhost:8888'");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
        out.close();
		
	}
}
