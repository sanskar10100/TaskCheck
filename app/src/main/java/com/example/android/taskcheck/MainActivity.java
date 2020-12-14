package com.example.android.taskcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	static List<String> tasks;
	TasksListAdapater adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		int size = sharedPreferences.getInt("size_of_list", 0);
		boolean displayIntro = sharedPreferences.getBoolean("display_intro", true);
		tasks = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			tasks.add(sharedPreferences.getString(String.valueOf(i), ""));
		}
		sharedPreferences.edit().clear().apply();

		// Fetch and set current date
		TextView date = findViewById(R.id.text_view_date);
		String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		date.setText(currentDate);

		adapter = new TasksListAdapater();

		// Generate and bind recyclerView
		RecyclerView recyclerView = findViewById(R.id.recycler_view_tasks);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

		if (displayIntro) {
			Snackbar.make(findViewById(R.id.parent_view), "Long click on a task to mark as completed!", Snackbar.LENGTH_LONG)
					.setAction("CLOSE", view -> {
					})
					.setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
					.show();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		if (tasks.size() > 0) {
			editor.putInt("size_of_list", tasks.size());
			for (int i = 0; i < tasks.size(); i++) {
				editor.putString(String.valueOf(i), tasks.get(i));
			}
		}
		editor.putBoolean("display_intro", false);
		editor.apply();
	}

	/**
	 * Adds a task to the current list and notifies recyclerView of dataset change
	 */
	public void addTask(View view) {
		EditText editTextTaskDescription = findViewById(R.id.edit_text_task_description);
		String taskDescription = editTextTaskDescription.getText().toString();
		if (taskDescription.length() != 0) {
			tasks.add(taskDescription);
			editTextTaskDescription.setText("");
			adapter.notifyDataSetChanged();
		}
	}
}