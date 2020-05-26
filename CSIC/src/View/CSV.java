/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;

/**
 *
 * @author daviddiaz
 */
public class CSV {
    FrameDifferentiator fd;

    public CSV(FrameDifferentiator fd) {
        this.fd = fd;
    }
    
    public void exportCSV(){
        String folder = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(fd);
        if (option == JFileChooser.APPROVE_OPTION) {
            fd.keywordsUsed.add("Average");
            File file = fileChooser.getSelectedFile();
            if (!file.exists()) {
                new File(file.getAbsolutePath()).mkdir();
            }
            folder = file.getAbsolutePath();

            if (fd.usedTables.size() > 0) {
                for (int i = 0; i < fd.tabbedPane.getTabCount() + 1; i++) {
                    List<String[]> datas = new ArrayList<>();
                    if (i == 0) {
                        if (fd.multiTable == true) {
                            datas = exportTable(null, i, 0, fd.tabbedPane);
                            datas.add(new String[0]);
                            datas.add(new String[0]);
                            datas.addAll(exportTable(null, i, 1, fd.tabbedPane));
                            exportCSVFunction(datas, folder, "Average");
                        } else {
                            datas = exportTable(null, i, 0, fd.tabbedPane);
                            exportCSVFunction(datas, folder, "Average");
                        }
                    } else if (i == fd.tabbedPane.getTabCount()) {
                        JTabbedPane panes = (JTabbedPane) fd.dialogSCF.getContentPane().getComponent(0);
                        for (int j = 0; j < panes.getTabCount(); j++) {
                            JPanel myPanel = (JPanel) panes.getComponentAt(j);
                            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
                            JViewport viewport = scrollPane.getViewport();
                            JTable myTable = (JTable) viewport.getView();
                            datas.addAll(exportTable(myTable, j, 0, panes));
                        }
                        exportCSVFunction(datas, folder, "SCF_and_CONTRIBUTION");
                    } else {
                        datas = exportTable(null, i, 0, fd.tabbedPane);
                        exportCSVFunction(datas, folder, fd.tabbedPane.getTitleAt(i));
                    }
                }
            }
        }
    }
    
    private void exportCSVFunction(List<String[]> datas, String folder, String nameFile) {
        if (fd.controller.writeCSV(datas, folder, nameFile)) {
            fd.errorText.setForeground(Color.green);
            fd.errorText.setText("All files have been created.");
        } else {
            fd.errorText.setForeground(Color.RED);
            fd.errorText.setText("Error has ocurred in table " + nameFile);
        }
    }

    private List<String[]> exportTable(JTable myTable, int indexTab, int indexComponent, JTabbedPane pane) {
        List<String[]> datas = new ArrayList<>();
        if (myTable == null) {
            JPanel myPanel = (JPanel) (pane.getComponentAt(indexTab));
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(indexComponent);
            JViewport viewport = scrollPane.getViewport();
            myTable = (JTable) viewport.getView();
        }
        String[] columnNames = new String[myTable.getColumnCount()];
        for (int j = 0; j < myTable.getColumnCount(); j++) {
            columnNames[j] = myTable.getColumnName(j);
        }
        datas.add(columnNames);
        for (int k = 0; k < myTable.getRowCount(); k++) {
            String[] data = new String[myTable.getColumnCount()];
            for (int j = 0; j < myTable.getColumnCount(); j++) {
                if (myTable.getValueAt(k, j) != null && myTable.getValueAt(k, j).toString().trim().length() != 0) {
                    data[j] = String.valueOf(myTable.getValueAt(k, j));
                }
            }
            datas.add(data);
        }
        return datas;
    }
}
