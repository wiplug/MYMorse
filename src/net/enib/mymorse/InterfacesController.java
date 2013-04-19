package net.enib.mymorse;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

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
		
		//if(a.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
		ledController = new LedController();
		vibratorController = new VibratorController(a);
		soundController = new SoundController();
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
	}
	
	public void onResume(){
		ledController.initCamera(0);
	}
	
	@Override
	public void turnOn() {
		if(isFlashEnabled()){
			ledController.turnOn();
			Log.d("InterfaceController", "led on");
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
	
	private class PlayMorseAsyncTask extends AsyncTask<String, Void, Void>{
	
		private boolean stop = false;
		
		public void stopThread(){
			stop=true;
		}

		@Override
		protected Void doInBackground(String... params) {
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
			return null;
		}
	}
	
}
