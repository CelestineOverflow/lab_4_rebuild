package datatypes;

import java.util.ArrayList;

public class Professor implements dataArray {
    public static String[] LABELS = {"e_number", "firstName", "lastName", "courseID"};
    public String e_number;
    public String firstName;
    public String lastName;
    public String courseID;

    public Professor(String e_number, String firstName, String lastName, String courseID) {
        this.e_number = e_number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseID = courseID;
    }

    public void printRaw() {
        String spacer = " - ";
        System.out.println(e_number + spacer + firstName + spacer + lastName + spacer + courseID);
    }

    @Override
    public String[] arrayValues() {
        String[] data = {e_number, firstName, lastName, courseID};
        return data;
    }

    static public String[][] arrayValues2D(ArrayList<Professor> data) {
        String[][] data2D = new String[data.size()][Professor.LABELS.length];
        for (int i = 0; i < data.size(); i++) {
            data2D[i] = data.get(i).arrayValues();
        }
        return data2D;
    }
}
