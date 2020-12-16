package com.example.android.taskcheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksListAdapater extends RecyclerView.Adapter<TasksListAdapater.ViewHolder> {

	// Boilerplate code for Adapter, explanation not necessary

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_view, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.getTextView().setText(MainActivity.tasks.get(position).taskDescription);

		if (MainActivity.tasks.get(position).dueDate.equals("0/0/0")) {
			holder.getTaskDueDateTextView().setText(R.string.no_due_date);
		} else {
			holder.getTaskDueDateTextView().setText(MainActivity.tasks.get(position).dueDate);
		}

		// Long click deletes the task from the list and displays a toast
		holder.itemView.setOnLongClickListener(view -> {
			MainActivity.tasks.remove(position);
			notifyDataSetChanged();
			Toast.makeText(holder.itemView.getContext(), "Task completed!", Toast.LENGTH_SHORT).show();
			return true;
		});
	}

	@Override
	public int getItemCount() {
		return MainActivity.tasks.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView taskDescriptionTextView;
		private final TextView taskDueDateTextView;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			taskDescriptionTextView = itemView.findViewById(R.id.text_view_single_task_description);
			taskDueDateTextView = itemView.findViewById(R.id.task_due_date);
		}

		public TextView getTextView() {
			return taskDescriptionTextView;
		}

		public TextView getTaskDueDateTextView() {
			return taskDueDateTextView;
		}
	}
}
