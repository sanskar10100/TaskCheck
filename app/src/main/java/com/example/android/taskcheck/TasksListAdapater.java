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

		// This obviously isn't a very good pattern, but I couldn't think of any other patterns
		// Using another function would require passing all the data like holder and position to it,
		// ultimately defeating the purpose.
		if (MainActivity.tasks.get(position).dueDate.equals("none")) {
			holder.getTaskDueDateTextView().setText(R.string.no_due_date);
		} else {
			// Date is formatted to DD/MM/YYYY before being set
			holder.getTaskDueDateTextView().setText(Helper.reverseDateString(MainActivity.tasks.get(position).dueDate));
		}

		if (MainActivity.tasks.get(position).dueTime.equals("none")) {
			holder.getTaskDueTimeTextView().setText(R.string.no_due_time);
		} else {
			holder.getTaskDueTimeTextView().setText(MainActivity.tasks.get(position).dueTime);
		}

		if (MainActivity.tasks.get(position).taskPriority.equals("none")) {
			holder.getTaskPriority().setText(R.string.no_task_priority_assigned);
		} else {
			holder.getTaskPriority().setText(MainActivity.tasks.get(position).taskPriority);
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
		private final TextView taskDueTimeTextView;
		private final TextView taskPriority;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			taskDescriptionTextView = itemView.findViewById(R.id.text_view_single_task_description);
			taskDueTimeTextView = itemView.findViewById(R.id.task_due_time);
			taskDueDateTextView = itemView.findViewById(R.id.task_due_date);
			taskPriority = itemView.findViewById(R.id.task_priority);
		}

		public TextView getTextView() {
			return taskDescriptionTextView;
		}

		public TextView getTaskDueDateTextView() {
			return taskDueDateTextView;
		}

		public TextView getTaskDueTimeTextView() {
			return taskDueTimeTextView;
		}

		public TextView getTaskPriority() {
			return taskPriority;
		}
	}
}
