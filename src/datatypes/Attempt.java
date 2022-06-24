package datatypes;

import java.util.ArrayList;

public class Attempt implements dataArray {
    public String studentID;
    public String courseID;
    public String attemptYear;
    public String term;
    public String grade;
    public static String[] LABELS = {"studentID", "courseID", "attemptYear", "term", "grade"};

    public Attempt(String studentID, String courseID, String term, String attemptYear, String grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.attemptYear = attemptYear;
        this.term = term;
        this.grade = grade;
    }

    public void printRaw(){
        System.out.println("courseID: " + courseID + " term: " + term + " year: " + attemptYear + " grade: " + grade);
    }
    public static String[][] arrayValues2D(ArrayList<Attempt> attempts) {
        String[][] data2D = new String[attempts.size()][Attempt.LABELS.length];
        for (int i = 0; i < attempts.size(); i++) {
            data2D[i] = attempts.get(i).arrayValues();
        }
        return data2D;
    }

    @Override
    public String[] arrayValues() {
        String[] data = {studentID, courseID, attemptYear, term, grade};
        return data;
    }
}

