package fr.nawrasg.callnotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ServiceReceiver extends BroadcastReceiver{
	private static MyPhoneStateListener phoneListener;
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		TelephonyManager telephony = (TelephonyManager) 
				arg0.getSystemService(Context.TELEPHONY_SERVICE);
		if(phoneListener == null){
			phoneListener = new MyPhoneStateListener();
			telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
			Log.d("Nawras", "listener");			
		}
	}

}
