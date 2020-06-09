/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author daviddiaz
 */
public class InsertValuesTable {

    MainFrame mf;

    public InsertValuesTable(MainFrame mf) {
        this.mf = mf;

    }

    public void insertValues(String path) {
        List<Object[]> lines = mf.controller.readFile(path);
        initTable(Arrays.copyOf(lines.get(0), lines.get(0).length, String[].class));
        lines.remove(0);

        DefaultTableModel model = (DefaultTableModel) mf.table.getModel();
        lines.forEach(line -> {
            model.addRow(line);
        });
        mf.pack();
        model.fireTableStructureChanged();

        mf.table.setModel(model);
        mf.table.setRowSelectionAllowed(false);
        mf.table.setColumnSelectionAllowed(true);

        mf.repaint();
        mf.revalidate();

        mf.table.revalidate();
        mf.table.repaint();

    }

    public void initTable(String[] values) {

        mf.table = new JTable();
        DefaultTableModel modelGeneric = new DefaultTableModel(values, 0) {

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
        mf.table.setModel(modelGeneric);
        //Center columns
        for (int i = 0; i < mf.table.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            mf.table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) mf.table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        mf.table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        mf.table.getTableHeader().setReorderingAllowed(false);

        TableColumnModel tcm = mf.table.getColumnModel();

        tcm.getColumn(1).setPreferredWidth(400);
        mf.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollpaneGeneric = new JScrollPane(mf.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mf.panelGeneric.add(scrollpaneGeneric);
        mf.jTabbedPane1.add(mf.panelGeneric, "Values");
    }

}
