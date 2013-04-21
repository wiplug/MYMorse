package net.enib.mymorse.activity;

import net.enib.mymorse.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private Intent intentParametresActivity;
	private Intent intentConverterActivity;
	private Intent intentAboutUsActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intentParametresActivity = new Intent(MainActivity.this, ParametersActivity.class);
		intentConverterActivity = new Intent(MainActivity.this, ConverterActivity.class);
		intentAboutUsActivity = new Intent(MainActivity.this, AboutUsActivity.class);
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
     }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           case R.id.parametres:
        	   startParameters(this.findViewById(R.id.parametres));
              return true;
          case R.id.quitter:
        	  quit(this.findViewById(R.id.QuitButton));
              return true;
        }
        return false;}
    
    public void swapTitle(View v){
    	TextView title = (TextView) v.findViewById(R.id.title);
    	if(title.getText().toString().equalsIgnoreCase(getString(R.string.MYMorse))){
    		title.setText(R.string.mMYMorse);
    		title.setSingleLine(true);
    	} else {
    		title.setText(R.string.MYMorse);
    	}
    }
    
    public void startParameters(View v){
    	startActivityForResult(intentParametresActivity, 1);
    }
    
    public void startConverter(View v){
    	startActivityForResult(intentConverterActivity, 2);
    }
    
    public void startAboutUs(View v){
    	startActivityForResult(intentAboutUsActivity, 3);
    }
    
    public void quit(View v){
    	finish();
    }
}
