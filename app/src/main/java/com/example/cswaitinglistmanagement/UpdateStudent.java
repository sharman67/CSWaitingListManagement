package com.example.cswaitinglistmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateStudent extends AppCompatActivity {

    // creating variables
    private EditText courseNameEdt, studentNameEdt, studentIdEdt, cellEdt, addressEdt;
    private TextView waitingIdTV;
    private String courseNameVal, studentNameVal, studentIdVal, cellVal, addressVal, priorityVal, yearVal;
    private int waitingIdVal;
    private Button updateStudentBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        studentNameEdt = findViewById(R.id.idEdtStudentName);
        studentIdEdt = findViewById(R.id.idEdtStudentId);
        cellEdt = findViewById(R.id.idEdtCell);
        addressEdt = findViewById(R.id.idEdtAddress);
        waitingIdTV = findViewById(R.id.idEdtWaitingId);

        updateStudentBtn = findViewById(R.id.idBtnUpdateStudent);

        Spinner spinnerPriority = (Spinner) findViewById(R.id.idEdtPriority);
        Spinner spinnerYear = (Spinner) findViewById(R.id.idEdtYear);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(
                this,
                R.array.priority_list,
                android.R.layout.simple_spinner_item
        );
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(
                this,
                R.array.year_list,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerPriority.setAdapter(adapterPriority);
        spinnerYear.setAdapter(adapterYear);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(UpdateStudent.this);
        //Get student details passed to the acitivity
        courseNameVal = getIntent().getStringExtra("CourseName");
        studentNameVal = getIntent().getStringExtra("StudentName");
        studentIdVal = getIntent().getStringExtra("StudentId");
        priorityVal = getIntent().getStringExtra("Priority");
        yearVal = getIntent().getStringExtra("Year");
        cellVal = getIntent().getStringExtra("Cell");
        addressVal = getIntent().getStringExtra("Address");
        waitingIdVal = getIntent().getIntExtra("WaitingId",0);

        //Set values for the student retrieved
        courseNameEdt.setText(courseNameVal);
        studentNameEdt.setText(studentNameVal);
        studentIdEdt.setText(studentIdVal);
        cellEdt.setText(cellVal);
        addressEdt.setText(addressVal);
        waitingIdTV.setText("WaitingListNumber: " + waitingIdVal);

        int spinnerPriorityPosition = adapterPriority.getPosition(priorityVal);
        spinnerPriority.setSelection(spinnerPriorityPosition);

        int spinnerYearPosition = adapterPriority.getPosition(yearVal);
        spinnerYear.setSelection(spinnerYearPosition);

        updateStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all  text fields.
                String courseName = courseNameEdt.getText().toString();
                String studentName = studentNameEdt.getText().toString();
                String studentId = studentIdEdt.getText().toString();
                String priority = spinnerPriority.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();
                String cell = cellEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String waitingId = String.valueOf(waitingIdVal);

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() || studentName.isEmpty() || studentId.isEmpty() || priority.isEmpty()
                        || year.isEmpty() || cell.isEmpty() || address.isEmpty()) {
                    Toast.makeText(UpdateStudent.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to update
                // student to sqlite data and pass all our values to it.
                dbHandler.updateStudent(waitingId, studentId, courseName, studentName, priority, year, cell, address );

                // after adding the data we are displaying a toast message.
                Toast.makeText(UpdateStudent.this, "Student Details has been Updated.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void HomeFn(View view) {
        Intent mainIntent = new Intent(UpdateStudent.this, WaitingListMgmt.class);
        UpdateStudent.this.startActivity(mainIntent);
        UpdateStudent.this.finish();
    }
}