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
		int size = sharedPreferences.getInt("0", 0);
		tasks = new ArrayList<>(size);
		for (int i = 1; i <= size; i++) {
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
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (tasks.size() > 0) {
			SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putInt(String.valueOf(0), tasks.size());
			for (int i = 1; i <= tasks.size(); i++) {
				editor.putString(String.valueOf(i), tasks.get(i - 1));
			}
			editor.apply();
		}
	}

	/**
	 * Adds a task to the current list and notifies recyclerView of dataset change
	 */
	public void addTask(View view) {
		EditText editTextTaskDescription = findViewById(R.id.edit_text_task_description);
		String taskDescription = editTextTaskDescription.getText().toString();
		if (taskDescription != null && taskDescription.length() != 0) {
			tasks.add(taskDescription);
			editTextTaskDescription.setText("");
			adapter.notifyDataSetChanged();
		}
	}
}