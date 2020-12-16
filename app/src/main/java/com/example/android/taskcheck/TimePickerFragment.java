package com.example.android.taskcheck;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute, false);
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onTimeSet(TimePicker timePicker, int i, int i1) {
		if (i < 12) {
			MainActivity.dueTime = ((i == 0) ? 12 : i) + ":" + String.format("%02d", i1) + " AM";
		} else {
			MainActivity.dueTime = ((i == 12) ? 12 : i - 12) + ":" + String.format("%02d", i1) + " PM";
		}
	}
}