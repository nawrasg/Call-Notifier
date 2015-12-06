package fr.nawrasg.callnotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import fr.nawrasg.callnotifier.others.Settings;

public class SMSReceiver extends BroadcastReceiver{

	final SmsManager nSM = SmsManager.getDefault();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(Settings.ifSMS()){
			Bundle nBundle = intent.getExtras();
			Intent nIntent = new Intent(context, SMSService.class);
			nIntent.putExtras(nBundle);
			context.startService(nIntent);			
		}
	}

}
