package net.enib.mymorse;

import net.enib.mymorse.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

public class ParametresActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	ListPreference dureePointList;
	CheckBoxPreference sonCheckbox;
	CheckBoxPreference vibreurCheckbox;
	CheckBoxPreference flashCheckbox;
	
	SharedPreferences sharedPreferences;
	
	final String DUREEPOINTLISTKEY = "dureePointListKey";
	final String SONCHECKBOXKEY = "sonCheckBox";
	final String VIBREURCHECKBOXKEY = "vibreurCheckBox";
	final String FLASHCHECKBOXKEY = "flashCheckBox";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_parametres);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.contains(DUREEPOINTLISTKEY);
		dureePointList = (ListPreference) findPreference(DUREEPOINTLISTKEY);
		sonCheckbox = (CheckBoxPreference) findPreference(SONCHECKBOXKEY);
		vibreurCheckbox = (CheckBoxPreference) findPreference(VIBREURCHECKBOXKEY);
		flashCheckbox = (CheckBoxPreference) findPreference(FLASHCHECKBOXKEY);
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		dureePointList.setSummary(sharedPreferences.getString(DUREEPOINTLISTKEY, "") + " ms");
		sonCheckbox.setChecked(sharedPreferences.getBoolean(SONCHECKBOXKEY, false));
		sonCheckbox.setSummary(sharedPreferences.getBoolean(SONCHECKBOXKEY, false) ? "Son activé" : "Son désactivé");
		vibreurCheckbox.setChecked(sharedPreferences.getBoolean(VIBREURCHECKBOXKEY, false));
		vibreurCheckbox.setSummary(sharedPreferences.getBoolean(VIBREURCHECKBOXKEY, false) ? "Vibreur activé" : "Vibreur désactivé");
		flashCheckbox.setChecked(sharedPreferences.getBoolean(FLASHCHECKBOXKEY, false));
		flashCheckbox.setSummary(sharedPreferences.getBoolean(FLASHCHECKBOXKEY, false) ? "Flash activé" : "Flash désactivé");
		
		//Log.d("ParametreActivity", "Start");
		
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		//Log.d("ParametreActivity", "Preference Change");
	    if (key.equals(DUREEPOINTLISTKEY)) {
	    	dureePointList.setSummary(sharedPreferences.getString(key, "Error") + " ms");
	    } else if (key.equals(SONCHECKBOXKEY)) {
	    	sonCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Son activé" : "Son désactivé");
	    } else if (key.equals(VIBREURCHECKBOXKEY)) {
	    	vibreurCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Vibreur activé" : "Vibreur désactivé");
	    } else if (key.equals(FLASHCHECKBOXKEY)) {
	    	flashCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Flash activé" : "Flash désactivé");
	    }
	}
}
