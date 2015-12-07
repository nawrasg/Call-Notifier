package fr.nawrasg.callnotifier;

import android.app.IntentService;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.SmsMessage;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import fr.nawrasg.callnotifier.others.Settings;

public class SMSService extends IntentService {
	private String mAPI = null, mURL = null;

	public SMSService() {
		super("SMSService");
	}

	public SMSService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final Bundle nBundle = intent.getExtras();
		final OkHttpClient client = new OkHttpClient();

		if (nBundle != null) {
			final Object[] nList = (Object[]) nBundle.get("pdus");
			for (int i = 0; i < nList.length; i++) {
				SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) nList[i]);
				String phoneNumber = currentMessage.getDisplayOriginatingAddress();
				String nMessage = null;
				try {
					nMessage = URLEncoder.encode(currentMessage.getDisplayMessageBody(), "UTF-8");
					getAtlantisUrl();
					Request request = null;
					if (Settings.getMethod().equals("GET")) {
						String nURL = getURL() + "?name=" + Settings.getName(phoneNumber) + "&number=" + phoneNumber + "&message=" + nMessage;
						if(mAPI != null){
							nURL += "&api=" + mAPI;
						}
						request = new Request.Builder()
								.url(nURL)
								.build();
					} else {
						FormEncodingBuilder nBody = new FormEncodingBuilder()
								.add("name", Settings.getName(phoneNumber))
								.add("number", phoneNumber)
								.add("message", nMessage);
						if(mAPI != null){
							nBody.add("api", mAPI);
						}
						request = new Request.Builder()
								.url(getURL())
								.post(nBody.build())
								.build();
					}
					Response response = client.newCall(request).execute();

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getURL(){
		if(Settings.isAtlantis()){
			return mURL;
		}else {
			return Settings.getURL();
		}
	}

	private void getAtlantisUrl(){
		Uri nUri = Uri.parse("content://fr.nawrasg.atlantis.provider");
		ContentProviderClient nCPC = App.getContext().getContentResolver().acquireContentProviderClient(nUri);
		try {
			if(nCPC != null){
				Cursor nCursor = nCPC.query(nUri, null, "call_notifier", null, null);
				if(nCursor != null && nCursor.moveToFirst()){
					mAPI = nCursor.getString(nCursor.getColumnIndex("api"));
					mURL = nCursor.getString(nCursor.getColumnIndex("url"));
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
