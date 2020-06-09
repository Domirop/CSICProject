/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import java.awt.Color;
import javax.swing.BorderFactory;
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

    public void removeElements(double minValue, double maxValue, String column) {
        try {
            DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
            for (int i = 0; i < model.getColumnCount(); i++) {
                if(model.getColumnName(i).equals(column)){
                    index = i;
                }
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                double value = Double.parseDouble(String.valueOf(model.getValueAt(i, index)));
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
                double value = Double.parseDouble(String.valueOf(model.getValueAt(0, i)));
                fr.ComboColumn.addItem(model.getColumnName(i));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        fr.ComboColumn.repaint();
    }

    private boolean controlText() {
        if (!fr.textMaxValue.getText().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$") || fr.textMaxValue.getText().length() == 0) {
            fr.textMaxValue.setBorder(BorderFactory.createLineBorder(Color.red));
            fr.textMaxValue.setToolTipText("The format is (number).(2 number)");
            return false;
        } else {
            fr.textMaxValue.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            fr.textMaxValue.setToolTipText("The format is (number).(2 number)");
        }
        if (!fr.textMinValue.getText().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$") || fr.textMinValue.getText().length() == 0) {
            fr.textMinValue.setBorder(BorderFactory.createLineBorder(Color.red));
            fr.textMinValue.setToolTipText("The format is (number).(2 number)");
            return false;
        } else {
            fr.textMinValue.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        return true;
    }
    
    public void removeElements() {
        fr.errorText.setText("");
        fr.errorText.setForeground(Color.RED);
        if (controlText()) {
            double maxValue = Double.parseDouble(fr.textMaxValue.getText());
            double minValue = Double.parseDouble(fr.textMinValue.getText());
            String column = fr.ComboColumn.getSelectedItem().toString();
            if (minValue <= maxValue) {
                removeElements(minValue, maxValue, column);
            } else {
                fr.errorText.setText("Min value can't be less than max value");
            }
        }
    }
}
