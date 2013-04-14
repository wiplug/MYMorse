package net.enib.mymorse;

import net.enib.mymorse.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ParametresActivity extends FragmentActivity {
	
	boolean son;
	boolean vibreur;
	boolean flash;
	
	public ParametresActivity(){
		son = true;
		vibreur = true;
		flash = true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parametres);
		((CheckBox) findViewById(R.id.sonCheckbox)).setChecked(son);
		((CheckBox) findViewById(R.id.vibreurCheckbox)).setChecked(vibreur);
		((CheckBox) findViewById(R.id.flashCheckbox)).setChecked(flash);
		Log.d("ParametreActivity", "Start");
	}
	
	public void pointTime(View view) {
		//Toast.makeText(ParametresActivity.this, R.string.parametres, Toast.LENGTH_SHORT).show();
		//Log.d("ParametreActivity", "Durée d'un point");
		FragmentManager fm = getSupportFragmentManager();
        NumberPickerDialog npd = new NumberPickerDialog();
        npd.show(fm, "fragment_edit_name");
	}
	
	public void isVibreur(View view) {
		//Toast.makeText(ParametresActivity.this, R.string.vibreur, Toast.LENGTH_SHORT).show();
		//Log.d("ParametreActivity", "Vibreur");
		this.vibreur = ((CheckBox) view.findViewById(R.id.vibreurCheckbox)).isChecked();
	}
	
	public void isSon(View view) {
		//Toast.makeText(ParametresActivity.this, R.string.son, Toast.LENGTH_SHORT).show();
		//Log.d("ParametreActivity", "Son");
		this.son = ((CheckBox) view.findViewById(R.id.sonCheckbox)).isChecked();
	}
	
	public void isFlash(View view) {
		//Toast.makeText(ParametresActivity.this, R.string.flash, Toast.LENGTH_SHORT).show();
		//Log.d("ParametreActivity", "Flash");
		this.flash = ((CheckBox) view.findViewById(R.id.flashCheckbox)).isChecked();
	}
	
}
