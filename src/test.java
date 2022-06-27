import datatypes.Attempt;
import datatypes.Professor;
import datatypes.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class test {
    private JPanel mainPanel;
    private JButton showAttempsButton;
    private JButton showStudentsButton;
    private JButton showSupervisingProfesorButton;
    private JPanel tablePanel;
    private JLabel tableXLabel;
    private GetDataFromDatabase getDataFromDatabase;
    private GeneralTable generalTable;
    private Student studentSelected;

    public test() {
        showStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableXLabel.setText("Table : Students");
                String[][] data = Student.arrayValues2D(getDataFromDatabase.getStudents());
                updateTableAsStudent(data);
            }
        });
        showAttempsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableXLabel.setText("Table : Attempts");
                int selectedRow = generalTable.getSelectedRow();
                System.out.println("Selected Row ");
                System.out.println(selectedRow);
                if (selectedRow > 0 && selectedRow <= getDataFromDatabase.getStudents().size()) {
                    studentSelected = getDataFromDatabase.getStudents().get(selectedRow - 1);
                    String[][] data = Attempt.arrayValues2D(getDataFromDatabase.getAttemptsForStudent(studentSelected));
                    updateTableAsAttempt(data);
                }
            }
        });
        showSupervisingProfesorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableXLabel.setText("Table : Supervising Profesor");
                int selectedRow = generalTable.getSelectedRow();
                System.out.println("Selected Row ");
                System.out.println(selectedRow);
                // TODO: get the professor from the database

                if (selectedRow > 0 && selectedRow <= getDataFromDatabase.getAttemptsForStudent(studentSelected).size()) {
                    Professor professor = getDataFromDatabase.getProfessorFromAttempt(getDataFromDatabase.getAttemptsForStudent(studentSelected).get(selectedRow - 1));
                    ArrayList<Professor> professors = new ArrayList<>();
                    professors.add(professor);
                    String[][] data = Professor.arrayValues2D(professors);
                    updateTableAsProfessor(data);
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void updateTableAsAttempt(String[][] data) {
        generalTable = new GeneralTable(10, Attempt.LABELS.length, Attempt.LABELS);
        generalTable.setData(data);
        tablePanel.removeAll();
        tablePanel.add(generalTable);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void updateTableAsStudent(String[][] data) {
        generalTable = new GeneralTable(10, Student.LABELS.length, Student.LABELS);
        generalTable.setData(data);
        tablePanel.removeAll();
        tablePanel.add(generalTable);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void updateTableAsProfessor(String[][] data) {
        generalTable = new GeneralTable(10, Professor.LABELS.length, Professor.LABELS);
        generalTable.setData(data);
        tablePanel.removeAll();
        tablePanel.add(generalTable);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void createUIComponents() {
        getDataFromDatabase = new GetDataFromDatabase();
        String[][] data = Student.arrayValues2D(getDataFromDatabase.getStudents());
        tablePanel = new JPanel();
        generalTable = new GeneralTable(10, Student.LABELS.length, Student.LABELS);
        generalTable.setData(data);
        tablePanel.add(generalTable);
    }
}
