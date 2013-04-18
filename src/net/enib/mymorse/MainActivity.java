package net.enib.mymorse;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		intentParametresActivity = new Intent(MainActivity.this, ParametresActivity.class);
		intentConverterActivity = new Intent(MainActivity.this, ConverterActivity.class);
		setContentView(R.layout.activity_main);
		//Log.d("MainActivity", "Start");
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
              finish();
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
}
