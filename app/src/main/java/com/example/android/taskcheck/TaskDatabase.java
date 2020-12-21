package com.example.android.taskcheck;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * The Database is accessed at two endpoints in the app: first, when the app is launched,
 * all tasks in database are fetched, and the database is cleared.
 * Second, when the app loses focus (user exits or presses recent button), the list of tasks
 * is stored in the database
 */
@Database(entities = {TaskData.class}, version = 4)
public abstract class TaskDatabase extends RoomDatabase {
	public abstract TaskDao taskDao();
}
