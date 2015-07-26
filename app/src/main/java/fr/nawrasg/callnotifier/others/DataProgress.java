package fr.nawrasg.callnotifier.others;

import android.os.AsyncTask;
import fr.nawrasg.callnotifier.App;

public abstract class DataProgress extends AsyncTask<String, Void, String>{
	private CheckConnection nIC;
	
	protected String mURL;
	
	public DataProgress(){
		nIC = new CheckConnection(App.getContext());
		mURL = Settings.getURL();
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(nIC.checkConnection() == CheckConnection.TYPE_NOT_CONNECTED){
			this.cancel(true);
		}
	}
}
