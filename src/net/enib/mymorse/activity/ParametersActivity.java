package net.enib.mymorse.activity;

import net.enib.mymorse.R;
import net.enib.mymorse.R.layout;
import net.enib.mymorse.R.string;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Window;

public class ParametersActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{

	private ListPreference dureePointList;
	private CheckBoxPreference sonCheckbox;
	private CheckBoxPreference vibreurCheckbox;
	private CheckBoxPreference flashCheckbox;
	
	private SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_parameters);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		sharedPreferences.contains(getString(R.string.DUREEPOINTLISTKEY));
		dureePointList = (ListPreference) findPreference(getString(R.string.DUREEPOINTLISTKEY));
		sonCheckbox = (CheckBoxPreference) findPreference(getString(R.string.SONCHECKBOXKEY));
		vibreurCheckbox = (CheckBoxPreference) findPreference(getString(R.string.VIBREURCHECKBOXKEY));
		flashCheckbox = (CheckBoxPreference) findPreference(getString(R.string.FLASHCHECKBOXKEY));
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		dureePointList.setSummary(sharedPreferences.getString(getString(R.string.DUREEPOINTLISTKEY), "") + " ms");
		sonCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.SONCHECKBOXKEY), false));
		sonCheckbox.setSummary(getString(R.string.son) + " "+ (sharedPreferences.getBoolean(getString(R.string.SONCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
		vibreurCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.VIBREURCHECKBOXKEY), false));
		vibreurCheckbox.setSummary(getString(R.string.vibreur) + " "+ (sharedPreferences.getBoolean(getString(R.string.VIBREURCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
		flashCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false));
		flashCheckbox.setSummary(getString(R.string.flash) + " "+ (sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
	}
	
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, String key) {
	    if (key.equals(getString(R.string.DUREEPOINTLISTKEY))) {
	    	dureePointList.setSummary(sharedPreferences.getString(key, "Error") + " ms");
	    } else if (key.equals(getString(R.string.SONCHECKBOXKEY))) {
	    	sonCheckbox.setSummary(getString(R.string.son) + " "+ (sharedPreferences.getBoolean(getString(R.string.SONCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
	    } else if (key.equals(getString(R.string.VIBREURCHECKBOXKEY))) {
	    	vibreurCheckbox.setSummary(getString(R.string.vibreur) + " "+ (sharedPreferences.getBoolean(getString(R.string.VIBREURCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
	    } else if (key.equals(getString(R.string.FLASHCHECKBOXKEY))) {
	    	if(sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false)){
	    		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	    		alertDialog.setTitle(getString(R.string.flash));
	    		alertDialog.setMessage(getString(R.string.confirmFlash));
	    		alertDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                	SharedPreferences.Editor editor = sharedPreferences.edit();
	                	editor.putBoolean(getString(R.string.FLASHCHECKBOXKEY), true);
	                	editor.apply();
	                	updateLedCheckBox();
	                }
	            });
	    		alertDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
	                 
	                public void onClick(DialogInterface dialog, int which) {
	                	SharedPreferences.Editor editor = sharedPreferences.edit();
	                	editor.putBoolean(getString(R.string.FLASHCHECKBOXKEY), false);
	                	editor.apply();
	                	updateLedCheckBox();
	                }
	            });
	    		alertDialog.setCancelable(false);
	    		alertDialog.show();
	    	} else {
	    		updateLedCheckBox();
	    	}
	    }
	}
	
	private void updateLedCheckBox(){
		flashCheckbox.setSummary(getString(R.string.flash) + " "+ (sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false) ? getString(R.string.active) : getString(R.string.desactive)));
    	flashCheckbox.setChecked(sharedPreferences.getBoolean(getString(R.string.FLASHCHECKBOXKEY), false));
	}
}
