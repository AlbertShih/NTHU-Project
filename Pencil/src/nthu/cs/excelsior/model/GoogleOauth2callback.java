package nthu.cs.excelsior.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;


import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class GoogleOauth2callback {
	public static String getAccessToken(HttpServletRequest req) throws IOException {

		URL urlObtainToken = new URL(
				"https://accounts.google.com/o/oauth2/token");
		HttpURLConnection connectionObtainToken = (HttpURLConnection) urlObtainToken
				.openConnection();

		connectionObtainToken.setRequestMethod("POST");
		connectionObtainToken.setDoOutput(true);

		OutputStreamWriter writer = new OutputStreamWriter(
				connectionObtainToken.getOutputStream());
		writer.write("code=" + req.getParameter("code") + "&");
		//writer.write("client_id=511406630946.apps.googleusercontent.com&");
		writer.write("client_id=49990830201.apps.googleusercontent.com&");
		//writer.write("client_secret=AlsTlb_CEw1pd-GlcK8KFzmV&");
		writer.write("client_secret=RyP_H7XqCaGXThxwJKcEIQMH&");
		//writer.write("redirect_uri=http://localhost:8888/oauth2callback&");
		//writer.write("redirect_uri=http://vitalon-p.appspot.com/oauth2callback&");
		writer.write("redirect_uri=http://education-star.appspot.com/oauth2callback&approval_prompt=force&");		
		writer.write("grant_type=authorization_code");
		writer.close();
		String access_token = null;
		if (connectionObtainToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
			StringBuilder sbLines = new StringBuilder("");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connectionObtainToken.getInputStream(), "utf-8"));
			String strLine = "";
			while ((strLine = reader.readLine()) != null) {
				sbLines.append(strLine);
			}
			try {
				JSONObject j = new JSONObject(sbLines.toString());
				access_token = j.getString("access_token");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return access_token;
	}
}
