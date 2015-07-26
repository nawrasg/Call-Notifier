package fr.nawrasg.callnotifier.others;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import fr.nawrasg.callnotifier.App;

public class Settings {
	
	public static String getURL(){
		SharedPreferences nPref = PreferenceManager.getDefaultSharedPreferences(App.getContext());
		return nPref.getString("url", "");
	}
	
//	public static int getPort(Context context){
//		SharedPreferences nPref = PreferenceManager.getDefaultSharedPreferences(context);
//		return nPref.getInt("port", 80);
//	}
	
	public static boolean ifCall(){
		SharedPreferences nPref = PreferenceManager.getDefaultSharedPreferences(App.getContext());
		return nPref.getBoolean("call", false);
	}
	
	public static boolean ifSMS(){
		SharedPreferences nPref = PreferenceManager.getDefaultSharedPreferences(App.getContext());
		return nPref.getBoolean("sms", false);
	}
	
	public static String getMethod(){
		SharedPreferences nPref = PreferenceManager.getDefaultSharedPreferences(App.getContext());
		return nPref.getString("http", "GET");
	}
	
	public static String getName(String number){
		String contactName = "";
		try{
	        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
	        String[] projection = new String[] { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME };
	        String selection = null;
	        String[] selectionArgs = null;
	        String sortOrder = ContactsContract.PhoneLookup.DISPLAY_NAME+ " COLLATE LOCALIZED ASC";
	        ContentResolver cr = App.getContext().getContentResolver();
	        if(cr != null){
	            Cursor resultCur = cr.query(uri, projection, selection, selectionArgs, sortOrder);
	            if(resultCur != null){
	                while (resultCur.moveToNext()) {
	                    //contactId = resultCur.getString(resultCur.getColumnIndex(ContactsContract.PhoneLookup._ID));
	                    contactName = resultCur.getString(resultCur.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME));
	                    break;
	                }
	                resultCur.close();
	            }
	        }
	    }
	    catch(Exception e){
	    }
		return contactName;
	}
}
