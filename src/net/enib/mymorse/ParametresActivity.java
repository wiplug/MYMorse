package net.enib.mymorse;

import net.enib.mymorse.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Window;

public class ParametresActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	private ListPreference dureePointList;
	private CheckBoxPreference sonCheckbox;
	private CheckBoxPreference vibreurCheckbox;
	private CheckBoxPreference flashCheckbox;
	
	private SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_parametres);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.contains(getString(R.string.DUREEPOINTLISTKEY));
		dureePointList = (ListPreference) findPreference(getString(R.string.DUREEPOINTLISTKEY));
		sonCheckbox = (CheckBoxPreference) findPreference(getString(R.string.SONCHECKBOXKEY));
		vibreurCheckbox = (CheckBoxPreference) findPreference(getString(R.string.VIBREURCHECKBOXKEY));
		flashCheckbox = (CheckBoxPreference) findPreference(getString(R.string.FLASHCHECKBOXKEY));
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		dureePointList.setSummary(sharedPreferences.getString(getString(R.string.DUREEPOINTLISTKEY), "") + " ms");
		sonCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.SONCHECKBOXKEY), false));
		sonCheckbox.setSummary(sharedPreferences.getBoolean(getString(R.string.SONCHECKBOXKEY), false) ? "Son activé" : "Son désactivé");
		vibreurCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.VIBREURCHECKBOXKEY), false));
		vibreurCheckbox.setSummary(sharedPreferences.getBoolean(getString(R.string.VIBREURCHECKBOXKEY), false) ? "Vibreur activé" : "Vibreur désactivé");
		flashCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false));
		flashCheckbox.setSummary(sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false) ? "Flash activé" : "Flash désactivé");
		
		//Log.d("ParametreActivity", "Start");
		
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		//Log.d("ParametreActivity", "Preference Change");
	    if (key.equals(getString(R.string.DUREEPOINTLISTKEY))) {
	    	dureePointList.setSummary(sharedPreferences.getString(key, "Error") + " ms");
	    } else if (key.equals(getString(R.string.SONCHECKBOXKEY))) {
	    	sonCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Son activ�" : "Son d�sactiv�");
	    } else if (key.equals(getString(R.string.VIBREURCHECKBOXKEY))) {
	    	vibreurCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Vibreur activ�" : "Vibreur d�sactiv�");
	    } else if (key.equals(getString(R.string.FLASHCHECKBOXKEY))) {
	    	flashCheckbox.setSummary(sharedPreferences.getBoolean(key, false) ? "Flash activ�" : "Flash d�sactiv�");
	    }
	}
}
