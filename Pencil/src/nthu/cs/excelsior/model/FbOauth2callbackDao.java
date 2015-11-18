package nthu.cs.excelsior.model;

//import org.json.JSONObject;
//import org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nthu.cs.excelsior.object.Profile;

@SuppressWarnings("serial")
public class FbOauth2callbackDao extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		if (code == null || code.equals("")) {
			// an error occurred, handle this
		}

		String token = null;
		try {
			String g = "https://graph.facebook.com/oauth/access_token?client_id=656442537728789&redirect_uri="
					+ URLEncoder.encode("http://education-star.appspot.com/oauth2callback","UTF-8")
					+ "&client_secret=e82ed903145b5c2329b230c2b0e597ba&code="
					+ code;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			token = b.toString();
			if (token.startsWith("{"))
				throw new Exception("error on requesting token: " + token
						+ " with code: " + code);
		} catch (Exception e) {
			// an error occurred, handle this
			e.printStackTrace();
		}

		String graph = null;
		try {
			String g = "https://graph.facebook.com/me?" + token;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
		} catch (Exception e) {
			// an error occurred, handle this
			e.printStackTrace();
		}

		String id=null;//facebookId
		String firstName=null;
		String middleNames=null;
		String lastName=null;
		String displayName=null;
		String userName=null;
		String language=null;
		String imageUrl=null;
//		System.out.println(graph);
		while(graph==null);
		try {
			JSONObject json = new JSONObject(graph);
			id = json.getString("id");
			userName = json.getString("username");
			imageUrl = "http://graph.facebook.com/"+userName+"/picture";
			firstName = json.getString("first_name");
			language = json.getString("locale");
			if (json.has("middle_name"))
				middleNames = json.getString("middle_name");
			else
				middleNames = null;
			if (middleNames != null && middleNames.equals(""))
				middleNames = null;
			lastName = json.getString("last_name");
			if(middleNames == null)
				displayName = lastName + firstName;
		} catch (JSONException e) {
			// an error occurred, handle this
			e.printStackTrace();
		}	
		
		   Profile p=new Profile(id,displayName,imageUrl,language,"");
//						j.getJSONObject("image").getString("url"),
//						j.getString("language"),j.getJSONObject("ageRange").getString("min"));
			//	setModel(req, p);
				
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
				session.setAttribute("displayName", displayName);
				session.setAttribute("imageUrl",imageUrl);
		        session.setAttribute("laguage",language);
		        session.setAttribute("ageRange","");
//				session.setAttribute("imageUrl",j.getJSONObject("image").getString("url"));
//		        session.setAttribute("laguage",j.getString("language"));
//		        session.setAttribute("ageRange",j.getJSONObject("ageRange").getString("min"));
		        
				// if p doesn't exist in database then put it in
		        //if(ObjectifyService.begin().query(Profile.class).filter("id", id)==null){
					//ObjectifyService.begin().put(p);
				//}
			
		
		
		
	}
	
	
	
}