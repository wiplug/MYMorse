package net.enib.mymorse.activity;

import net.enib.mymorse.R;
import net.enib.mymorse.controller.InterfacesController;
import net.enib.mymorse.controller.LatinMorseConverter;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ConverterActivity extends Activity {

	private LatinMorseConverter morseConverter;
	
	private EditText textField;
	private EditText morseField;
	private ProgressBar progressBar;
	
	private TextWatcher textTextWatcher;
	private TextWatcher morseTextWatcher;
	private InterfacesController interfaceController;
	
	private Intent intentParametresActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_converter);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		intentParametresActivity = new Intent(ConverterActivity.this, ParametersActivity.class);
		morseConverter = new LatinMorseConverter();
		interfaceController = new InterfacesController(this);
		textField = (EditText) this.findViewById(R.id.editText);
		morseField = (EditText) this.findViewById(R.id.editMorse);
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
		initTextWatcher();
		morseField.addTextChangedListener(morseTextWatcher);
		textField.addTextChangedListener(textTextWatcher);
		
		InputFilter alphaNumericFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence arg0, int arg1, int arg2, Spanned arg3, int arg4, int arg5)
            {
                 for (int k = arg1; k < arg2; k++) {
                	 if (!(Character.toString(arg0.charAt(k)).matches("[a-zA-Z0-9 ]"))) {
                		 return "";
                	 }
                 }
                 return null;
            }
		};
		
		InputFilter pointUnderscore = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence arg0, int arg1, int arg2, Spanned arg3, int arg4, int arg5)
            {
                 for (int k = arg1; k < arg2; k++) {
                	 if (!(Character.toString(arg0.charAt(k)).matches("[_./ ]"))) {
                		 return "";
                	 }
                 }
                 return null;
            }
		};
		textField.setFilters(new InputFilter[]{ alphaNumericFilter});
		morseField.setFilters(new InputFilter[]{ pointUnderscore});
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
	
	private void initTextWatcher(){
		textTextWatcher = new TextWatcher() {
			 
			public void afterTextChanged(Editable s) {
				morseField.addTextChangedListener(morseTextWatcher);
			}
			
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		    	morseField.removeTextChangedListener(morseTextWatcher);
		    }
		 
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	morseField.setText(morseConverter.latinToMorseString(s.toString()));
		    }
		};
		
		morseTextWatcher = new TextWatcher() {
			 
			public void afterTextChanged(Editable s) {
				textField.addTextChangedListener(textTextWatcher);
			}
			
		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		    	textField.removeTextChangedListener(textTextWatcher);
		    }
		 
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	textField.setText(morseConverter.morseToLatinString(s.toString()));
		    }
		};
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
	
	public void playMorse(View v){
		interfaceController.playMorse(morseField.getText().toString());
	}
	
	public void setMorseProgress(int progress){
		progressBar.setProgress(progress);
	}
}
