package com.example.cswaitinglistmanagement;

public class WaitingListModal {

    // variables for all fields
    private String courseName;
    private String studentName;
    private String cell;
    private String address;
    private String studentId;
    private String priority;
    private String year;
    private int waitingId;

    // creating getter and setter methods
    public String getCourseName() { return courseName; }
    public String getStudentName() { return studentName; }
    public String getCell() { return cell; }
    public String getAddress() { return address; }
    public String getStudentId() { return studentId; }
    public String getPriority() { return priority; }
    public String getYear() { return year; }
    public int getWaitingId() { return waitingId; }

    // constructor
    public WaitingListModal(int waitingId,
                       String studentId,
                       String courseName,
                       String studentName,
                            String priority,
                            String year,
                            String cell,
                            String address)
    {
        this.waitingId = waitingId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.priority = priority;
        this.year = year;
        this.cell = cell;
        this.address = address;
    }
}
