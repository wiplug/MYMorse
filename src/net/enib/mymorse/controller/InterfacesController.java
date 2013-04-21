package net.enib.mymorse.controller;

import net.enib.mymorse.R;
import net.enib.mymorse.activity.ConverterActivity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

public class InterfacesController implements InterfaceControllerInterface {
	
	private ConverterActivity parent;
	
	private LedController ledController = null;
	private VibratorController vibratorController = null;
	private SoundController soundController = null;
	private SharedPreferences sharedPreferences;
	
	private String SONCHECKBOXKEY;
	private String VIBREURCHECKBOXKEY;
	private String FLASHCHECKBOXKEY;
	private String DUREEPOINTLISTKEY;
	
	final private boolean DEFAULT_SOUND = true;
	final private boolean DEFAULT_VIBRATOR = true;
	final private boolean DEFAULT_LED = false;
	final private String DEFAULT_TIME = "200";
	
	private final int POINT_UNIT = 1;
	private final int TRAIT_UNIT = 3;
	private final int SLASH_UNIT = 7;
	
	private String morseString;
	
	private PlayMorseAsyncTask playThread = null;
	
	public InterfacesController(ConverterActivity a){
		
		parent = a;
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
		
		SONCHECKBOXKEY = a.getString(R.string.SONCHECKBOXKEY);
		VIBREURCHECKBOXKEY = a.getString(R.string.VIBREURCHECKBOXKEY);
		FLASHCHECKBOXKEY = a.getString(R.string.FLASHCHECKBOXKEY);
		DUREEPOINTLISTKEY = a.getString(R.string.DUREEPOINTLISTKEY);
		
		playThread = new PlayMorseAsyncTask();
		
		ledController = new LedController();
		vibratorController = new VibratorController(a);
		soundController = new SoundController(parent.getAssets());
	}
	
	public void playMorse(String morseString){
		this.morseString = morseString;
		playThread.stopThread();
		parent.setMorseProgress(0);
		playThread = new PlayMorseAsyncTask();
		playThread.execute(morseString);
		
	}
	
	public void onPause(){
		if(playThread != null){
			playThread.stopThread();
		}
		while(playThread.stop && playThread.getStatus()==AsyncTask.Status.RUNNING){}
		ledController.releaseCameraAndPreview();
		parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
	}
	
	public void onResume(){
		ledController.initCamera(0);
	}
	
	@Override
	public void turnOn() {
		if(isFlashEnabled()){
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
		ledController.turnOff();
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
		return sharedPreferences.getBoolean(SONCHECKBOXKEY, DEFAULT_SOUND);
	}
	
	private boolean isVibreurEnabled(){
		return sharedPreferences.getBoolean(VIBREURCHECKBOXKEY, DEFAULT_VIBRATOR);
	}
	
	private boolean isFlashEnabled(){
		return sharedPreferences.getBoolean(FLASHCHECKBOXKEY, DEFAULT_LED);
	}
	
	private int getDureePoint(){
		return Integer.parseInt(sharedPreferences.getString(DUREEPOINTLISTKEY, DEFAULT_TIME));
	}
	
	private class PlayMorseAsyncTask extends AsyncTask<String, Void, Void>{
	
		private boolean stop = false;
		
		public void stopThread(){
			stop=true;
		}

		@Override
		protected Void doInBackground(String... params) {
			parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			int pointTime = getDureePoint();
			for (int i=0; i<(morseString.length()); i++){
				if (stop){
					break;
				}
				String s = String.valueOf(params[0].charAt(i));
				if(s.equalsIgnoreCase(".")){
					pointOn(pointTime);
				} else if(s.equalsIgnoreCase("_")){
					traitOn(pointTime);
				} else if(s.equalsIgnoreCase(" ")){
					espace(pointTime);
				} else if(s.equalsIgnoreCase("/")){
					slash(pointTime);
				}
				parent.setMorseProgress(100*(i+1)/(morseString.length()));
				try{
					Thread.sleep(pointTime*POINT_UNIT);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			stop=false;
			parent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
			return null;
		}
	}
	
}
