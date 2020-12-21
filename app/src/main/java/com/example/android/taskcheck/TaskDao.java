package com.example.android.taskcheck;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

	/**
	 * @return the list of tasks fetched from the Room database
	 */
	@Query("SELECT * FROM task_data")
	List<TaskData> getAll();

	/**
	 * @param taskData the list of tasks to be saved in the Room database
	 */
	@Insert
	void insertAll(List<TaskData> taskData);
}
