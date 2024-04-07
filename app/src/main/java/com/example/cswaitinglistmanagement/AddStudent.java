package com.example.cswaitinglistmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    // creating variables
    private EditText courseNameEdt, studentNameEdt, studentIdEdt, cellEdt, addressEdt;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        studentNameEdt = findViewById(R.id.idEdtStudentName);
        studentIdEdt = findViewById(R.id.idEdtStudentId);
        cellEdt = findViewById(R.id.idEdtCell);
        addressEdt = findViewById(R.id.idEdtAddress);

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

        Button addstudentBtn = findViewById(R.id.idBtnAddStudent);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(AddStudent.this);

        // below line is to add on click listener for our add student button.
        addstudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all fields.
                String courseName = courseNameEdt.getText().toString();
                String studentName = studentNameEdt.getText().toString();
                String studentId = studentIdEdt.getText().toString();
                String priority = spinnerPriority.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();
                String cell = cellEdt.getText().toString();
                String address = addressEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (courseName.isEmpty() || studentName.isEmpty() || studentId.isEmpty() || priority.isEmpty()
                        || year.isEmpty() || cell.isEmpty() || address.isEmpty()) {
                    Toast.makeText(AddStudent.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new student
                // to sqlite data and pass all our values to it.
                dbHandler.addStudent(studentId, courseName, studentName, priority, year, cell, address );

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddStudent.this, "Student has been added to waiting list.", Toast.LENGTH_SHORT).show();

                //Reset fields after student is added
                courseNameEdt.setText("");
                studentNameEdt.setText("");
                studentIdEdt.setText("");
                spinnerPriority.setAdapter(adapterPriority);
                spinnerYear.setAdapter(adapterYear);
                cellEdt.setText("");
                addressEdt.setText("");
            }
        });
    }
    // Home button function
    public void HomeFn(View view) {
        Intent mainIntent = new Intent(AddStudent.this, WaitingListMgmt.class);
        AddStudent.this.startActivity(mainIntent);
        AddStudent.this.finish();
    }
}