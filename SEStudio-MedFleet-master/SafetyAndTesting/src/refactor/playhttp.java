package refactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
*/
public class playhttp 
{
	/*
	public static void main(String[] args) {
		String url = "http://104.43.172.127:3000/drones/56c637561ad762bc0c725c67";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		
		request.addHeader("Content-Type", "application/json");
		HttpResponse response = null;
		BufferedReader rd;
		
		StringBuffer result = new StringBuffer();
		String line = "";
		
		try {
			response = client.execute(request);
			rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());
		System.out.println(result.toString());

	}*/
}
