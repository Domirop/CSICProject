/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.FileData;
import Model.Atomo.Matter;
import Model.Model;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class FrameDifferentiator extends javax.swing.JFrame {

    /**
     * Creates new form FrameDifferentiator
     */
    private List<String> files = new ArrayList<>();
    private List<File> filesData = new ArrayList<>();
    private JPanel panelGeneric = new JPanel();
    private List<String> names = new ArrayList<>();
    private List<File> usedFiles = new ArrayList<>();
    private List<JTable> usedTables = new ArrayList<>();

    Model modelo = new Model();
    JTable tableGeneric;

    public FrameDifferentiator() {
        initComponents();
    }

    public FrameDifferentiator(List<String> files, List<File> filesData) {
        initComponents();
        panelGeneric.setLayout(new GridLayout(0, 1));
        tabbedPane.addTab("Generic", panelGeneric);
        tabbedPane.setVisible(false);
        this.files = files;
        this.filesData = filesData;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboOptions = new javax.swing.JComboBox<>();
        fieldKeyword = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        deleteButtton = new javax.swing.JButton();
        buttonExportCSV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Search by keyword:");

        comboOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Starts with", "Ends with", "Contains", "Range" }));

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        deleteButtton.setText("Reset");
        deleteButtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButttonActionPerformed(evt);
            }
        });

        buttonExportCSV.setText("Export to CSV");
        buttonExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fieldKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(buttonExportCSV)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButtton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd)
                    .addComponent(deleteButtton)
                    .addComponent(buttonExportCSV))
                .addGap(18, 18, 18)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        names.clear();
        usedFiles.clear();
        JPanel panel = new JPanel();
        tabbedPane.setVisible(true);

        getUsedFiles();

        panel.setLayout(new GridLayout(0, 1));
        JTable table = addRowsToTable(initTablesDifferentiators());
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(scrollpane);
        tabbedPane.addTab(fieldKeyword.getText(), panel);

        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

        genericTable(usedFiles);
        usedFiles.clear();
        names.clear();

        usedTables.add(table);
        revalidate();
        pack();
    }//GEN-LAST:event_buttonAddActionPerformed

    private JTable initTablesDifferentiators() {
        JTable table = new JTable();
        table.setAutoCreateRowSorter(true);
        List<String> singleNames = new ArrayList<>();
        singleNames.add("Gaussian");
        singleNames.add("Atom");

        for (String file : names) {
            if (!singleNames.contains(file)) {
                singleNames.add(file);
            }
        }
        DefaultTableModel model = new DefaultTableModel(
                singleNames.toArray(new String[0]),
                0) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

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

    private JTable addRowsToTable(JTable table) {

        JTable tableWithElements = table;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<FileData> fileData = modelo.getFileData(usedFiles);

        List<Object> data = new ArrayList<>();

        for (int i = 0; i < fileData.get(0).getAtoms().size(); i++) {
            data.clear();

            for (int j = 1; j < fileData.size() + 1; j++) {
                if (j == 1) {
                    data.add(fileData.get(j - 1).getAtoms().get(i).getGausianData());
                    data.add(fileData.get(j - 1).getAtoms().get(i).getAtom());

                }
                data.add(fileData.get(j - 1).getAtoms().get(i).getIsotropic());

            }
            model.addRow(data.toArray(new Object[0]));

        }

        return tableWithElements;
    }

    private void getUsedFiles() {
        if (String.valueOf(comboOptions.getSelectedItem()).equals("Starts with")) {
            for (int i = 0; i < files.size(); i++) {
                String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                if (filename.startsWith(fieldKeyword.getText())) {
                    usedFiles.add(filesData.get(i));
                    if (!names.contains(filename)) {
                        names.add(filename);
                    }

                }
            }
        } else if (String.valueOf(comboOptions.getSelectedItem()).equals("Ends with")) {
            for (int i = 0; i < files.size(); i++) {
                String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                if (filename.endsWith(fieldKeyword.getText())) {
                    usedFiles.add(filesData.get(i));
                    names.add(filename);
                }
            }

        } else if (String.valueOf(comboOptions.getSelectedItem()).equals("Contains")) {
            for (int i = 0; i < files.size(); i++) {
                String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                if (filename.contains(fieldKeyword.getText())) {
                    usedFiles.add(filesData.get(i));
                    names.add(filename);
                }
            }
        } else {

        }
    }

    private void deleteButttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButttonActionPerformed
        tabbedPane.removeAll();
        tabbedPane.addTab("Generic", panelGeneric);
        tabbedPane.setVisible(false);
        panelGeneric.removeAll();
        tableGeneric = null;
    }//GEN-LAST:event_deleteButttonActionPerformed

    private void buttonExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportCSVActionPerformed
        usedTables.add(tableGeneric);
        for (JTable usedTable : usedTables) {
            String[] data = new String[usedTable.getRowCount() + 1];

            System.out.println(usedTable.getColumnCount());
            for (int i = 0; i < usedTable.getColumnCount(); i++) {
                data[0] = (String) usedTable.getColumnModel().getColumn(i).getHeaderValue() + ",";

            }
            System.out.println(data[0]);
            for (int count = 1; count <= usedTable.getRowCount(); count++) {
                for (int i = 1; i <= usedTable.getColumnCount(); i++) {
                    data[i] = usedTable.getValueAt(count - 1, i - 1).toString();

                }
                //numdata.add(model.getValueAt(count, 0).toString());
            }

        }


    }//GEN-LAST:event_buttonExportCSVActionPerformed

    private void genericTable(List<File> usedFiles) {
        Matter materia = modelo.getMateriElement(usedFiles, fieldKeyword.getText());

        String[] values = new String[materia.getResult().size() + 1];
        values[0] = "Keyword";
        for (int i = 1; i <= materia.getResult().size(); i++) {
            values[i] = materia.getResult().get(i - 1).getAtomo();
        }
        if (tableGeneric == null) {
            initGenericTable(values);
            addElementsToGeneric(materia);
            JScrollPane scrollpaneGeneric = new JScrollPane(tableGeneric);
            panelGeneric.add(scrollpaneGeneric);
        } else {
            addElementsToGeneric(materia);
        }
    }

    private void initGenericTable(String[] values) {
        tableGeneric = new JTable();
        DefaultTableModel modelGeneric = new DefaultTableModel(values, 0) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

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
        tableGeneric.setAutoCreateRowSorter(true);
        tableGeneric.setModel(modelGeneric);
        //Center columns
        for (int i = 0; i < tableGeneric.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableGeneric.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableGeneric.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableGeneric.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void addElementsToGeneric(Matter materia) {
        DefaultTableModel model = (DefaultTableModel) tableGeneric.getModel();
        Object[] value = new String[materia.getResult().size() + 1];
        value[0] = materia.getDifferentiator();
        for (int i = 1; i <= materia.getResult().size(); i++) {
            value[i] = String.valueOf(materia.getResult().get(i - 1).getValue());
        }
        model.addRow(value);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonExportCSV;
    private javax.swing.JComboBox<String> comboOptions;
    private javax.swing.JButton deleteButtton;
    private javax.swing.JTextField fieldKeyword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
