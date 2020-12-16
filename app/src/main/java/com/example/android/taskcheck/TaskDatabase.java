package com.example.android.taskcheck;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskData.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase {
	public abstract TaskDao taskDao();
}
