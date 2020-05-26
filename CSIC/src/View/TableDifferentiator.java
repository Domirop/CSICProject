/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.FileData;
import java.awt.Font;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author domit
 */
public class TableDifferentiator {

    FrameDifferentiator fd;

    public TableDifferentiator(FrameDifferentiator fd) {
        this.fd = fd;
    }

    /**
     * Creates a myTable with every single keyword.
     *
     * @return the myTable.
     */
    public JTable initTablesDifferentiators() {
        JTable table = new JTable();
        table.setAutoCreateRowSorter(true);
        List<String> singleNames = new ArrayList<>();
        singleNames.add("Gaussian");
        singleNames.add("Atom");

        fd.names.stream().filter((file) -> (!singleNames.contains(file))).forEachOrdered((file) -> {
            singleNames.add(file);
        });
        boolean[] canEditTry = new boolean[singleNames.size()];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }
        DefaultTableModel model = new DefaultTableModel(
                singleNames.toArray(new String[0]),
                0) {
            boolean[] canEdit = canEditTry;

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int column) {
                Class<?> returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }

                return returnValue;

            }
        ;
        };
        table.setModel(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        return table;
    }

    /**
     * Method used to add rows to the myTable.
     *
     * @param table the myTable that we want to add rows to.
     * @return myTable with rows added.
     */
    public JTable addRowsToTable(JTable table) {
        JTable tableWithElements = table;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<FileData> fileData = fd.controller.getFileData(fd.usedFiles, Double.parseDouble(fd.temperature));
        String fileNameError = "";
        boolean correcto = false;
        for (String name : fd.names) {
            salir:
            if (!correcto) {
                for (FileData fileData1 : fileData) {
                    if (fileData1.getFileName().equals(name)) {
                        fileNameError = "";
                        break salir;
                    } else {
                        correcto = true;
                        fileNameError = name;
                    }
                }
            }
        }
        if (fileData.size() == fd.usedFiles.size()) {
            fd.errorText.setText("");
            List<Object> data = new ArrayList<>();
            for (int i = 0; i < fileData.get(0).getAtoms().size(); i++) {
                data.clear();
                for (int j = 1; j < fileData.size() + 1; j++) {
                    if (j == 1) {
                        data.add(Integer.parseInt(fileData.get(j - 1).getAtoms().get(i).getGaussianData()));
                        data.add(fileData.get(j - 1).getAtoms().get(i).getAtom());
                    }
                    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                    otherSymbols.setDecimalSeparator('.');
                    otherSymbols.setGroupingSeparator(',');
                    DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                    df.setRoundingMode(RoundingMode.CEILING);
                    data.add(Double.parseDouble(String.valueOf(df.format(fileData.get(j - 1).getAtoms().get(i).getIsotropic()))));
                }
                model.addRow(data.toArray(new Object[0]));
            }
            return tableWithElements;
        } else {
            fd.keywordsUsed.remove(fd.fieldKeyword.getText());
            fd.errorText.setText("Syntax error. Please, check the file " + fileNameError + ".");
            return table;
        }
    }
}
