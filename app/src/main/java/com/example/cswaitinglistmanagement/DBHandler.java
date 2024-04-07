package com.example.cswaitinglistmanagement;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "CSDeptDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "WaitingList";

    // below variable is for columns.
    private static final String WAITING_ID_COL = "Student_Waiting_Id";
    private static final String STUDENT_ID_COL = "Student_Id";
    private static final String COURSE_NAME_COL = "Course_Name";
    private static final String STUDENT_NAME_COL = "Student_Name";
    private static final String STUDENT_PRIORITY_COL = "Student_Priority";
    private static final String STUDENT_YEAR_COL = "Student_Year";
    private static final String STUDENT_CELL_COL = "Student_Cell";
    private static final String STUDENT_ADDRESS_COL = "Student_Address";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + WAITING_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_ID_COL + " TEXT,"
                + COURSE_NAME_COL + " TEXT,"
                + STUDENT_NAME_COL + " TEXT,"
                + STUDENT_PRIORITY_COL + " TEXT,"
                + STUDENT_YEAR_COL + " TEXT,"
                + STUDENT_CELL_COL + " TEXT,"
                + STUDENT_ADDRESS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new student to our sqlite database.
    public int addStudent(String studentId, String courseName, String name, String priority,
                           String year, String cell, String address) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(STUDENT_ID_COL, studentId);
        values.put(COURSE_NAME_COL, courseName);
        values.put(STUDENT_NAME_COL, name);
        values.put(STUDENT_PRIORITY_COL, priority);
        values.put(STUDENT_YEAR_COL, year);
        values.put(STUDENT_CELL_COL, cell);
        values.put(STUDENT_ADDRESS_COL, address);

        // after adding all values we are passing
        // content values to our table.
        int vWaitingId;
        vWaitingId = (int) db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        // Returning primary key
        return vWaitingId;
    }
    // this method is use to update a student from our sqlite database.
    public void updateStudent(String waitingId, String studentId, String courseName, String name, String priority,
                          String year, String cell, String address) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(STUDENT_ID_COL, studentId);
        values.put(COURSE_NAME_COL, courseName);
        values.put(STUDENT_NAME_COL, name);
        values.put(STUDENT_PRIORITY_COL, priority);
        values.put(STUDENT_YEAR_COL, year);
        values.put(STUDENT_CELL_COL, cell);
        values.put(STUDENT_ADDRESS_COL, address);

        String selection = WAITING_ID_COL + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { waitingId };

        // after adding all values we are passing
        // content values to our table.
        db.update(TABLE_NAME, values, selection, selectionArgs );

        // at last we are closing our
        // database after adding database.
        db.close();

    }
    // this method is use to delete a student from our sqlite database.
    public void deleteStudent(String waitingId ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = WAITING_ID_COL + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { waitingId };
        // Issue SQL statement.
        db.delete(TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    // we have created a new method for reading all the students in waiting list.
    public ArrayList<WaitingListModal> readWaitingList() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorWaitingList = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<WaitingListModal> waitingListModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorWaitingList.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                waitingListModalArrayList.add(new WaitingListModal(cursorWaitingList.getInt(0),
                        cursorWaitingList.getString(1),
                        cursorWaitingList.getString(2),
                        cursorWaitingList.getString(3),
                        cursorWaitingList.getString(4),
                        cursorWaitingList.getString(5),
                        cursorWaitingList.getString(6),
                        cursorWaitingList.getString(7)));
            } while (cursorWaitingList.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorWaitingList.close();
        return waitingListModalArrayList;
    }

    // we have created a new method for search a student in the waiting list.
    public ArrayList<WaitingListModal> searchStudent(int vWaitingListId) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorSearchStudent = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + WAITING_ID_COL
                                                    + " = " + vWaitingListId, null);
        // on below line we are creating a new array list.
        ArrayList<WaitingListModal> waitingListModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorSearchStudent.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                waitingListModalArrayList.add(new WaitingListModal(cursorSearchStudent.getInt(0),
                        cursorSearchStudent.getString(1),
                        cursorSearchStudent.getString(2),
                        cursorSearchStudent.getString(3),
                        cursorSearchStudent.getString(4),
                        cursorSearchStudent.getString(5),
                        cursorSearchStudent.getString(6),
                        cursorSearchStudent.getString(7)));
            } while (cursorSearchStudent.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorSearchStudent.close();
        Log.v(TAG, "StudentCursorWaitingId: " + waitingListModalArrayList);
        return waitingListModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}