package net.enib.mymorse;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class LedController implements InterfaceControllerInterface {

	private Camera mCamera;
	
	public LedController(){
	}
	
	public void initCamera(int id){
		 try {
		        releaseCameraAndPreview();
		        mCamera = Camera.open(id);
		        Parameters p = mCamera.getParameters();
				p.setFlashMode(Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(p);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void turnOn() {
	        Parameters p = mCamera.getParameters();
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(p);
	}
	
	

	public void releaseCameraAndPreview() {
	    if (mCamera != null) {
	    	mCamera.stopPreview();
	        mCamera.release();
	        mCamera = null;
	    }
	}

	@Override
	public void turnOff() {
		Parameters p = mCamera.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		mCamera.setParameters(p);
	}

}
