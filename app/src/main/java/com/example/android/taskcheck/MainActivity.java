package com.example.android.taskcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

		// Fetch data stored in SharedPreference file
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

		// Displays a snackbar with information on how to mark a task as completed (long click)
		// Displayed only at the first run
		if (displayIntro) {
			displayIntroSnackbar();
		}
	}

	/**
	 * Displays a snackbar to the user when launching the app for the first time or when clicked on
	 * the menu button. The snackbar display info on how to mark a task as completed
	 */
	private void displayIntroSnackbar() {
		Snackbar.make(findViewById(R.id.parent_view), "Long click on a task to mark as completed!", Snackbar.LENGTH_LONG)
				.setAction("CLOSE", view -> {
				})
				.setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
				.show();
	}

	/**
	 * Used for saving the data in the app to SharedPreferences before the app closes.
	 */
	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		// Save tasks data
		if (tasks.size() > 0) {
			editor.putInt("size_of_list", tasks.size());
			for (int i = 0; i < tasks.size(); i++) {
				editor.putString(String.valueOf(i), tasks.get(i));
			}
		}
		// Set first time launch state as false
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
		} else {
			Toast.makeText(this, "Cannot add empty Task!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@SuppressLint("NonConstantResourceId")
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_action_clear_all: {
				tasks.clear();
				adapter.notifyDataSetChanged();
				Toast.makeText(this, "Task list cleared!", Toast.LENGTH_SHORT).show();
				return true;
			}
			case R.id.redisplay_intro: {
				displayIntroSnackbar();
				return true;
			}
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}