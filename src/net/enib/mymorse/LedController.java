package net.enib.mymorse;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class LedController extends AbstractInterfaceController {

	Camera mCamera;
	
	public LedController(){
		super();
	}
	
	@Override
	public void pointOn(int pointTime) {
		safeCameraOpen(0);
		try{
			Thread.sleep(pointTime*POINT_UNIT);
		} catch (Exception e){
			e.printStackTrace();
		}
		releaseCameraAndPreview();
	}

	@Override
	public void traitOn(int pointTime) {
		safeCameraOpen(0);
		try{
			Thread.sleep(pointTime*TRAIT_UNIT);
		} catch (Exception e){
			e.printStackTrace();
		}
		releaseCameraAndPreview();
	}
	
	private boolean safeCameraOpen(int id) {
	    boolean qOpened = false;
	  
	    try {
	        releaseCameraAndPreview();
	        mCamera = Camera.open(id);
	        Parameters p = mCamera.getParameters();
			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(p);
			mCamera.startPreview();
	        qOpened = (mCamera != null);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return qOpened;    
	}

	private void releaseCameraAndPreview() {
	    //mPreview.setCamera(null);
	    if (mCamera != null) {
	    	mCamera.stopPreview();
	        mCamera.release();
	        mCamera = null;
	    }
	}

}
