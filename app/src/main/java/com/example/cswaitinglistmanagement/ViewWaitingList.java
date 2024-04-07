package com.example.cswaitinglistmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewWaitingList extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<WaitingListModal> courseModalArrayList;
    private DBHandler dbHandler;
    private WaitingListRVAdapter waitingListRVAdapter;
    private RecyclerView waitingListRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waiting_list);

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewWaitingList.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readWaitingList();

        // on below line passing our array list to our adapter class.
        waitingListRVAdapter = new WaitingListRVAdapter(courseModalArrayList, ViewWaitingList.this);
        waitingListRV = findViewById(R.id.idRVWaitingList);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewWaitingList.this, RecyclerView.VERTICAL, false);
        waitingListRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        waitingListRV.setAdapter(waitingListRVAdapter);
    }

    public void HomeFn(View view) {
        Intent mainIntent = new Intent(ViewWaitingList.this, WaitingListMgmt.class);
        ViewWaitingList.this.startActivity(mainIntent);
        ViewWaitingList.this.finish();
    }
}