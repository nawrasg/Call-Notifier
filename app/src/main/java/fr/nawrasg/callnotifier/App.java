package fr.nawrasg.callnotifier;

import android.app.Application;
import android.content.Context;

public class App extends Application{
	private static Context nContext;
	
	public void onCreate(){
		super.onCreate();
		nContext = getApplicationContext();
	}
	
	public static Context getContext(){
		return nContext;
	}
}
