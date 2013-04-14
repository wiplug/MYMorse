package net.enib.mymorse;

import net.enib.mymorse.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class NumberPickerDialog extends DialogFragment {

	Context context;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    context = getActivity().getApplicationContext();
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View view = li.inflate(R.layout.number_picker_dialog, null);
	    builder.setView(view);
	    builder.setTitle(R.string.duree_point);
	    
	    
	    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	           }
	       });
	    builder.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	       });
	    
	    NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker);
	    np.setMinValue(50);
	    np.setMaxValue(500);
	    
	    return builder.create();
	}
}
