package fr.nawrasg.callnotifier;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import fr.nawrasg.callnotifier.others.Settings;

public class MyPhoneStateListener extends PhoneStateListener {
	private String mURL, mAPI;

	public void onCallStateChanged(int state, String incomingNumber) {

		switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				if (Settings.ifCall()) {
					getAtlantisUrl();
					try {
						run(incomingNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
		}
	}

	public void run(String phoneNumber) throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = null;
		if (Settings.getMethod().equals("GET")) {
			String nURL = getURL() + "?name=" + Settings.getName(phoneNumber) + "&number=" + phoneNumber;
			if (mAPI != null) {
				nURL += "&api=" + mAPI;
			}
			request = new Request.Builder()
					.url(nURL)
					.build();
		} else {
			FormEncodingBuilder nBody = new FormEncodingBuilder()
					.add("name", Settings.getName(phoneNumber))
					.add("number", phoneNumber);
			if (mAPI != null) {
				nBody.add("api", mAPI);
			}
			request = new Request.Builder()
					.url(getURL())
					.post(nBody.build())
					.build();
		}

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void onResponse(Response response) throws IOException {
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
			}
		});
	}

	private String getURL() {
		if (Settings.isAtlantis()) {
			return mURL;
		} else {
			return Settings.getURL();
		}
	}

	private void getAtlantisUrl() {
		Uri nUri = Uri.parse("content://fr.nawrasg.atlantis.provider");
		ContentProviderClient nCPC = App.getContext().getContentResolver().acquireContentProviderClient(nUri);
		try {
			if (nCPC != null) {
				Cursor nCursor = nCPC.query(nUri, null, "call_notifier", null, null);
				if (nCursor != null && nCursor.moveToFirst()) {
					mAPI = nCursor.getString(nCursor.getColumnIndex("api"));
					mURL = nCursor.getString(nCursor.getColumnIndex("url"));
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
