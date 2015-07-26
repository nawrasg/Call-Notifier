package fr.nawrasg.callnotifier.others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataProgressGET extends DataProgress{
		
	protected String doInBackground(String... x) {
		BufferedReader nBR = null;
		String data = null;
		try{
			HttpClient nClient = new DefaultHttpClient();
			String url = "?name=" + x[0] + "&number=" + x[1];
			if(x.length == 3){
				url += "&message=" + x[2];
			}
			URI nURL = new URI(mURL + url);
			HttpGet nGet = new HttpGet(nURL);
			HttpResponse nReponse = nClient.execute(nGet);
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
