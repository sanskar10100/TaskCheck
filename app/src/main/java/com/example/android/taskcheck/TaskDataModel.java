package com.example.android.taskcheck;

public class TaskDataModel {
	private final String taskDescription;

	TaskDataModel(final String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskDescription() {
		return taskDescription;
	}
}
