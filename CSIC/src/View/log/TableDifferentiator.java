/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.log;

import View.log.FrameDifferentiator;
import Model.log.Atomo.FileData;
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
    public JTable initTablesDifferentiators(List<FileData> fileData) {
        JTable table = new JTable();
        table.setAutoCreateRowSorter(true);
        List<String> singleNames = new ArrayList<>();
        singleNames.add("Gaussian");
        singleNames.add("Atom");
        fileData.stream().filter((file) -> (!singleNames.contains(file.getFileName()))).forEachOrdered((file) -> {
            singleNames.add(file.getFileName());
        });

        DefaultTableModel model = new DefaultTableModel(
                singleNames.toArray(new String[0]),
                0) {

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
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
     * @param fileData List with the elements of row.
     * @return myTable with rows added.
     */
    public JTable addRowsToTable(JTable table, List<FileData> fileData) {
        JTable tableWithElements = table;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String fileNameError = "";
        try{
            fd.errorText.setText("");
            List<Object> data = new ArrayList<>();
            for (int i = 0; i < fileData.get(0).getAtoms().size(); i++) {
                data.clear();
                for (int j = 1; j < fileData.size() + 1; j++) {
                    fileNameError = fileData.get(j - 1).getFileName();
                    if (j == 1) {
                        data.add(Integer.parseInt(fileData.get(j - 1).getAtoms().get(i).getGaussianData()));
                        data.add(fileData.get(j - 1).getAtoms().get(i).getAtom());
                    }
                    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                    otherSymbols.setDecimalSeparator('.');
                    otherSymbols.setGroupingSeparator(',');
                    DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
                    df.setRoundingMode(RoundingMode.CEILING);
                    data.add(Double.parseDouble(String.valueOf(df.format(fileData.get(j - 1).getAtoms().get(i).getIsotropic()))));
                }
                model.addRow(data.toArray(new Object[0]));
            }
            return tableWithElements;
        } catch(Exception e) {
            e.printStackTrace();
            fd.keywordsUsed.remove(fd.fieldKeyword.getText());
            fd.errorText.setText("Syntax error. Please, check the file " + fileNameError + ".");
            return table;
        }
    }
}
