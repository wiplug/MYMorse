package net.enib.mymorse.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class VibratorController implements InterfaceControllerInterface {

	private Vibrator mVibrator;
	private final int MAX_VIBRATE = 10000;
	
	public VibratorController(Activity a){
		mVibrator = (Vibrator) a.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public boolean hasVibrator(){
		return mVibrator.hasVibrator();
	}

	@Override
	public void turnOn(){
		mVibrator.vibrate(MAX_VIBRATE);
	}

	@Override
	public void turnOff(){
		mVibrator.cancel();
	}
}
