/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author daviddiaz
 */
public class Order {

    FrameDifferentiator fd;
    Component c;

    public Order(FrameDifferentiator fd) {
        this.fd = fd;
    }

    public void orderDescValuesTable() {
        JTable myTabla = getSelectedTable();
        fd.errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            String[] datas = getGaussianToOrder();
            for (int i = 2; i < myTabla.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                if (bg1 < bg2) {
                    changeOrder(fd.tableGeneric);
                    double a = Double.parseDouble(fd.tableGeneric.getValueAt(Integer.parseInt(datas[0]) - 1, i).toString());
                    double b = Double.parseDouble(fd.tableGeneric.getValueAt(Integer.parseInt(datas[1]) - 1, i).toString());
                    double ax = a;
                    fd.tableGeneric.setValueAt(String.valueOf(b), Integer.parseInt(datas[0]) - 1, i);
                    fd.tableGeneric.setValueAt(String.valueOf(ax), Integer.parseInt(datas[1]) - 1, i);
                    reorderNormalTables(datas[0], datas[1], (i - 2));
                    reorderSpecialTables(datas[0], datas[1], i);
                }
            }
        } else {
            fd.errorText.setText("Please select 2 rows");
            fd.errorText.setVisible(true);
        }
    }

    private void initAverageTable() {

        DefaultTableModel model = (DefaultTableModel) fd.tableGeneric.getModel();

        Vector data = model.getDataVector();
        Vector dataColumn = new Vector();

        for (int i = 0; i < model.getColumnCount(); i++) {
            dataColumn.add(model.getColumnName(i));
        }
        DefaultTableModel model2 = new DefaultTableModel(0, 0) {

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
        model2.setDataVector(data, dataColumn);
        fd.averageTableReorder.setModel(model2);

        for (int i = 0; i < fd.averageTableReorder.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) fd.averageTableReorder.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        fd.averageTableReorder.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        TableColumn column = null;
        for (int i = 0; i < fd.averageTableReorder.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = fd.averageTableReorder.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = fd.averageTableReorder.getColumnModel().getColumn(i);
                column.setMinWidth(200);
            }
        }
        fd.averageTableReorder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public void orderDescAverageTable() {
        if (fd.tableGeneric.getSelectedRowCount() == 2) {
            if (fd.averageTableReorder.getRowCount() == 0) {
                initAverageTable();
            }
            fd.errorDialogCoor.setText("");
            int[] indexs = fd.tableGeneric.getSelectedRows();
            for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(indexs[0], i).toString());
                double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(indexs[1], i).toString());
                if (bg1 < bg2) {
                    double ax = bg1;
                    fd.averageTableReorder.setValueAt(bg2, indexs[0], i);
                    fd.averageTableReorder.setValueAt(ax, indexs[1], i);

                }
            }

            if (!fd.dialogAverageOrder.isVisible()) {
                fd.dialogAverageOrder.setVisible(true);
            }
            fd.dialogAverageOrder.pack();
            fd.dialogAverageOrder.setSize(1080, 720);

        } else {
            fd.errorText.setText("Select 2 rows to order.");

        }
    }

    public void orderAscAverageTable() {
        if (fd.tableGeneric.getSelectedRowCount() == 2) {
            if (fd.averageTableReorder.getRowCount() == 0) {
                initAverageTable();
            }
            fd.errorDialogCoor.setText("");
            Random random = new Random();
            final float hue = random.nextFloat();
            final float saturation = (random.nextInt(2000) + 1000) / 10000f;
            final float luminance = 0.9f;
            final Color color = Color.getHSBColor(hue, saturation, luminance);
            int[] indexs = fd.tableGeneric.getSelectedRows();

            for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(indexs[0], i).toString());
                double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(indexs[1], i).toString());
                final TableColour tce = new TableColour(indexs, color);
                fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(tce);

                if (bg1 > bg2) {
                    double ax = bg1;
                    fd.averageTableReorder.setValueAt(bg2, indexs[0], i);
                    fd.averageTableReorder.setValueAt(ax, indexs[1], i);

                }
            }

            if (!fd.dialogAverageOrder.isVisible()) {
                fd.dialogAverageOrder.setVisible(true);
            }
            fd.dialogAverageOrder.pack();
            fd.dialogAverageOrder.setSize(1080, 720);

        } else {
            fd.errorText.setText("Select 2 rows to order.");
        }
    }

    public void orderAscValuesTable() {
        JTable myTabla = getSelectedTable();
        fd.errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            String[] datas = getGaussianToOrder();
            for (int i = 2; i < myTabla.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                if (bg1 > bg2) {
                    changeOrder(fd.tableGeneric);
                    double a = Double.parseDouble(fd.tableGeneric.getValueAt(Integer.parseInt(datas[0]) - 1, i).toString());
                    double b = Double.parseDouble(fd.tableGeneric.getValueAt(Integer.parseInt(datas[1]) - 1, i).toString());
                    double ax = a;
                    fd.tableGeneric.setValueAt(String.valueOf(b), Integer.parseInt(datas[0]) - 1, i);
                    fd.tableGeneric.setValueAt(String.valueOf(ax), Integer.parseInt(datas[1]) - 1, i);
                    reorderNormalTables(datas[0], datas[1], (i - 2));
                    reorderSpecialTables(datas[0], datas[1], i);
                }
            }
        } else {
            fd.errorText.setText("Please select 2 rows");
            fd.errorText.setVisible(true);
        }
    }

    /**
     * Method used to order the "normal" tables.
     *
     * @param gausian1
     * @param gausian2
     * @param order desc or asc
     */
    private void reorderNormalTables(String gausian1, String gausian2, int index) {
        changeOrder(fd.normalTables.get(index));
        DefaultTableModel model = (DefaultTableModel) fd.normalTables.get(index).getModel();
        for (int i = 2; i < model.getColumnCount(); i++) {
            double bg1 = Double.parseDouble(model.getValueAt(Integer.parseInt(gausian1) - 1, i).toString());
            double bg2 = Double.parseDouble(model.getValueAt(Integer.parseInt(gausian2) - 1, i).toString());
            double aux = bg1;
            model.setValueAt(String.valueOf(bg2), Integer.parseInt(gausian1) - 1, i);
            model.setValueAt(String.valueOf(aux), Integer.parseInt(gausian2) - 1, i);
        }
        fd.normalTables.get(index).repaint();
    }

    /**
     * Method used to order the "special" tables.
     *
     * @param gausian1
     * @param gausian2
     * @param order desc or asc
     */
    private void reorderSpecialTables(String gausian1, String gausian2, int index) {
        for (JTable specialTable : fd.specialTables) {
            List<TableElement> element1 = new ArrayList<>();
            List<TableElement> element2 = new ArrayList<>();
            for (int i = 0; i < specialTable.getRowCount(); i++) {
                String rowValue = specialTable.getValueAt(i, 0).toString();
                String columnValue = specialTable.getValueAt(i, 1).toString();
                if (rowValue.equals(gausian1) || columnValue.equals(gausian1)) {
                    element1.add(new TableElement(rowValue, columnValue, i));
                }
                if (rowValue.equals(gausian2) || columnValue.equals(gausian2)) {
                    element2.add(new TableElement(rowValue, columnValue, i));
                }
            }
            if (element1.size() > element2.size()) {
                reorderElements(specialTable, element2, element1, index);
            } else if (element2.size() > element1.size()) {
                reorderElements(specialTable, element1, element2, index);
            } else {
                reorderElements(specialTable, element1, element2, index);
            }

        }
    }

    /**
     * Method used to check wether a value has a pair or not
     *
     * @param specialTable myTable that is going to be ordered
     * @param element1 values used as keywords
     * @param element2 values used as keywords
     */
    public void reorderElements(JTable specialTable, List<TableElement> element1, List<TableElement> element2, int index) {
        List<TableElement> elements = new ArrayList<>();
        for (TableElement elementType1 : element1) {
            next:
            for (int j = 0; j < element2.size(); j++) {
                if (elementType1.column.equals(element2.get(j).column) || elementType1.column.equals(element2.get(j).row)
                        || elementType1.row.equals(element2.get(j).column) || elementType1.row.equals(element2.get(j).row)) {
                    double bg1 = Double.parseDouble(specialTable.getValueAt(elementType1.indexRow, index).toString());
                    double bg2 = Double.parseDouble(specialTable.getValueAt(element2.get(j).indexRow, index).toString());
                    double aux = bg1;
                    specialTable.setValueAt(String.valueOf(bg2), elementType1.indexRow, index);
                    specialTable.setValueAt(String.valueOf(aux), element2.get(j).indexRow, index);
                    element2.remove(element2.get(j));
                    break next;
                } else {
                    if (j == element2.size() - 1) {
                        elements.add(elementType1);
                    }
                }
            }
        }
        if (fd.errorText.getText().length() == 0) {
            fd.errorText.setVisible(false);
            fd.errorText.setText("The following values will not be ordered: ");
            if (!elements.isEmpty()) {
                fd.errorText.setVisible(true);
                for (TableElement element : elements) {
                    fd.errorText.setText(fd.errorText.getText() + " " + element.row + "," + element.column + "  ");
                }
            }
            if (!element2.isEmpty()) {
                for (TableElement element : element2) {
                    fd.errorText.setVisible(true);
                    fd.errorText.setText(fd.errorText.getText() + " " + element.row + "," + element.column + "  ");
                }
            }
        }
    }

    /**
     *
     * @return selected myTable
     */
    private JTable getSelectedTable() {
        JPanel myPanel = (JPanel) (fd.tabbedPane.getSelectedComponent());
        JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
        JViewport viewport = scrollPane.getViewport();
        JTable myTable = (JTable) viewport.getView();
        return myTable;
    }

    /**
     * Used to order values
     *
     * @return gaussian values ordered
     */
    private String[] getGaussianToOrder() {
        JTable myTable = getSelectedTable();
        DefaultTableModel model = (DefaultTableModel) myTable.getModel();
        int[] rows = myTable.getSelectedRows();
        String[] firstRow = String.valueOf(model.getDataVector().elementAt(rows[0])).replace("[", "").split(",");
        String[] secondRow = String.valueOf(model.getDataVector().elementAt(rows[1])).replace("[", "").split(",");
        String firstRow1 = firstRow[0].replace(" ", "");
        String firstRow2 = firstRow[1].replace(" ", "");
        String secondRow1 = secondRow[0].replace(" ", "");
        String secondRow2 = secondRow[1].replace(" ", "");
        String gausian1;
        String gausian2;
        if (firstRow1.equals(secondRow1) || firstRow1.equals(secondRow2)) {
            gausian1 = firstRow2;
        } else {
            gausian1 = firstRow1;
        }
        if (secondRow1.equals(firstRow1) || secondRow1.equals(firstRow2)) {
            gausian2 = secondRow2;
        } else {
            gausian2 = secondRow1;
        }
        String[] datas = new String[]{gausian1, gausian2};
        return datas;
    }

    private void changeOrder(JTable table) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setComparator(0, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sorter.setSortsOnUpdates(true);
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
    }
}

class TableColour
        extends javax.swing.table.DefaultTableCellRenderer {

    int[] rows;
    Color color;

    public TableColour(int[] rows, Color color) {
        this.rows = rows;
        this.color = color;
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == rows[0] || row == rows[1]) {
            cellComponent.setBackground(color);
            cellComponent.setForeground(Color.BLACK);

        } else {
            cellComponent.setBackground(Color.white);
            cellComponent.setForeground(Color.BLACK);
        }

        return cellComponent;

    }

}
