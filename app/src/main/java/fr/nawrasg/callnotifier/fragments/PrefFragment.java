package fr.nawrasg.callnotifier.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

import fr.nawrasg.callnotifier.AboutActivity;
import fr.nawrasg.callnotifier.R;

public class PrefFragment extends PreferenceFragment{
	private Preference mAbout;
	private CheckBoxPreference mAtlantis;
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		mContext = getActivity();
		mAbout = findPreference("about");
		mAbout.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				startActivity(new Intent(mContext, AboutActivity.class));
				return true;
			}
		});
		mAtlantis = (CheckBoxPreference) findPreference("atlantis");
		if(!checkAtlantis()){
			mAtlantis.setEnabled(false);
		}
		mAtlantis.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				return false;
			}
		});
	}

	private boolean checkAtlantis(){
		PackageManager nPM = mContext.getPackageManager();
		try {
			nPM.getPackageInfo("fr.nawrasg.atlantis", PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
}
