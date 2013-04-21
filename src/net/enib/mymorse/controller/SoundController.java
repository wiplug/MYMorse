package net.enib.mymorse.controller;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundController implements InterfaceControllerInterface {

	private int soundId;
	private boolean loaded = false;
	private AssetManager assetManager;
	private SoundPool soundPool;
	public SoundController(AssetManager asset){
		assetManager = asset;
		 soundPool= new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		
		AssetFileDescriptor descriptor=null;
		try {
			descriptor = assetManager.openFd("morse_court.mp3");
			
			soundId = soundPool.load(descriptor, 1);
			
			soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			    public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
			       loaded = true;
			       
			    }
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void turnOn(){
		soundPool.play(soundId, 1, 1, 0, 0, 1);
	}

	@Override
	public void turnOff(){
		
	}
}
