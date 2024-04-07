package com.example.cswaitinglistmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class SearchStudent extends AppCompatActivity {

    // creating variable
    private EditText waitingIdEdt;
    private ArrayList<WaitingListModal> courseModalArrayList;
    private Button searchBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);

        String actionVal = getIntent().getStringExtra("Action");

        waitingIdEdt = findViewById(R.id.idEdtWaitingId);

        searchBtn = findViewById(R.id.idBtnSearchStudent);

        dbHandler = new DBHandler(SearchStudent.this);

        // below line is to add on click listener to search a student.
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from text field.
                String waitingId = waitingIdEdt.getText().toString();

                courseModalArrayList = new ArrayList<>();
                dbHandler = new DBHandler(SearchStudent.this);

                String courseNameVal, studentNameVal, studentIdVal, priorityVal, cellVal, yearVal, addressVal;
                int waitingIdVal;

                // validating if the text field is empty or not.
                if (waitingId.isEmpty() ) {
                    Toast.makeText(SearchStudent.this, "Please enter the Waiting List Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method search student in database.
                courseModalArrayList = dbHandler.searchStudent(Integer.parseInt(waitingId));

                //Check if student exists
                if (courseModalArrayList.isEmpty()) {
                    Toast.makeText(SearchStudent.this, "No Records found.", Toast.LENGTH_SHORT).show();
                } else {
                    //Retrieve student details
                    waitingIdVal = courseModalArrayList.get(0).getWaitingId();
                    studentIdVal = courseModalArrayList.get(0).getStudentId();
                    courseNameVal = courseModalArrayList.get(0).getCourseName();
                    studentNameVal = courseModalArrayList.get(0).getStudentName();
                    priorityVal = courseModalArrayList.get(0).getPriority();
                    yearVal = courseModalArrayList.get(0).getYear();
                    cellVal = courseModalArrayList.get(0).getCell();
                    addressVal = courseModalArrayList.get(0).getAddress();

                    Intent mainIntent = null;
                    //Invoke correct activity
                    if (actionVal.equals("Update")){
                         mainIntent = new Intent(SearchStudent.this, UpdateStudent.class);

                    }
                    if (actionVal.equals("Delete")){
                         mainIntent = new Intent(SearchStudent.this, DeleteStudent.class);
                    }
                    //Pass on retrieved student details to next activity
                    mainIntent.putExtra("WaitingId",waitingIdVal );
                    mainIntent.putExtra("StudentId", studentIdVal);
                    mainIntent.putExtra("CourseName", courseNameVal);
                    mainIntent.putExtra("StudentName", studentNameVal);
                    mainIntent.putExtra("Priority", priorityVal);
                    mainIntent.putExtra("Year", yearVal);
                    mainIntent.putExtra("Cell", cellVal);
                    mainIntent.putExtra("Address", addressVal);
                    SearchStudent.this.startActivity(mainIntent);
                    SearchStudent.this.finish();

                }
            }
        });
    }
    public void HomeFn(View view) {
        Intent mainIntent = new Intent(SearchStudent.this, WaitingListMgmt.class);
        SearchStudent.this.startActivity(mainIntent);
        SearchStudent.this.finish();
    }
}