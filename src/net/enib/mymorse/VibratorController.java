package net.enib.mymorse;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class VibratorController extends AbstractInterfaceController {

	private Vibrator mVibrator;
	
	public VibratorController(Activity a){
		super();
		mVibrator = (Vibrator) a.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public boolean hasVibrator(){
		return mVibrator.hasVibrator();
	}
	
	@Override
	public void pointOn(int pointTime) {
		mVibrator.vibrate(pointTime*POINT_UNIT);
	}

	@Override
	public void traitOn(int pointTime) {
		mVibrator.vibrate(pointTime*TRAIT_UNIT);
	}

}
