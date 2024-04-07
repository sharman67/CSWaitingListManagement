package com.example.cswaitinglistmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteStudent extends AppCompatActivity {

    // creating variables
    private TextView courseNameEdt, studentNameEdt, studentIdEdt, priorityEdt, yearEdt, cellEdt, addressEdt, waitingIdEdt;
    private Button deleteStudentBtn;
    private String courseNameVal, studentNameVal, studentIdVal, cellVal, addressVal, priorityVal, yearVal;
    private int waitingIdVal;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        studentNameEdt = findViewById(R.id.idEdtStudentName);
        studentIdEdt = findViewById(R.id.idEdtStudentId);
        priorityEdt = findViewById(R.id.idEdtPriority);
        yearEdt = findViewById(R.id.idEdtYear);
        cellEdt = findViewById(R.id.idEdtCell);
        addressEdt = findViewById(R.id.idEdtAddress);
        waitingIdEdt = findViewById(R.id.idEdtWaitingId);

        deleteStudentBtn = findViewById(R.id.idBtnDeleteStudent);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(DeleteStudent.this);
        //Getting all values passed in the intent
        courseNameVal = getIntent().getStringExtra("CourseName");
        studentNameVal = getIntent().getStringExtra("StudentName");
        studentIdVal = getIntent().getStringExtra("StudentId");
        priorityVal = getIntent().getStringExtra("Priority");
        yearVal = getIntent().getStringExtra("Year");
        cellVal = getIntent().getStringExtra("Cell");
        addressVal = getIntent().getStringExtra("Address");
        waitingIdVal = getIntent().getIntExtra("WaitingId",0);

        //Setting text view values for the student retrieved
        courseNameEdt.setText(courseNameVal);
        studentNameEdt.setText(studentNameVal);
        studentIdEdt.setText(studentIdVal);
        priorityEdt.setText(priorityVal);
        yearEdt.setText(yearVal);
        cellEdt.setText(cellVal);
        addressEdt.setText(addressVal);
        waitingIdEdt.setText("WaitingListNumber: " + waitingIdVal);

        // below line is to add on click listener delete student button.
        deleteStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling a method to delete
                // student from the database.
                dbHandler.deleteStudent(Integer.toString(waitingIdVal));

                // after adding the data we are displaying a toast message.
                Toast.makeText(DeleteStudent.this, "Student has been Deleted from waiting list.", Toast.LENGTH_SHORT).show();

                HomeFn(v);
            }
        });
    }
    public void HomeFn(View view) {
        Intent mainIntent = new Intent(DeleteStudent.this, WaitingListMgmt.class);
        DeleteStudent.this.startActivity(mainIntent);
        DeleteStudent.this.finish();
    }
}