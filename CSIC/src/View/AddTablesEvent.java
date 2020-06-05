/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.AtomTable;
import Model.Atomo.FileData;
import Model.Atomo.Molecule;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
     *
     * @param fieldText
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
                    List<FileData> fileData = fd.controller.getFileData(fd.usedFiles, Double.parseDouble(fd.temperature), Double.parseDouble(fd.maxValue));
                    JTable table = td.addRowsToTable(td.initTablesDifferentiators(fileData), fileData);
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
                            fd.labelSelect.setVisible(true);
                            fd.comboSelectRowsOrColumns.setVisible(true);
                            fd.buttonRemoveColumn.setVisible(true);
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
                        }
                        table.getTableHeader().setReorderingAllowed(false);
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        panel.add(scrollpane);
                        fd.tabbedPane.addTab(fieldText, panel);
                        fd.tabbedPane.setSelectedIndex(fd.tabbedPane.getTabCount() - 1);
                        avg.averageTable(fd.usedFiles);
                        if (fd.usedTables.isEmpty()) {
                            fd.usedTables.add(fd.tableGeneric);
                        }
                        fd.normalTables.add(table);
                        fd.usedTables.add(table);
                        fd.revalidate();
                        scf.addSCFTable(fieldText);
                        fd.orderDesc.setVisible(true);
                        fd.orderAsc.setVisible(true);
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
        if (fd.getSize() != new Dimension(1080, 720)) {
            fd.setSize(1080, 720);
        }
    }

    public void actionButtondialogName(ActionEvent evt) {
        JPanel newPane = new JPanel();
        newPane.setLayout(new GridLayout(0, 1));
        String[] values = new String[fd.keywordsUsed.size() + 2];
        values[0] = "Row";
        values[1] = "Column";
        for (int i = 2; i < fd.keywordsUsed.size() + 2; i++) {
            values[i] = fd.keywordsUsed.get(i - 2);
        }

        JTable tableCoord = new JTable();

        DefaultTableModel modelTable = new DefaultTableModel(values, 0) {

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
        tableCoord.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel model = (DefaultTableModel) tableCoord.getModel();

        List<Molecule> molList = new ArrayList<>();

        for (int i = 2; i < tableCoord.getColumnCount(); i++) {
            fd.usedFiles.clear();
            getUsedFiles(tableCoord.getColumnName(i), "Starts with", false);
            if (fd.usedFiles.size() == 0) {
                getUsedFiles(tableCoord.getColumnName(i), "Ends with", false);
            }
            if (fd.usedFiles.size() == 0) {
                getUsedFiles(tableCoord.getColumnName(i), "Contains", false);

            }

            Molecule mole = new Molecule();
            try {
                mole = fd.controller.getTableMolecule(fd.usedFiles, fd.colAndRows, tableCoord.getColumnName(i), Double.parseDouble(fd.temperature));
            } catch (Exception e) {
                fd.dialogNombre.dispose();
                fd.errorText.setVisible(true);
                e.printStackTrace();
                fd.errorText.setText("Some files were not imported or format file isn't correct.");
                return;
            }
            molList.add(mole);
        }
        List<List<Object>> rows = new ArrayList<>();
        for (int i = 0; i < fd.colAndRows.size(); i++) {
            List<Object> val = new ArrayList<>();
            String[] coord = fd.colAndRows.get(i).split(",");
            val.add(Integer.parseInt(coord[0]));
            val.add(Integer.parseInt(coord[1]));
            for (int j = 0; j < molList.size(); j++) {
                double value = molList.get(j).getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                val.add(Double.parseDouble(String.valueOf(df.format(value))));
            }
            rows.add(val);
        }

        rows.forEach((row) -> {
            model.addRow(row.toArray());
        });

        int count = 0;
        for (int j = 0; j < fd.normalTables.size(); j++) {
            List<List<Object>> newRow = new ArrayList<>();
            DefaultTableModel df = (DefaultTableModel) fd.normalTables.get(j).getModel();

            Molecule molecule = molList.get(j);
            System.out.println(molecule.getFilesData().size());
            for (int l = 0; l < fd.colAndRows.size(); l++) {
                List<Object> val = new ArrayList<>();
                val.add(fd.colAndRows.get(l));
                val.add("J");
                for (int k = 0; k < molecule.getFilesData().size(); k++) {
                    for (int i = 2; i < df.getColumnCount(); i++) {
                        if (molecule.getFilesData().get(k).getFileName().equals(df.getColumnName(i) + ".log")) {
                            val.add(molecule.getFilesData().get(k).getAtomsTable().get(l).getValue());
                        }
                    }

                }
                newRow.add(val);

            }

            newRow.forEach((newRows) -> {
                df.addRow(newRows.toArray());
            });
            count += df.getColumnCount() - 2;

            fd.normalTables.get(j).repaint();
            fd.normalTables.get(j).revalidate();
            System.out.println("--------");
        }

        tableCoord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        fd.specialTables.add(tableCoord);

        fd.usedTables.add(tableCoord);
        JScrollPane scrollpaneHola = new JScrollPane(tableCoord, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        newPane.add(scrollpaneHola);

        fd.tabbedPane.insertTab(fd.fieldNameValues.getText(), new ImageIcon(""), newPane, null, 1);
        fd.dialogNombre.dispose();

        fd.fieldNameValues.setText(
                "");
    }

    /**
     * This method add element to the JList of the dialogCoordinates.
     *
     * @param rowCount numbers of rowCount.
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
                fd.errorDialogCoor.setText("<html><body>The value of the column and row must be smaller than the number of gaussians(" + rowCount + ").</body></html>");

            }
        } else {
            fd.errorDialogCoor.setText("The values must be integer.");
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
        List<String> names = new ArrayList<>();
        switch (caseValue) {
            case "Starts with":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.startsWith(value)) {
                        names.add(filename);
                        fd.usedFiles.add(fd.filesData.get(i));
                        if (!fd.names.contains(filename)) {
                            fd.names.add(filename);
                        }
                    }
                }
                if (fd.getSize() != new Dimension(1080, 720)) {
                    fd.setSize(1080, 720);
                }
                if (isAdd) {
                    actionButtonAdd(fd.fieldKeyword.getText());
                    if (!fd.allFiles.contains(names)) {
                        fd.allFiles.add(names);
                    }
                }
                break;
            case "Ends with":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.endsWith(value)) {
                        names.add(filename);
                        fd.usedFiles.add(fd.filesData.get(i));
                        if (!fd.names.contains(filename)) {
                            fd.names.add(filename);
                        }
                    }
                }
                if (isAdd) {
                    actionButtonAdd(fd.fieldKeyword.getText());
                    if (!fd.allFiles.contains(names)) {
                        fd.allFiles.add(names);
                    }
                }
                break;
            case "Contains":
                for (int i = 0; i < fd.files.size(); i++) {
                    String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                    if (filename.contains(value)) {
                        names.add(filename);
                        fd.usedFiles.add(fd.filesData.get(i));
                        if (!fd.names.contains(filename)) {
                            fd.names.add(filename);
                        }
                    }
                }
                if (isAdd) {
                    actionButtonAdd(fd.fieldKeyword.getText());
                    if (!fd.allFiles.contains(names)) {
                        fd.allFiles.add(names);
                    }
                }
                break;
            case "Range starts with":
                if (value.matches("^[0-9]?[0-9]*(-)[0-9]?[0-9]*$")) {
                    String[] values = new String[2];
                    boolean fFileCero = false;
                    boolean sFileCero = false;
                    values = value.split("-");

                    if (values[0].startsWith("0")) {
                        values[0] = values[0].replace("0", "");
                        fFileCero = true;
                    }

                    if (values[1].startsWith("0")) {
                        values[1] = values[1].replace("0", "");
                        sFileCero = true;
                    }

                    for (int j = Integer.parseInt(values[0]); j <= Integer.parseInt(values[1]); j++) {
                        fd.usedFiles.clear();
                        fd.names.clear();
                        String charact1 = "";
                        String charact2 = "";
                        if (fFileCero == true && j < 10) {
                            charact1 = "0" + String.valueOf(j);
                        } else {
                            charact1 = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact2 = "0" + String.valueOf(j);
                        } else {
                            charact2 = String.valueOf(j);
                        }

                        String separador = "";
                        List<String> allNames = new ArrayList<>();
                        for (int i = 0; i < fd.files.size(); i++) {
                            String filename = fd.files.get(i).contains(".log") ? fd.files.get(i).replace(".log", "") : fd.files.get(i).replace(".txt", "");
                            searchSeparator:
                            for (char c : filename.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    separador = String.valueOf(c);
                                    break searchSeparator;
                                }
                            }
                            if (filename.split(separador)[0].equals(charact1) || filename.split(separador)[0].equals(charact2)) {
                                allNames.add(filename);
                                fd.usedFiles.add(fd.filesData.get(i));
                                if (!fd.names.contains(filename)) {
                                    fd.names.add(filename);
                                }
                            }
                        }
                        if (!fd.allFiles.contains(allNames)) {
                            fd.allFiles.add(allNames);
                        }
                        actionButtonAdd(String.valueOf(j));
                    }
                } else {
                    fd.errorText.setText("The format is (number-number)");
                }
                break;
            case "Range ends with":
                if (value.matches("^[0-9]?[0-9]*(-)[0-9]?[0-9]*$")) {
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
                        String charact1 = "";
                        String charact2 = "";
                        if (fFileCero == true && j < 10) {
                            charact1 = "0" + String.valueOf(j);
                        } else {
                            charact1 = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact2 = "0" + String.valueOf(j);
                        } else {
                            charact2 = String.valueOf(j);
                        }
                        String separador = "";
                        List<String> allNames = new ArrayList<>();
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
                            if (separated[separated.length - 1].equals(charact1) || separated[separated.length - 1].equals(charact2)) {
                                allNames.add(filename);
                                fd.usedFiles.add(fd.filesData.get(i));
                                if (!fd.names.contains(filename)) {
                                    fd.names.add(filename);
                                }
                            }
                        }
                        if (!fd.allFiles.contains(allNames)) {
                            fd.allFiles.add(allNames);
                        }
                        actionButtonAdd(String.valueOf(j));
                    }
                }
                break;
            default:
                break;
        }
    }

    public void removeRow(List<Integer> indexs) {
        for (int i = indexs.size() - 1; i >= 0; i--) {
            int index = indexs.get(i);
            fd.normalTables.forEach((normalTable) -> {
                DefaultTableModel def = (DefaultTableModel) normalTable.getModel();
                def.removeRow(index);
                normalTable.repaint();
            });
            DefaultTableModel def = (DefaultTableModel) fd.tableGeneric.getModel();
            def.removeRow(index);
            fd.tableGeneric.repaint();
        }
    }

    public void removeColumn() {
        JPanel myPanel = (JPanel) (fd.tabbedPane.getSelectedComponent());
        JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
        JViewport viewport = scrollPane.getViewport();
        JTable table = (JTable) viewport.getView();
        if (table.getColumnCount() > 3) {
            if (table.getSelectedColumn() != 0 && table.getSelectedColumn() != 1) {
                fd.errorText.setText("");
                if (table.getSelectedColumnCount() > 0) {
                    fd.usedFiles.clear();
                    List<File> aux = new ArrayList<>();
                    for (int i = 2; i < table.getColumnCount(); i++) {
                        String tableName = table.getColumnName(i);
                        aux.addAll(fd.filesData.stream()
                                .filter((File v) -> v.getName().contains(tableName))
                                .collect(Collectors.toList()));
                    }

                    int index = 0;
                    for (int i = 2; i < fd.tableGeneric.getColumnCount(); i++) {
                        if (aux.get(0).getName().contains(fd.tableGeneric.getColumnName(i))) {
                            index = i;
                        }
                    }

                    String columnSelected = table.getColumnName(table.getSelectedColumn());
                    table.removeColumn(table.getColumnModel().getColumn(table.getSelectedColumn()));
                    fd.usedFiles.addAll(aux.stream().filter((File v) -> !v.getName().contains(columnSelected)).collect(Collectors.toList()));
                    Molecule molecule = fd.controller.getMolecule(fd.usedFiles, fd.fieldKeyword.getText(), Double.parseDouble(fd.temperature), Double.parseDouble(fd.maxValue));
                    DefaultTableModel model = (DefaultTableModel) fd.tableGeneric.getModel();
                    for (int i = 0; i < fd.tableGeneric.getRowCount(); i++) {
                        double value = molecule.getResult().get(i).getValue();
                        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(fd.getLocale());
                        otherSymbols.setDecimalSeparator('.');
                        otherSymbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
                        df.setRoundingMode(RoundingMode.CEILING);
                        model.setValueAt(Double.parseDouble(df.format(value)), i, index);
                    }
                    JTableHeader th = fd.tableGeneric.getTableHeader();
                    TableColumnModel tcm = th.getColumnModel();
                    TableColumn tc = tcm.getColumn(index);
                    tc.setHeaderValue(table.getColumnName(2));
                    fd.tableGeneric.setTableHeader(th);
                    th.repaint();
                    table.revalidate();
                    table.repaint();
                } else {
                    fd.errorText.setText("Please select one column");
                }
            } else {
                fd.errorText.setText("This column cannot be deleted");
            }
        } else {
            fd.errorText.setText("Last column cannot be deleted");
        }
    }
}
