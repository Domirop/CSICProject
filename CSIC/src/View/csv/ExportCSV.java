/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author domit
 */
public class ExportCSV {

    MainFrame mf;

    public ExportCSV(MainFrame mf) {
        this.mf = mf;
    }

    public void exportToCSV(String fileName) {
        List<String[]> datas = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) mf.table.getModel();
        String[] columnNames = new String[model.getColumnCount()];
        for (int i = 0; i < model.getColumnCount(); i++) {
            columnNames[i] = model.getColumnName(i);
        }
        datas.add(columnNames);
        for (int i = 0; i < model.getRowCount(); i++) {
            String[] data = new String[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                data[j] = String.valueOf(model.getValueAt(i, j));
            }
            datas.add(data);
        }
        String folder = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(mf);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.exists()) {
                new File(file.getAbsolutePath()).mkdir();
            }
            folder = file.getAbsolutePath();
        }
        if (mf.controller.writeCSV(datas, folder, fileName)) {
            mf.errorText.setForeground(Color.GREEN);
            mf.errorText.setText("File was created successfully.");
        } else {
            mf.errorText.setForeground(Color.red);
            mf.errorText.setText("File wasn't created successfullt.");
        }
    }
}
