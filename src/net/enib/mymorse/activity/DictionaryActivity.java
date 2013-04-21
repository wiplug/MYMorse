package net.enib.mymorse.activity;

import net.enib.mymorse.R;
import net.enib.mymorse.controller.InterfacesController;
import net.enib.mymorse.controller.LatinMorseConverter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class DictionaryActivity extends Activity {
	
	private InterfacesController interfaceController;
	private Intent intentParametresActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);
		interfaceController = new InterfacesController(this);
		intentParametresActivity = new Intent(DictionaryActivity.this, ParametersActivity.class);
		LinearLayout dictionaryLinearLayout = (LinearLayout) this.findViewById(R.id.dictionaryLinearLayout);
		for (int i = 0 ; i < LatinMorseConverter.ALPHABET_TABLE.length ;i+=2){
			Button letter = new Button(this);
			letter.setText(LatinMorseConverter.ALPHABET_TABLE[i] + " : " + LatinMorseConverter.ALPHABET_TABLE[i+1]);
			letter.setOnClickListener(new OnClickListener() { 
				  @Override
				  public void onClick(View v) 
				  {
					  String s = ((Button)v).getText().toString();
					  s = s.replaceAll("\\s", "");
					  String sTable[] = s.split(":");
					  interfaceController.playMorse(sTable[1]);
				  }    
				});
			dictionaryLinearLayout.addView(letter);
		}
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        interfaceController.onResume();
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        interfaceController.onPause();
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
        return false;
     }
    
    public void startParameters(View v){
    	startActivityForResult(intentParametresActivity, 1);
    }

}
