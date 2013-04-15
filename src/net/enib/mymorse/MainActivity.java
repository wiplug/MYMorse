package net.enib.mymorse;

import net.enib.mymorse.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private Intent intentNameActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		intentNameActivity = new Intent(MainActivity.this, ParametresActivity.class);
		setContentView(R.layout.activity_main);
		LatinMorseConverter morseConverter = new LatinMorseConverter();
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
        	   startActivityForResult(intentNameActivity, 1);
              //Toast.makeText(MainActivity.this, R.string.parametres, Toast.LENGTH_SHORT).show();
              return true;
          case R.id.quitter:
              finish();
              return true;
        }
        return false;}
    
    public void swapTitle(View v){
    	//Log.d("MainActivity", "swapTitle");
    	TextView title = (TextView) v.findViewById(R.id.title);
    	if(title.getText().toString().equalsIgnoreCase(getString(R.string.MYMorse))){
    		title.setText(R.string.mMYMorse);
    	} else {
    		title.setText(R.string.MYMorse);
    	}
    }
}
