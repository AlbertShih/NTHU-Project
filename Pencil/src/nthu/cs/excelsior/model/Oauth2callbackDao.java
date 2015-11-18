package nthu.cs.excelsior.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;


import nthu.cs.excelsior.object.Profile;


import com.googlecode.objectify.ObjectifyService;



@SuppressWarnings("serial")
public class Oauth2callbackDao extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 GoogleTokenResponse response =
		          new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
		              "49990830201.apps.googleusercontent.com", "RyP_H7XqCaGXThxwJKcEIQMH",
		              req.getParameter("code"), "http://education-star.appspot.com/oauth2callback")
		              .execute();
		String access_token = response.getAccessToken();
		URL urUserid = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+access_token); 
		HttpURLConnection connObtainUserId =  (HttpURLConnection) urUserid.openConnection();
		connObtainUserId.setRequestMethod("GET");
		connObtainUserId.setDoInput(true);
		StringBuilder sbLines = new StringBuilder(""); 
		System.out.println(access_token);
		// if token is correct
		 String strLine = "";
		 String id=null;
		if (connObtainUserId.getResponseCode() == HttpURLConnection.HTTP_OK){
			// get response
			 BufferedReader readerId =  new BufferedReader(new 
					 InputStreamReader(connObtainUserId.getInputStream(),"utf-8"));
			 while((strLine=readerId.readLine())!=null){
				 sbLines.append(strLine);
			 }
			 try {
					JSONObject j = new JSONObject(sbLines.toString());
					id = j.getString("id");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 }
		 }	 
			 sbLines = new StringBuilder(""); 
			 URL urUserInfo = new URL("https://www.googleapis.com/plus/v1/people/"+id+"/?access_token="+access_token
					 +"&fields=displayName,image/url,language,ageRange/min");	 
			 HttpURLConnection connObtainUserInfo =  (HttpURLConnection) urUserInfo.openConnection();
			 connObtainUserInfo.setRequestMethod("GET");
			 connObtainUserInfo.setDoInput(true);
			 if (connObtainUserInfo.getResponseCode() == HttpURLConnection.HTTP_OK){
				 BufferedReader readerINFO =  new BufferedReader(new 
						 InputStreamReader(connObtainUserInfo.getInputStream(),"utf-8"));
				 strLine = "";
				 while((strLine=readerINFO.readLine())!=null){
					 sbLines.append(strLine);
				 }
			 }
			 System.out.println(sbLines);
			 try {
					JSONObject j = new JSONObject(sbLines.toString());
					Profile p=new Profile(id,j.getString("displayName"),j.getJSONObject("image").getString("url"),
							j.getString("language"),j.getJSONObject("ageRange").getString("min"));
				//	setModel(req, p);
					
					HttpSession session = req.getSession();
					session.setAttribute("id", id);
					session.setAttribute("displayName", j.getString("displayName"));
					session.setAttribute("imageUrl",j.getJSONObject("image").getString("url"));
			        session.setAttribute("laguage",j.getString("language"));
			        session.setAttribute("ageRange",j.getJSONObject("ageRange").getString("min"));
			        
					// if p doesn't exist in database then put it in
			        if(ObjectifyService.begin().query(Profile.class).filter("id", id)==null){
						ObjectifyService.begin().put(p);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 } 
		}
	
	
	
}
