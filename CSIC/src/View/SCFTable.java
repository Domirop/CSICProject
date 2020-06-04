/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.FileData;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author daviddiaz
 */
public class SCFTable {
    //Métodos relacionados con la tabla SCF

    FrameDifferentiator fd;

    public SCFTable(FrameDifferentiator fd) {
        this.fd = fd;
    }

    public void addSCFTable(String keyword) {
        fd.tabPaneSCF.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        fd.tabPaneSCF.add(keyword, initSCFTable());
    }

    public JPanel initSCFTable() {
        ArrayList<String> nameFiles = new ArrayList<>();
        ArrayList<Object> contribution = new ArrayList<>();
        ArrayList<Object> SCF = new ArrayList<>();
        ArrayList<Object> convertedValues = new ArrayList<>();
        nameFiles.add("Values");
        contribution.add("Boltzmann population");
        SCF.add("Energy (Hartree)");
        convertedValues.add("Relative energy (kJ/mol)");

        for (FileData fileData : fd.controller.getFileData(fd.usedFiles, Double.valueOf(fd.temperature), Double.parseDouble(fd.maxValue))) {
            nameFiles.add(fileData.getFileName());
            contribution.add(fileData.getContribution());
            SCF.add(fileData.getEnergyValue());
            convertedValues.add(fileData.getRelativeEnergy());

        }
        Object[][] data = new Object[][]{
            contribution.toArray(new Object[0]),
            SCF.toArray(new Object[0]),
            convertedValues.toArray(new Object[0])

        };

        String[] headers = nameFiles.toArray(new String[0]);
        JTable tableSCF = new JTable(data, headers);

        DefaultTableModel newModel = new DefaultTableModel(
                data, headers
        ) {
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
        }
    ;
        tableSCF.setModel(newModel);

        for (int i = 0; i < tableSCF.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableSCF.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableSCF.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableSCF.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        TableColumn column = null;
        for (int i = 0; i < tableSCF.getColumnCount(); i++) {
            if (i == 0) {
                column = tableSCF.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = tableSCF.getColumnModel().getColumn(i);
                column.setMinWidth(250);
            }
        }
        tableSCF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 1; i < tableSCF.getColumnCount() - 1; i++) {
            double var1 = Double.parseDouble(String.valueOf(tableSCF.getValueAt(0, i)));
            double var2 = Double.parseDouble(String.valueOf(tableSCF.getValueAt(0, i + 1)));

            if (var1 < var2) {
                tableSCF.moveColumn(i, i + 1);
                tableSCF.repaint();
                tableSCF.revalidate();
                i = 1;
            }

        }

        JScrollPane scrollpaneGeneric = new JScrollPane(tableSCF, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(1000, 100));
        panel.add(scrollpaneGeneric);
        return panel;
    }
}
