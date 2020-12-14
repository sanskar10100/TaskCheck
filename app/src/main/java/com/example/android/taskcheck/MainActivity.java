package com.example.android.taskcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

	static List<String> tasks = new ArrayList<>();
	TasksListAdapater adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView date = findViewById(R.id.text_view_date);
		String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		date.setText(currentDate);

		adapter = new TasksListAdapater();

		RecyclerView recyclerView = findViewById(R.id.recycler_view_tasks);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	public void addTask(View view) {
		EditText editTextTaskDescription = findViewById(R.id.edit_text_task_description);
		String taskDescription = editTextTaskDescription.getText().toString();
		tasks.add(taskDescription);
		editTextTaskDescription.setText("");
		adapter.notifyDataSetChanged();
	}
}