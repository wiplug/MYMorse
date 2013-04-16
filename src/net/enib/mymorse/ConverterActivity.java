package net.enib.mymorse;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.Spanned;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;

public class ConverterActivity extends Activity {

	private LatinMorseConverter morseConverter;
	private EditText textField;
	private EditText morseField;
	private TextWatcher textTextWatcher;
	private TextWatcher morseTextWatcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		morseConverter = new LatinMorseConverter();
		setContentView(R.layout.activity_converter);
		textField = (EditText) this.findViewById(R.id.editText);
		morseField = (EditText) this.findViewById(R.id.editMorse);
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
		//Log.d("ConverterActivity", "Start");
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
}
