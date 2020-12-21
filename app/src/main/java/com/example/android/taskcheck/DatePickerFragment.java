package com.example.android.taskcheck;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	/**
	 * Sets default values in DatePicker.
	 */
	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}


	/**
	 * Sets due date for a task in this format: "YYYY/MM/DD"
	 *
	 * @param i  year
	 * @param i1 month
	 * @param i2 day
	 */
	@Override
	public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
		MainActivity.dueDate =
				i +
						"/" + String.format(Locale.getDefault(), "%02d", (i1 + 1)) +
						"/" + String.format(Locale.getDefault(), "%02d", i2);

	}
}