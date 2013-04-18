package net.enib.mymorse;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

public class InterfacesController implements InterfaceControllerInterface {
	
	private LedController ledController = null;
	private VibratorController vibratorController = null;
	private SoundController soundController = null;
	private SharedPreferences sharedPreferences;
	
	private String SONCHECKBOXKEY;
	private String VIBREURCHECKBOXKEY;
	private String FLASHCHECKBOXKEY;
	private String DUREEPOINTLISTKEY;
	
	private final int POINT_UNIT = 1;
	private final int TRAIT_UNIT = 3;
	private final int SLASH_UNIT = 7;
	
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
			try{
				Thread.sleep(pointTime*POINT_UNIT);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void turnOn() {
		if(ledController != null && isFlashEnabled()){
			ledController.turnOn();
		}
		if(isSonEnabled()){
			soundController.turnOn();
		}
		if(vibratorController.hasVibrator() && isVibreurEnabled()){
			vibratorController.turnOn();
		}
	}

	@Override
	public void turnOff() {
		if(ledController != null){
			ledController.turnOff();
		}
		soundController.turnOff();
		vibratorController.turnOff();
	}
	
	private void pointOn(int pointTime) {
		turnOn();
		try{
			Thread.sleep(pointTime*POINT_UNIT);
		} catch (Exception e){
			e.printStackTrace();
		}
		turnOff();
	}

	private void traitOn(int pointTime) {
		turnOn();
		try{
			Thread.sleep(pointTime*TRAIT_UNIT);
		} catch (Exception e){
			e.printStackTrace();
		}
		turnOff();
	}
	
	private void espace(int pointTime) {
		try{
			Thread.sleep(pointTime*TRAIT_UNIT);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void slash(int pointTime) {
		try{
			Thread.sleep(pointTime*SLASH_UNIT);
		} catch (Exception e){
			e.printStackTrace();
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
