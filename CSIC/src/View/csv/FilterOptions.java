/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import View.csv.MainFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author domit
 */
public class FilterOptions {

    MainFrame fr;
    int index = 0;

    public FilterOptions(MainFrame fr) {
        this.fr = fr;
    }

    public void removeElements(double minValue, double maxValue, int column) {
        try {
            DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
            column += index;
            for (int i = 0; i < model.getRowCount(); i++) {
                double value = Double.parseDouble(String.valueOf(model.getValueAt(i, column)));
                if (value < minValue || value > maxValue) {
                    model.removeRow(i);
                    i = 0;
                }
            }
            fr.errorText.setText("");
            fr.table.repaint();
        } catch (NumberFormatException numberFormatException) {
            fr.errorText.setText("Error has ocurred, please check the file format.");
        }
    }

    public void initCombo() {
        DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
        fr.errorText.setText("");
        for (int i = 0; i < model.getColumnCount(); i++) {
            try {
                if(index == 0) index = i;
                double value = Double.parseDouble(String.valueOf(model.getValueAt(0, i)));
                fr.ComboColumn.addItem(model.getColumnName(i));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        fr.ComboColumn.repaint();
    }
}
