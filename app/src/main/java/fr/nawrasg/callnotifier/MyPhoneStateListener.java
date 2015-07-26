package fr.nawrasg.callnotifier;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import fr.nawrasg.callnotifier.others.DataProgressGET;
import fr.nawrasg.callnotifier.others.DataProgressPOST;
import fr.nawrasg.callnotifier.others.Settings;

public class MyPhoneStateListener extends PhoneStateListener{
	
	public void onCallStateChanged(int state, String incomingNumber){
		
		  switch(state){
		    case TelephonyManager.CALL_STATE_IDLE:
		    	break;
		    case TelephonyManager.CALL_STATE_OFFHOOK:
		    	break;
		    case TelephonyManager.CALL_STATE_RINGING:
		    	if(Settings.ifCall()){
		    		if(Settings.getMethod().equals("GET")){
		    			DataProgressGET nCmd = new DataProgressGET();
		    			nCmd.execute(Settings.getName(incomingNumber), incomingNumber);		    		
		    		}else{
		    			DataProgressPOST nCmd = new DataProgressPOST();
		    			nCmd.execute(Settings.getName(incomingNumber), incomingNumber);		    		

		    		}
		    	}
		    	break;
		    }
		  }
}
