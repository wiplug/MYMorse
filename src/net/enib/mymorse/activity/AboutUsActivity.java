package net.enib.mymorse.activity;

import net.enib.mymorse.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class AboutUsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
	}

}
