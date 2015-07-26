package fr.nawrasg.callnotifier.others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConnection {
	public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    private Context mContext;
     
    public CheckConnection(Context context){
    	mContext = context;
    }
     
    public int checkConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
 
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
             
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        } 
        return TYPE_NOT_CONNECTED;
    }
     
//    public String checkConnectionString(Context context) {
//        int conn = checkConnection(context);
//        String status = null;
//        if (conn == CheckConnection.TYPE_WIFI) {
//            status = "wifi";
//        } else if (conn == CheckConnection.TYPE_MOBILE) {
//            status = "mobile";
//        } else if (conn == CheckConnection.TYPE_NOT_CONNECTED) {
//            status = "NA";
//        }
//        return status;
//    }
}
