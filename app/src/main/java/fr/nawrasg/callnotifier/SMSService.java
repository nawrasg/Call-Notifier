package fr.nawrasg.callnotifier;

import java.net.URLEncoder;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import fr.nawrasg.callnotifier.others.DataProgressGET;
import fr.nawrasg.callnotifier.others.DataProgressPOST;
import fr.nawrasg.callnotifier.others.Settings;

public class SMSService extends IntentService{

	public SMSService(){
		super("SMSService");
	}
	
	public SMSService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle nBundle = intent.getExtras();
		try{
			if(nBundle != null){
				final Object[] nList = (Object[]) nBundle.get("pdus");
				for(int i = 0; i < nList.length; i++){
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) nList[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String nMessage = URLEncoder.encode(currentMessage.getDisplayMessageBody(), "UTF-8");
                    if(Settings.getMethod().equals("GET")){
		    			DataProgressGET nCmd = new DataProgressGET();
		    			nCmd.execute(Settings.getName(phoneNumber), phoneNumber, nMessage);		    		
		    		}else{
		    			DataProgressPOST nCmd = new DataProgressPOST();
		    			nCmd.execute(Settings.getName(phoneNumber), phoneNumber, nMessage);		    		

		    		}
				}
			}
		}catch(Exception e){
			
		}
	}

}
