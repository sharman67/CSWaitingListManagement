package com.example.cswaitinglistmanagement;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class WaitingListRVAdapter extends RecyclerView.Adapter<WaitingListRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<WaitingListModal> waitingListModalArrayList;
    private Context context;

    // constructor
    public WaitingListRVAdapter(ArrayList<WaitingListModal> waitingListModalArrayList, Context context) {
        this.waitingListModalArrayList = waitingListModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.waiting_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        WaitingListModal modal = waitingListModalArrayList.get(position);
        int waitingIdVal = modal.getWaitingId();
        holder.waitingIdTV.setText("Waiting List Id: " + Integer.toString(waitingIdVal));
        holder.courseNameTV.setText(modal.getCourseName());
        holder.studentNameTV.setText(modal.getStudentName());
        holder.studentIdTV.setText(modal.getStudentId());
        holder.priorityTV.setText(modal.getPriority());
        holder.cellTV.setText(modal.getCell());
        holder.yearTV.setText(modal.getYear());
        holder.addressTV.setText("Address: " + modal.getAddress());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return waitingListModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseNameTV, studentNameTV, studentIdTV, priorityTV, cellTV, yearTV, addressTV, waitingIdTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            waitingIdTV = itemView.findViewById(R.id.idCSWaitingId);
            courseNameTV = itemView.findViewById(R.id.idCourseName);
            studentNameTV = itemView.findViewById(R.id.idStudentName);
            studentIdTV = itemView.findViewById(R.id.idStudentId);
            priorityTV = itemView.findViewById(R.id.idPriority);
            cellTV = itemView.findViewById(R.id.idCell);
            yearTV = itemView.findViewById(R.id.idYear);
            addressTV = itemView.findViewById(R.id.idAddress);
        }
    }
}
