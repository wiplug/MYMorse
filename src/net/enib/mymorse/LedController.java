package net.enib.mymorse;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class LedController implements InterfaceControllerInterface {

	private Camera mCamera;
	
	public LedController(){
		
	}
	
	private void safeCameraOpen(int id) {
	    try {
	        releaseCameraAndPreview();
	        mCamera = Camera.open(id);
	        Parameters p = mCamera.getParameters();
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(p);
			mCamera.startPreview();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void releaseCameraAndPreview() {
	    if (mCamera != null) {
	    	mCamera.stopPreview();
	        mCamera.release();
	        mCamera = null;
	    }
	}
	
	@Override
	public void turnOn() {
		safeCameraOpen(0);
	}

	@Override
	public void turnOff() {
		releaseCameraAndPreview();
	}

}
