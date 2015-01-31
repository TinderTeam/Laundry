package cn.fuego.misp.ui.pop;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class MispDatePicker extends DialogFragment implements
		DatePickerDialog.OnDateSetListener
{
	private MispPopWindowListener listener;
	public MispDatePicker(MispPopWindowListener listener)
	{
		this.listener = listener;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog datPicker = new DatePickerDialog(getActivity(), this, year, month, day);
		
		return datPicker;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day)
	{
		Log.d("OnDateSet", "select year:" + year + ";month:" + month + ";day:"
				+ day);
		String dataString = year+"-"+month + "-" + day;
		listener.onConfirmClick(dataString);
	}
}
