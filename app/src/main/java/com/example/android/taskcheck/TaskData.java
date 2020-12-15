package com.example.android.taskcheck;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_data")
public class TaskData {

	@PrimaryKey(autoGenerate = true)
	public int id;

	@ColumnInfo(name = "task_description")
	public String taskDescription;

	public TaskData(int id, String taskDescription) {
		this.id = id;
		this.taskDescription = taskDescription;
	}
}
