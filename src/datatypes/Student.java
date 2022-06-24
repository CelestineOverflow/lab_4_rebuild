package datatypes;

import java.util.ArrayList;

public class Student implements dataArray {
    public static String[] LABELS = {"studentID", "firstName", "lastName", "dob", "programID"};
    public String studentID;
    public String firstName;
    public String lastName;
    public String dob;
    public String programID;

    public Student(String studentID, String firstName, String lastName, String dob, String programID) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.programID = programID;
    }

    public void printRaw() {
        String spacer = " - ";
        System.out.println(studentID + spacer + firstName + spacer + lastName + spacer + dob + spacer + programID);
    }

    @Override
    public String[] arrayValues() {
        String[] data = {studentID, firstName, lastName, dob, programID};
        return data;
    }
    static public String[][] arrayValues2D(ArrayList<Student> students) {
        String[][] data2D = new String[students.size()][Student.LABELS.length];
        for (int i = 0; i < students.size(); i++) {
            data2D[i] = students.get(i).arrayValues();
        }
        return data2D;
    }
}