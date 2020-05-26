/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.Molecule;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author daviddiaz
 */
public class AddTablesEvent {

    FrameDifferentiator fd;
    DecorateFrame df;
    AverageTable avg;
    SCFTable scf;
    TableDifferentiator td;

    public AddTablesEvent(FrameDifferentiator fd, DecorateFrame df, SCFTable scf, AverageTable avg) {
        this.fd = fd;
        this.df = df;
        this.scf = scf;
        this.avg = avg;
        this.td = new TableDifferentiator(fd);
    }

    /**
     * Method used to add new files.
     */
    public void actionButtonAdd(String fieldText) {
        try {
            fd.errorText.setForeground(Color.red);
            if (!fd.keywordsUsed.contains(fieldText)) {
                fd.keywordsUsed.add(fieldText);
                if (!fd.usedFiles.isEmpty()) {
                    if (!fd.searchAdded) {
                        df.searchTab();
                        fd.searchAdded = true;
                    }
                    fd.errorText.setText("");
                    JTable table = td.addRowsToTable(td.initTablesDifferentiators());
                    if (table.getRowCount() != 0) {
                        JPanel panel = new JPanel();
                        if (fd.usedTables.isEmpty()) {
                            fd.itemSearchValue.setEnabled(true);
                            fd.itemExport.setEnabled(false);
                            fd.itemReset.setEnabled(true);
                            fd.buttonValue.setVisible(true);
                            fd.buttonExportCSV.setVisible(true);
                            fd.buttonRemoveTable.setVisible(true);
                            fd.buttonDelete.setVisible(true);
                            fd.tabbedPane.setVisible(true);
                            fd.itemExport.setEnabled(true);
                            fd.itemSCF.setEnabled(true);
                            fd.buttonAverage.setVisible(true);
                        }
                        panel.setLayout(new GridLayout(0, 1));
                        JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        TableColumn column = null;
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            if (i == 0 || i == 1) {
                                column = table.getColumnModel().getColumn(i);
                                column.setMinWidth(100);
                            } else {
                                column = table.getColumnModel().getColumn(i);
                                column.setMinWidth(300);
                            }
                            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                            panel.add(scrollpane);
                            fd.tabbedPane.addTab(fieldText, panel);
                            fd.tabbedPane.setSelectedIndex(fd.tabbedPane.getTabCount() - 1);
                            avg.averageTable(fd.usedFiles);
                            scf.addSCFTable(fieldText);
                            if (fd.usedTables.isEmpty()) {
                                fd.usedTables.add(fd.tableGeneric);
                            }
                            fd.normalTables.add(table);
                            fd.usedTables.add(table);
                            fd.revalidate();
                            //pack();
                            fd.itemChangeTemperature.setEnabled(false);
                            fd.itemChangeTemperature.setToolTipText("To change the temperature, import the files again.");
                        }
                    } else {
                        fd.errorText.setText("Couldn't find any file with the provided keyword.");
                    }

                } else {
                    fd.keywordsUsed.remove(fieldText);
                    fd.errorText.setText("Couldn't find any file with the provided keyword.");
                }
            } else {
                fd.errorText.setText("Some files have already been imported.");
            }
        } catch (Exception e) {
            fd.errorText.setText("Some files were not imported.");
        }
        if (fd.getSize() != new Dimension(1080, 480)) {
            fd.setSize(1080, 480);
        }
    }

    public void actionButtondialogName(ActionEvent evt) {
        fd.orderDesc.setVisible(true);
        fd.orderAsc.setVisible(true);
        JPanel newPane = new JPanel();
        newPane.setLayout(new GridLayout(0, 1));
        String[] values = new String[fd.keywordsUsed.size() + 2];
        values[0] = "Row";
        values[1] = "Column";
        for (int i = 2; i < fd.keywordsUsed.size() + 2; i++) {
            values[i] = fd.keywordsUsed.get(i - 2);
        }

        JTable tableCoord = new JTable();
        boolean[] canEditTry = new boolean[2 + fd.keywordsUsed.size()];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelTable = new DefaultTableModel(values, 0) {
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
        tableCoord.setModel(modelTable);
        //Center columns
        for (int i = 0; i < tableCoord.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableCoord.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableCoord.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableCoord.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        DefaultTableModel model = (DefaultTableModel) tableCoord.getModel();

        List<Molecule> molList = new ArrayList<>();

        for (int i = 2; i < tableCoord.getColumnCount(); i++) {
            fd.usedFiles.clear();
            getUsedFiles(tableCoord.getColumnName(i), "Starts with", false);
            Molecule mole = new Molecule();
            try {
                mole = fd.controller.getTableMolecule(fd.usedFiles, fd.colAndRows, tableCoord.getColumnName(i), Double.parseDouble(fd.temperature));
            } catch (Exception e) {
                fd.dialogNombre.dispose();
                fd.errorText.setVisible(true);
                fd.errorText.setText("Some files were not imported or format file isn't correct.");
                return;
            }
            molList.add(mole);
        }

        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < fd.colAndRows.size(); i++) {
            List<String> val = new ArrayList<>();
            String[] coord = fd.colAndRows.get(i).split(",");
            val.add(coord[0]);
            val.add(coord[1]);
            for (int j = 0; j < molList.size(); j++) {
                double value = molList.get(j).getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                val.add(String.valueOf(df.format(value)));
            }
            rows.add(val);
        }

        rows.forEach((row) -> {
            model.addRow(row.toArray());
        });
        tableCoord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fd.specialTables.add(tableCoord);
        fd.usedTables.add(tableCoord);
        JScrollPane scrollpaneHola = new JScrollPane(tableCoord, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        newPane.add(scrollpaneHola);
        fd.tabbedPane.insertTab(fd.fieldNameValues.getText(), new ImageIcon(""), newPane, null, 1);
        fd.dialogNombre.dispose();
        fd.fieldNameValues.setText("");
    }

    /**
     * This method add element to the JList of the dialogCoordinates.
     */
    public void addValueToList(int rowCount) {
        String regex = "\\d+";
        if (fd.fieldRow.getText().matches(regex) && fd.fieldColumn.getText().matches(regex)) {
            int row = Integer.parseInt(fd.fieldRow.getText());
            int column = Integer.parseInt(fd.fieldColumn.getText());
            if (row <= rowCount && column <= rowCount) {
                if (row >= column) {
                    fd.coorValues.add(row + "," + column);
                    fd.colAndRows.add(row + "," + column);
                    String[] vals = fd.coorValues.toArray(new String[0]);
                    fd.listValues.setListData(vals);
                    fd.errorDialogCoor.setText("");
                } else {
                    fd.errorDialogCoor.setText("<html><body>The value of the row must be greater than the column value.</body></html>");
                }
            } else {
                fd.errorDialogCoor.setText("<html><body>The value of the column and row must be greater than the number gaussians.</body></html>");

            }
        } else {
            fd.errorDialogCoor.setText("The values need to be integer.");
        }
        fd.fieldRow.setText("");
        fd.fieldColumn.setText("");
        fd.fieldRow.requestFocusInWindow();
        fd.fieldRow.requestFocus();
    }

    /**
     * Add the used files to a List.
     */
    public void getUsedFiles(String value, String caseValue, boolean isAdd) {
        switch (caseValue) {
            case "Starts with":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.startsWith(value)) {
                        fd.usedFiles.add(fd.filesData.get(i));
                        if (!fd.names.contains(filename)) {
                            fd.names.add(filename);
                        }
                    }
                }
                if (fd.getSize() != new Dimension(1080, 480)) {
                    fd.setSize(1080, 480);
                }
                if (isAdd) {
                    actionButtonAdd(fd.fieldKeyword.getText());
                }
                break;
            case "Ends with":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.endsWith(value)) {
                        fd.usedFiles.add(fd.filesData.get(i));
                        fd.names.add(filename);
                    }
                }
                if (isAdd) {
                    actionButtonAdd(fd.fieldKeyword.getText());
                }
                break;
            case "Contains":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.contains(value)) {
                        fd.usedFiles.add(fd.filesData.get(i));
                        fd.names.add(filename);
                    }
                }
                actionButtonAdd(fd.fieldKeyword.getText());
                break;
            case "Range starts with":
                if (value.matches("^[0-9]+(\\-[0-9]+)*$")) {
                    String[] values = new String[2];
                    boolean fFileCero = false;
                    boolean sFileCero = false;
                    values = value.split("-");
                    if (values[0].startsWith("0")) {
                        values[0].replace("0", "");
                        fFileCero = true;
                    }

                    if (values[1].startsWith("0")) {
                        values[1].replace("0", "");
                        sFileCero = true;
                    }

                    for (int j = Integer.valueOf(values[0]); j <= Integer.valueOf(values[1]); j++) {
                        fd.usedFiles.clear();
                        fd.names.clear();
                        String charact = "";
                        if (fFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }
                        String separador = "";
                        for (int i = 0; i < fd.files.size(); i++) {
                            String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                            searchSeparator:
                            for (char c : filename.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    separador = String.valueOf(c);
                                    break searchSeparator;
                                }
                            }
                            if (filename.split(separador)[0].equals(charact)) {
                                fd.usedFiles.add(fd.filesData.get(i));
                                if (!fd.names.contains(filename)) {
                                    fd.names.add(filename);
                                }
                            }
                        }
                        actionButtonAdd(charact);
                    }
                }
                break;
            case "Range ends with":
                if (value.matches("^[0-9]+(\\-[0-9]+)*$")) {
                    String[] values = new String[2];
                    boolean fFileCero = false;
                    boolean sFileCero = false;
                    values = value.split("-");
                    if (values[0].startsWith("0")) {
                        values[0].replace("0", "");
                        fFileCero = true;
                    }
                    if (values[1].startsWith("0")) {
                        values[1].replace("0", "");
                        sFileCero = true;
                    }
                    for (int j = Integer.valueOf(values[0]); j <= Integer.valueOf(values[1]); j++) {
                        fd.usedFiles.clear();
                        fd.names.clear();
                        String charact = "";
                        if (fFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }
                        String separador = "";
                        for (int i = 0; i < fd.files.size(); i++) {
                            String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append(filename);
                            sb1 = sb1.reverse();
                            String reversedFilename = sb1.toString();

                            searchSeparator:
                            for (char c : reversedFilename.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    separador = String.valueOf(c);
                                    break searchSeparator;
                                }
                            }
                            String[] separated = filename.split(separador);
                            if (separated[separated.length - 1].equals(charact)) {
                                fd.usedFiles.add(fd.filesData.get(i));
                                if (!fd.names.contains(filename)) {
                                    fd.names.add(filename);
                                }
                            }
                        }
                        actionButtonAdd(charact);
                    }
                }
                break;
            default:
                break;
        }
    }
}
