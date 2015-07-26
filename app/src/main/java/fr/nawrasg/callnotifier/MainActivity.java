package fr.nawrasg.callnotifier;

import android.app.Activity;
import android.os.Bundle;
import fr.nawrasg.callnotifier.fragments.PrefFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, new PrefFragment(), "main")
				.commit();
	}
	
}
