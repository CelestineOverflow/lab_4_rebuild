import datatypes.Attempt;
import datatypes.Professor;
import datatypes.Student;

import java.sql.*;
import java.util.ArrayList;

public class GetDataFromDatabase {
    private static String dbUrl = "jdbc:mysql://localhost:3306/stisys";
    private static String username = "root";
    private static String password = "1234";
    private Connection connection;

    public GetDataFromDatabase() {
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "select * from Student;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String dob = resultSet.getString("dob");
                String programID = resultSet.getString("programID");
                students.add(new Student(studentID, firstName, lastName, dob, programID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public ArrayList<Attempt> getAttemptsForStudent(Student student) {
        ArrayList<Attempt> results = new ArrayList<>();
        String query = "select * from ATTEMPT where studentID = " + String.valueOf(student.studentID) + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String courseID = resultSet.getString("courseID");
                String term = resultSet.getString("term");
                String attemptYear = resultSet.getString("attemptYear");
                String grade = resultSet.getString("grade");
                results.add(new Attempt(studentID, courseID, term, attemptYear, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public Professor getProfessorFromAttempt(Attempt attempt) {
        Professor professor = null;
        String query = "select * from professor where courseID = " + String.valueOf(attempt.courseID) + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String e_number = resultSet.getString("e_number");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String courseID = resultSet.getString("courseID");
                professor = new Professor(e_number, firstName, lastName, courseID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor;
    }
}
