package com.example.cswaitinglistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WaitingListMgmt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_list_mgmt);
    }

    //methods to invoke appropriate activities
    public void WaitingListFn(View view) {
        Intent mainIntent = new Intent(WaitingListMgmt.this, ViewWaitingList.class);
        WaitingListMgmt.this.startActivity(mainIntent);
        WaitingListMgmt.this.finish();
    }

    public void DeleteStudentFn(View view) {
        Intent mainIntent = new Intent(WaitingListMgmt.this, SearchStudent.class);
        mainIntent.putExtra("Action", "Delete");
        WaitingListMgmt.this.startActivity(mainIntent);
        WaitingListMgmt.this.finish();
    }

    public void UpdateStudentFn(View view) {
        Intent mainIntent = new Intent(WaitingListMgmt.this, SearchStudent.class);
        mainIntent.putExtra("Action", "Update");
        WaitingListMgmt.this.startActivity(mainIntent);
        WaitingListMgmt.this.finish();
    }

    public void AddStudentFn(View view) {
        Intent mainIntent = new Intent(WaitingListMgmt.this, AddStudent.class);
        WaitingListMgmt.this.startActivity(mainIntent);
        WaitingListMgmt.this.finish();
    }
}