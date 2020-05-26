/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.Molecule;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author daviddiaz
 */
public class AverageTable {
    //métodos relacionados con la tabla average

    FrameDifferentiator fd;

    public AverageTable(FrameDifferentiator fd) {
        this.fd = fd;
    }

    /**
     * Creates the "Average" myTable with all the info from the other tables.
     *
     * @param usedFiles
     */
    public void averageTable(List<File> usedFiles) {
        Molecule molecule = fd.controller.getMolecule(usedFiles, fd.fieldKeyword.getText(), Double.parseDouble(fd.temperature)
        );
        String[] values = new String[fd.keywordsUsed.size() + 2];
        values[0] = "Gaussian";
        values[1] = "Atom";
        for (int i = 2; i <= fd.keywordsUsed.size() + 1; i++) {
            values[i] = fd.keywordsUsed.get(i - 2);
        }

        if (fd.tableGeneric == null) {
            initAverageTable(values);
            addElementsToAverageTable(molecule);
            JScrollPane scrollpaneGeneric = new JScrollPane(fd.tableGeneric, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            fd.panelGeneric.add(scrollpaneGeneric);
        } else {
            fd.panelGeneric.removeAll();
            initAverageTable(values);
            addElementsToAverageTable(molecule);
            JScrollPane scrollpaneGeneric = new JScrollPane(fd.tableGeneric, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            fd.panelGeneric.add(scrollpaneGeneric);
        }
        TableColumn column = null;
        for (int i = 0; i < fd.tableGeneric.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = fd.tableGeneric.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = fd.tableGeneric.getColumnModel().getColumn(i);
                column.setMinWidth(200);
            }
        }
        fd.tableGeneric.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    /**
     * Initializes the Average myTable.
     *
     * @param values are the "keywords" used.
     */
    private void initAverageTable(String[] values) {
        fd.tableGeneric = new JTable();

        boolean[] canEditTry = new boolean[fd.keywordsUsed.size() + 2];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelGeneric = new DefaultTableModel(values, 0) {
            boolean[] canEdit = canEditTry;

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
        fd.tableGeneric.setAutoCreateRowSorter(true);
        fd.tableGeneric.setModel(modelGeneric);
        //Center columns
        for (int i = 0; i < fd.tableGeneric.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            fd.tableGeneric.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) fd.tableGeneric.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        fd.tableGeneric.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    }

    /**
     * Add all the elements to the Average myTable.
     *
     * @param molecule contains all the necessary info about the molecules.
     */
    private void addElementsToAverageTable(Molecule molecule) {
        DefaultTableModel model = (DefaultTableModel) fd.tableGeneric.getModel();
        List<List<Object>> auxList = new ArrayList<>(fd.rows);
        fd.rows.clear();
        for (int i = 0; i < molecule.getResult().size(); i++) {
            if (fd.keywordsUsed.size() == 1) {
                List<Object> values = new ArrayList<>();
                values.add(molecule.getResult().get(i).getGaussian());
                values.add(molecule.getResult().get(i).getAtom());
                double value = molecule.getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                values.add(String.valueOf(df.format(value)));
                fd.rows.add(values);
            } else {
                List<Object> values = new ArrayList<>(auxList.get(i));
                double value = molecule.getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                values.add(String.valueOf(df.format(value)));
                fd.rows.add(values);
            }
        }

        fd.rows.forEach((row) -> {
            model.addRow(row.toArray());
        });

    }
    
    /**
     * Checks if the average table exists
     * @param myTable 
     */
    private void averageTableExist(JTable myTable) {
        int firstIndex = myTable.getSelectedRows()[0];
        int secondIndex = myTable.getSelectedRows()[1];
        int thirdIndex = myTable.getSelectedRows()[2];
        System.out.println(firstIndex);

        System.out.println(firstIndex);
        String gaussians = myTable.getValueAt(firstIndex, 0).toString() + "-"
                + myTable.getValueAt(secondIndex, 0).toString() + "-"
                + myTable.getValueAt(thirdIndex, 0).toString();
        myTable.setValueAt(gaussians, firstIndex, 0);
        for (int i = 2; i < myTable.getColumnCount(); i++) {
            Double averageValue = (Double.parseDouble(myTable.getValueAt(firstIndex, i).toString())
                    + Double.parseDouble(myTable.getValueAt(secondIndex, i).toString())
                    + Double.parseDouble(myTable.getValueAt(thirdIndex, i).toString())) / 3;
            myTable.setValueAt(averageValue, firstIndex, i);
        }
        DefaultTableModel df = (DefaultTableModel) myTable.getModel();
        df.removeRow(thirdIndex);
        df.removeRow(secondIndex);
        fd.multiTable = true;
    }

    /**
     * Checks if the average table does not exist
     * @param myTable 
     */
    private void averageTableNotExist(JTable myTable) {
        List<List<Object>> values = new ArrayList<>();
        int firstIndex = myTable.getSelectedRows()[0];
        int secondIndex = myTable.getSelectedRows()[1];
        int thirdIndex = myTable.getSelectedRows()[2];
        for (int i = 0; i < myTable.getRowCount(); i++) {
            List<Object> localRows = new ArrayList<>();
            if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                if (i == firstIndex) {
                    localRows.add(myTable.getValueAt(firstIndex, 0).toString() + "-"
                            + myTable.getValueAt(secondIndex, 0).toString() + "-"
                            + myTable.getValueAt(thirdIndex, 0).toString());
                    localRows.add(myTable.getValueAt(i, 1).toString());
                }
            } else {
                localRows.add(myTable.getValueAt(i, 0).toString());
                localRows.add(myTable.getValueAt(i, 1).toString());
            }
            for (int j = 2; j < myTable.getColumnCount(); j++) {
                if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                    if (i == firstIndex) {
                        Double averageValue = (Double.parseDouble(myTable.getValueAt(firstIndex, j).toString())
                                + Double.parseDouble(myTable.getValueAt(secondIndex, j).toString())
                                + Double.parseDouble(myTable.getValueAt(thirdIndex, j).toString())) / 3;
                        localRows.add(averageValue);
                    }
                } else {
                    localRows.add(myTable.getValueAt(i, j).toString());
                }
            }
            if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                if (i == firstIndex) {
                    values.add(localRows);
                }
            } else {
                values.add(localRows);
            }

        }

        String[] headerValues = new String[myTable.getColumnCount()];
        for (int i = 0; i < myTable.getColumnCount(); i++) {
            headerValues[i] = myTable.getColumnName(i);
        }

        JPanel panel = (JPanel) fd.tabbedPane.getComponentAt(0);
        JTable averageTable = new JTable();
        boolean[] canEditTry = new boolean[headerValues.length];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelAverage = new DefaultTableModel(headerValues, 0) {
            boolean[] canEdit = canEditTry;

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
        averageTable.setAutoCreateRowSorter(true);
        for (List<Object> value : values) {
            Object[] datas = value.toArray();
            modelAverage.addRow(datas);
        }
        averageTable.setModel(modelAverage);
        averageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Center columns
        for (int i = 0; i < averageTable.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            averageTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) fd.tableGeneric.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        averageTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollpane = new JScrollPane(averageTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollpane);
        panel.revalidate();
        panel.repaint();
        TableColumn column = null;
        for (int i = 0; i < averageTable.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = averageTable.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = averageTable.getColumnModel().getColumn(i);
                column.setMinWidth(300);
            }
        }
        fd.multiTable = true;
    }

    /**
     * 
     * @param evt 
     */
    public void buttonAverageActionPerformed(ActionEvent evt) {
        JPanel myPanel = (JPanel) (fd.tabbedPane.getSelectedComponent());
        if (myPanel.getComponentCount() == 1) {
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();
            if (myTable.getSelectedRowCount() == 3) {
                averageTableNotExist(myTable);

            } else {
                fd.errorText.setText("Sorry, but you need check 3 rows");
            }
        } else {
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(1);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();
            if (myTable.getSelectedRowCount() == 3) {
                averageTableExist(myTable);
            } else {
                fd.errorText.setText("Sorry, but you need check 3 rows in second table.");
            }
        }
    }

}
