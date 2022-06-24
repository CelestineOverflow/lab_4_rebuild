import javax.swing.*;
import java.awt.*;

public class GeneralTable extends JTable {
    private static Font DEFAULT_FONT = new Font("Segoe UI Light", Font.PLAIN, 16);
    public GeneralTable(int rows, int columns, String[] LABELS) {
        super(rows, columns);
        setFont(DEFAULT_FONT);
        initLabels(LABELS);
    }
    public void initLabels(String[] labels){
        int column = 0;
        for(String label : labels){
            if(column>(getColumnCount()-1)){
                break;
            }
            setValueAt(label, 0, column);
            column++;
        }
    }
    public void setData(String[][] data){
        for(int row = 0; row < data.length; row++){
            for(int column = 0; column < data[row].length; column++){
                setValueAt(data[row][column], row+1, column);
            }
        }
    }
}
