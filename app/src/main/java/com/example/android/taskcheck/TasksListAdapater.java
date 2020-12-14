package com.example.android.taskcheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksListAdapater extends RecyclerView.Adapter<TasksListAdapater.ViewHolder> {

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView textView;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			textView = itemView.findViewById(R.id.text_view_single_task_description);
		}

		public TextView getTextView() {
			return textView;
		}
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_view, parent, false);

		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.getTextView().setText(MainActivity.tasks.get(position));
	}

	@Override
	public int getItemCount() {
		return MainActivity.tasks.size();
	}
}
