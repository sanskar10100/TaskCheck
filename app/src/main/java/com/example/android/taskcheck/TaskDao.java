package com.example.android.taskcheck;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

	@Query("SELECT * FROM task_data")
	List<TaskData> getAll();

	@Insert
	void insertAll(List<TaskData> taskData);
}
