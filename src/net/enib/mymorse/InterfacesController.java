package net.enib.mymorse;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

public class InterfacesController extends AbstractInterfaceController /*implements Runnable*/ {

	private LedController ledController = null;
	private VibratorController vibratorController = null;
	private SoundController soundController = null;
	SharedPreferences sharedPreferences;
	
	private String SONCHECKBOXKEY;
	private String VIBREURCHECKBOXKEY;
	private String FLASHCHECKBOXKEY;
	private String DUREEPOINTLISTKEY;
	
	public InterfacesController(Activity a){
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
		
		SONCHECKBOXKEY = a.getString(R.string.SONCHECKBOXKEY);
		VIBREURCHECKBOXKEY = a.getString(R.string.VIBREURCHECKBOXKEY);
		FLASHCHECKBOXKEY = a.getString(R.string.FLASHCHECKBOXKEY);
		DUREEPOINTLISTKEY = a.getString(R.string.DUREEPOINTLISTKEY);
		
		if(a.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			ledController = new LedController();
		}
		vibratorController = new VibratorController(a);
		soundController = new SoundController();
	}
	
	public void playMorse(String morseString){
		int pointTime = getDureePoint();
		for (int i=0; i<(morseString.length()); i++){
			String s = String.valueOf(morseString.charAt(i));
			if(s.equalsIgnoreCase(".")){
				pointOn(pointTime);
			} else if(s.equalsIgnoreCase("_")){
				traitOn(pointTime);
			} else if(s.equalsIgnoreCase(" ")){
				espace(pointTime);
			} else if(s.equalsIgnoreCase("/")){
				slash(pointTime);
			}
		}
	}
	
	@Override
	public void pointOn(int pointTime) {
		if(ledController != null && isFlashEnabled()){
			ledController.pointOn(pointTime);
		}
		if(isSonEnabled()){
			soundController.pointOn(pointTime);
		}
		if(vibratorController.hasVibrator() && isVibreurEnabled()){
			vibratorController.pointOn(pointTime);
		}
	}

	@Override
	public void traitOn(int pointTime) {
		if(ledController != null && isFlashEnabled()){
			ledController.traitOn(pointTime);
		}
		if(isSonEnabled()){
			soundController.traitOn(pointTime);
		}
		if(isVibreurEnabled()){
			vibratorController.traitOn(pointTime);
		}
	}
	
	private boolean isSonEnabled(){
		return sharedPreferences.getBoolean(SONCHECKBOXKEY, false);
	}
	
	private boolean isVibreurEnabled(){
		return sharedPreferences.getBoolean(VIBREURCHECKBOXKEY, false);
	}
	
	private boolean isFlashEnabled(){
		return sharedPreferences.getBoolean(FLASHCHECKBOXKEY, false);
	}
	
	private int getDureePoint(){
		return Integer.parseInt(sharedPreferences.getString(DUREEPOINTLISTKEY, ""));
	}
	
}
