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

	@ColumnInfo(name = "task_due_date")
	public String dueDate;

	@ColumnInfo(name = "task_due_time")
	public String dueTime;

	public TaskData(int id, String taskDescription, String dueDate, String dueTime) {
		this.id = id;
		this.taskDescription = taskDescription;
		this.dueDate = dueDate;
		this.dueTime = dueTime;
	}
}
