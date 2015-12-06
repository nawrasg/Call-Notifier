package fr.nawrasg.callnotifier.others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class DataProgressPOST extends DataProgress{
		
	protected String doInBackground(String... x) {
		BufferedReader nBR = null;
		String data = null;
		try{
			HttpClient nClient = new DefaultHttpClient();
			URI nURL = new URI(mURL);
			HttpPost nPost = new HttpPost(nURL);
			List<NameValuePair> nPairs = new ArrayList<NameValuePair>();
			nPairs.add(new BasicNameValuePair("name", x[0]));
			nPairs.add(new BasicNameValuePair("number", x[1]));
			if(x.length == 3){
				nPairs.add(new BasicNameValuePair("message", x[2]));
			}
			nPost.setEntity(new UrlEncodedFormEntity(nPairs));
			HttpResponse nReponse = nClient.execute(nPost);
			nBR = new BufferedReader(new InputStreamReader(nReponse.getEntity().getContent()));
			StringBuffer nSB = new StringBuffer("");
			String l = "";
			while((l = nBR.readLine()) != null){
				nSB.append(l);
			}
			nBR.close();
			data = nSB.toString();
			return data;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "NA";	
	}
}
