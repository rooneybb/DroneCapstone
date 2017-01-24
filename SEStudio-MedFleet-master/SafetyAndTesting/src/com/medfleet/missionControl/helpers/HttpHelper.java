package com.medfleet.missionControl.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
*/
import config.Config;


public enum HttpHelper 
{/*
	    INSTANCE;

	    private HttpHelper() {}
	    
	    public static HttpHelper getInstance() { return INSTANCE; }
	   
	    Config c = Config.getInstance();

		public String GetRequest (String url) throws Exception {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = null;
			BufferedReader rd;
			StringBuffer result = new StringBuffer();
			String line = "";

			response = client.execute(request);
			
			rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			return result.toString();
		}
		
		public String PostRequest(String url, String body) throws Exception {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(body, 
                     ContentType.create("application/json")));
			HttpResponse response = client.execute(post);

			BufferedReader rd = new BufferedReader(
	                        new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();

		}*/
}