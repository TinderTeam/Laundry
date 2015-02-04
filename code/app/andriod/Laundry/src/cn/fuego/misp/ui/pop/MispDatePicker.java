package cn.fuego.misp.ui.pop;

import java.util.Calendar;
import java.util.Date;

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
	private Date date;
	public MispDatePicker(MispPopWindowListener listener,Date date)
	{
		this.listener = listener;
		this.date = date;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		final Calendar c = Calendar.getInstance();
		if(null != date)
		{
			c.setTime(date);
		}
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
		int temp = month+1;
		String dataString;
		if(temp<10)
		{
			dataString = year+"-0"+temp + "-" + day;
		}
		else
		{
			dataString =  year+"-"+temp + "-" + day;
		}
		listener.onConfirmClick(dataString);
	}
}
