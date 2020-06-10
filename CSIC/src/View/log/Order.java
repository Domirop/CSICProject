/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.log;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

    public void changeOrderAverageTable(String[] gaussians, int index) {
        DefaultTableModel model = (DefaultTableModel) fd.averageTableReorder.getModel();
        int index1 = 0;
        int index2 = 0;
        for (int j = 0; j < model.getRowCount(); j++) {
            if (String.valueOf(model.getValueAt(j, 0)).equals(gaussians[0])) {
                index1 = j;
            } else if (String.valueOf(model.getValueAt(j, 0)).equals(gaussians[1])) {
                index2 = j;
            }
        }
        double a = Double.parseDouble(model.getValueAt(index1, index).toString());
        double b = Double.parseDouble(model.getValueAt(index2, index).toString());
        double ax = a;
        model.setValueAt(b, index1, index);
        model.setValueAt(ax, index2, index);
        fd.averageTableReorder.repaint();
    }

    public void orderDescValuesTable() {
        JTable myTabla = getSelectedTable();
        DefaultTableModel model = (DefaultTableModel) fd.tableGeneric.getModel();
        fd.errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            String[] datas = getGaussianToOrder();
            if (fd.multiTable == true) {
                if (orderSecondTable(datas)) {
                    fd.errorText.setText("");
                    for (int i = 2; i < myTabla.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                        double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                        if (bg1 < bg2) {
                            int index1 = 0;
                            int index2 = 0;
                            for (int j = 0; j < model.getRowCount(); j++) {
                                if (String.valueOf(model.getValueAt(j, 0)).equals(datas[0])) {
                                    index1 = j;
                                } else if (String.valueOf(model.getValueAt(j, 0)).equals(datas[1])) {
                                    index2 = j;
                                }
                            }
                            changeOrder(fd.tableGeneric);
                            double a = Double.parseDouble(fd.tableGeneric.getValueAt(index1, i).toString());
                            double b = Double.parseDouble(fd.tableGeneric.getValueAt(index2, i).toString());
                            double ax = a;
                            model.setValueAt(b, index1, i);
                            model.setValueAt(ax, index2, i);
                            changeOrderAverageTable(datas, i);
                            reorderNormalTables(index1, index2, (i - 2));
                            reorderSpecialTables(datas[0], datas[1], i);
                        }
                    }
                } else {
                    fd.errorText.setText("This value cannot be ordered");
                }
            } else {
                for (int i = 2; i < myTabla.getColumnCount(); i++) {
                    double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                    double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                    if (bg1 < bg2) {
                        int index1 = 0;
                        int index2 = 0;
                        for (int j = 0; j < model.getRowCount(); j++) {
                            if (String.valueOf(model.getValueAt(j, 0)).equals(datas[0])) {
                                index1 = j;
                            } else if (String.valueOf(model.getValueAt(j, 0)).equals(datas[1])) {
                                index2 = j;
                            }
                        }
                        changeOrder(fd.tableGeneric);
                        double a = Double.parseDouble(fd.tableGeneric.getValueAt(index1, i).toString());
                        double b = Double.parseDouble(fd.tableGeneric.getValueAt(index2, i).toString());
                        double ax = a;
                        model.setValueAt(b, index1, i);
                        model.setValueAt(ax, index2, i);
                        changeOrderAverageTable(datas, i);
                        reorderNormalTables(index1, index2, (i - 2));
                        reorderSpecialTables(datas[0], datas[1], i);
                    }
                }
            }
        } else {
            fd.errorText.setText("Please select 2 rows");
            fd.errorText.setVisible(true);
        }
    }

    public void reloadAverageTable() {
        DefaultTableModel model2 = (DefaultTableModel) fd.averageTableReorder.getModel();
        String[] columnNames = new String[fd.tableGeneric.getColumnCount()];
        Object[][] rows = new Object[fd.averageTable.getRowCount()][fd.averageTable.getColumnCount()];
        for (int i = 0;
                i < fd.averageTable.getRowCount();
                i++) {
            for (int j = 0; j < fd.averageTable.getColumnCount(); j++) {
                if (i == 0) {
                    columnNames[j] = fd.averageTable.getColumnName(j);
                }
                rows[i][j] = fd.averageTable.getValueAt(i, j).toString();

            }
        }

        model2.setDataVector(rows, columnNames);
        fd.averageTableReorder.setModel(model2);
    }

    public void initAverageTable() {
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
        String[] columnNames = new String[fd.tableGeneric.getColumnCount()];
        Object[][] rows = new Object[fd.tableGeneric.getRowCount()][fd.tableGeneric.getColumnCount()];
        for (int i = 0; i < fd.tableGeneric.getRowCount(); i++) {
            for (int j = 0; j < fd.tableGeneric.getColumnCount(); j++) {
                if (i == 0) {
                    columnNames[j] = fd.tableGeneric.getColumnName(j);
                }
                rows[i][j] = fd.tableGeneric.getValueAt(i, j);
            }
        }
        model2.setDataVector(rows, columnNames);
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
            List<String> values = new ArrayList<>();
            String firstValue = String.valueOf(fd.tableGeneric.getValueAt(fd.tableGeneric.getSelectedRows()[0], 0));
            String secondValue = String.valueOf(fd.tableGeneric.getValueAt(fd.tableGeneric.getSelectedRows()[1], 0));
            values.add(firstValue);
            values.add(secondValue);
            fd.indexOrderedValDesc.add(values.toArray(new String[0]));
            if (fd.averageTableReorder.getRowCount() == 0) {
                initAverageTable();
            }
            fd.errorDialogCoor.setText("");
            Random random = new Random();
            final float hue = random.nextFloat();
            final float saturation = (random.nextInt(2000) + 1000) / 10000f;
            final float luminance = 0.9f;
            final Color color = Color.getHSBColor(hue, saturation, luminance);
            for (String[] indexs : fd.indexOrderedValDesc) {
                DefaultTableModel model = (DefaultTableModel) fd.averageTableReorder.getModel();
                Integer index1 = null;
                Integer index2 = null;
                for (int i = 0; i < fd.averageTableReorder.getRowCount(); i++) {
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[0])) {
                        index1 = i;
                    }
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[1])) {
                        index2 = i;
                    }
                }
                if (index1 != null && index2 != null) {
                    for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(index1, i).toString());
                        double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(index2, i).toString());
                        int[] colorIndexs = new int[]{index1, index2};
                        final TableColour tce = new TableColour(colorIndexs, color);
                        fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(tce);
                        if (bg1 < bg2) {
                            double ax = bg1;
                            model.setValueAt(bg2, index1, i);
                            model.setValueAt(ax, index2, i);
                        }
                    }
                } else {
                    fd.errorText.setText("This values cannot be ordered.");
                }
            }

            if (!fd.dialogAverageOrder.isVisible()) {
                fd.dialogAverageOrder.setVisible(true);
            }
            fd.orderAverage = true;
            fd.dialogAverageOrder.pack();
            fd.dialogAverageOrder.setSize(1080, 720);

        } else {
            fd.errorText.setText("Select 2 rows to order.");
        }
    }

    public void orderAscAverageTable() {
        if (fd.tableGeneric.getSelectedRowCount() == 2) {
            List<String> values = new ArrayList<>();
            String firstValue = String.valueOf(fd.tableGeneric.getValueAt(fd.tableGeneric.getSelectedRows()[0], 0));
            String secondValue = String.valueOf(fd.tableGeneric.getValueAt(fd.tableGeneric.getSelectedRows()[1], 0));
            values.add(firstValue);
            values.add(secondValue);
            fd.indexOrderedValAsc.add(values.toArray(new String[0]));
            if (fd.averageTableReorder.getRowCount() == 0) {
                initAverageTable();
            }
            fd.errorDialogCoor.setText("");
            Random random = new Random();
            final float hue = random.nextFloat();
            final float saturation = (random.nextInt(2000) + 1000) / 10000f;
            final float luminance = 0.9f;
            final Color color = Color.getHSBColor(hue, saturation, luminance);
            for (String[] indexs : fd.indexOrderedValAsc) {
                DefaultTableModel model = (DefaultTableModel) fd.averageTableReorder.getModel();
                Integer index1 = null;
                Integer index2 = null;
                for (int i = 0; i < fd.averageTableReorder.getRowCount(); i++) {
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[0])) {
                        index1 = i;
                    }
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[1])) {
                        index2 = i;
                    }
                }
                if (index1 != null && index2 != null) {
                    for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(index1, i).toString());
                        double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(index2, i).toString());
                        int[] colorIndexs = new int[]{index1, index2};
                        final TableColour tce = new TableColour(colorIndexs, color);
                        fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(tce);
                        if (bg1 > bg2) {
                            double ax = bg1;
                            model.setValueAt(bg2, index1, i);
                            model.setValueAt(ax, index2, i);
                        }
                    }
                } else {
                    fd.errorText.setText("This values cannot be ordered.");
                }
            }

            if (!fd.dialogAverageOrder.isVisible()) {
                fd.dialogAverageOrder.setVisible(true);
            }
            fd.orderAverage = true;
            fd.dialogAverageOrder.pack();
            fd.dialogAverageOrder.setSize(1080, 720);

        } else {
            fd.errorText.setText("Select 2 rows to order.");
        }
    }

    public void orderAscValuesTable() {
        JTable myTabla = getSelectedTable();
        DefaultTableModel model = (DefaultTableModel) fd.tableGeneric.getModel();
        fd.errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            String[] datas = getGaussianToOrder();
            if (fd.multiTable == true) {
                if (orderSecondTable(datas)) {
                    fd.errorText.setText("");
                    for (int i = 2; i < myTabla.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                        double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                        if (bg1 > bg2) {
                            int index1 = 0;
                            int index2 = 0;
                            for (int j = 0; j < model.getRowCount(); j++) {
                                if (String.valueOf(model.getValueAt(j, 0)).equals(datas[0])) {
                                    index1 = j;
                                } else if (String.valueOf(model.getValueAt(j, 0)).equals(datas[1])) {
                                    index2 = j;
                                }
                            }
                            changeOrder(fd.tableGeneric);
                            double a = Double.parseDouble(fd.tableGeneric.getValueAt(index1, i).toString());
                            double b = Double.parseDouble(fd.tableGeneric.getValueAt(index2, i).toString());
                            double ax = a;
                            model.setValueAt(b, index1, i);
                            model.setValueAt(ax, index2, i);
                            changeOrderAverageTable(datas, i);
                            reorderNormalTables(index1, index2, (i - 2));
                            reorderSpecialTables(datas[0], datas[1], i);
                        }

                    }
                } else {
                    fd.errorText.setText("This values cannot be ordered.");
                }
            } else {
                for (int i = 2; i < myTabla.getColumnCount(); i++) {
                    double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                    double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                    if (bg1 > bg2) {
                        int index1 = 0;
                        int index2 = 0;
                        for (int j = 0; j < model.getRowCount(); j++) {
                            if (String.valueOf(model.getValueAt(j, 0)).equals(datas[0])) {
                                index1 = j;
                            } else if (String.valueOf(model.getValueAt(j, 0)).equals(datas[1])) {
                                index2 = j;
                            }
                        }
                        changeOrder(fd.tableGeneric);
                        double a = Double.parseDouble(fd.tableGeneric.getValueAt(index1, i).toString());
                        double b = Double.parseDouble(fd.tableGeneric.getValueAt(index2, i).toString());
                        double ax = a;
                        model.setValueAt(b, index1, i);
                        model.setValueAt(ax, index2, i);
                        changeOrderAverageTable(datas, i);
                        reorderNormalTables(index1, index2, (i - 2));
                        reorderSpecialTables(datas[0], datas[1], i);
                    }

                }
            }
        } else {
            fd.errorText.setText("Please select 2 rows");
            fd.errorText.setVisible(true);
        }
    }

    private boolean orderSecondTable(String[] datas) {
        JPanel myPanel = (JPanel) (fd.tabbedPane.getComponentAt(0));
        JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(1);
        JViewport viewport = scrollPane.getViewport();
        JTable secondTable = (JTable) viewport.getView();
        DefaultTableModel secondModel = (DefaultTableModel) secondTable.getModel();

        Object index1 = null;
        Object index2 = null;

        for (int i = 2; i < secondTable.getColumnCount(); i++) {
            for (int j = 0; j < secondModel.getRowCount(); j++) {
                if (String.valueOf(secondModel.getValueAt(j, 0)).equals(datas[0])) {
                    index1 = j;
                } else if (String.valueOf(secondModel.getValueAt(j, 0)).equals(datas[1])) {
                    index2 = j;
                }
            }

            if (index1 != null && index2 != null) {
                double a = Double.parseDouble(secondTable.getValueAt(Integer.parseInt(String.valueOf(index1)), i).toString());
                double b = Double.parseDouble(secondTable.getValueAt(Integer.parseInt(String.valueOf(index2)), i).toString());
                double ax = a;
                secondModel.setValueAt(String.valueOf(b), Integer.parseInt(String.valueOf(index1)), i);
                secondModel.setValueAt(String.valueOf(ax), Integer.parseInt(String.valueOf(index2)), i);
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    /**
     * Method used to order the "normal" tables.
     *
     * @param gausian1
     * @param gausian2
     * @param order desc or asc
     */
    private void reorderNormalTables(int gausian1, int gausian2, int index) {
        //changeOrder(fd.normalTables.get(index));
        DefaultTableModel model = (DefaultTableModel) fd.normalTables.get(index).getModel();
        for (int i = 2; i < model.getColumnCount(); i++) {
            double bg1 = Double.parseDouble(model.getValueAt(gausian1, i).toString());
            double bg2 = Double.parseDouble(model.getValueAt(gausian2, i).toString());
            double aux = bg1;
            model.setValueAt(String.valueOf(bg2), gausian1, i);
            model.setValueAt(String.valueOf(aux), gausian2, i);
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
        DefaultTableModel model = (DefaultTableModel) specialTable.getModel();
        for (TableElement elementType1 : element1) {
            next:
            for (int j = 0; j < element2.size(); j++) {
                if (elementType1.column.equals(element2.get(j).column) || elementType1.column.equals(element2.get(j).row)
                        || elementType1.row.equals(element2.get(j).column) || elementType1.row.equals(element2.get(j).row)) {
                    double bg1 = Double.parseDouble(specialTable.getValueAt(elementType1.indexRow, index).toString());
                    double bg2 = Double.parseDouble(specialTable.getValueAt(element2.get(j).indexRow, index).toString());
                    double aux = bg1;
                    model.setValueAt(String.valueOf(bg2), elementType1.indexRow, index);
                    model.setValueAt(String.valueOf(aux), element2.get(j).indexRow, index);
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
            fd.errorText.setText("The following values wont be ordered: ");
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

    public void reloadAscFromAverage() {
        if (fd.averageTableReorder.getRowCount() == 0) {
            initAverageTable();
        }
        fd.errorDialogCoor.setText("");
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;
        final Color color = Color.getHSBColor(hue, saturation, luminance);
        if (!fd.indexOrderedValAsc.isEmpty()) {
            for (String[] indexs : fd.indexOrderedValAsc) {
                DefaultTableModel model = (DefaultTableModel) fd.averageTableReorder.getModel();
                Integer index1 = null;
                Integer index2 = null;
                for (int i = 0; i < fd.averageTableReorder.getRowCount(); i++) {
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[0])) {
                        index1 = i;
                    }
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[1])) {
                        index2 = i;
                    }
                }
                if (index1 != null && index2 != null) {
                    for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(index1, i).toString());
                        double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(index2, i).toString());
                        int[] colorIndexs = new int[]{index1, index2};
                        final TableColour tce = new TableColour(colorIndexs, color);
                        fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(tce);
                        if (bg1 > bg2) {
                            double ax = bg1;
                            model.setValueAt(bg2, index1, i);
                            model.setValueAt(ax, index2, i);
                        }
                    }
                } else {
                    fd.errorText.setText("This values cannot be ordered.");
                }
            }
        }

        if (!fd.dialogAverageOrder.isVisible()) {
            fd.dialogAverageOrder.setVisible(true);
        }
        fd.orderAverage = true;
        fd.dialogAverageOrder.pack();
        fd.dialogAverageOrder.setSize(1080, 720);

    }

    public void reloadDescFromAverage() {
        if (fd.averageTableReorder.getRowCount() == 0) {
            initAverageTable();
        }
        fd.errorDialogCoor.setText("");
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = (random.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;
        final Color color = Color.getHSBColor(hue, saturation, luminance);
        if (!fd.indexOrderedValDesc.isEmpty()) {
            for (String[] indexs : fd.indexOrderedValDesc) {
                DefaultTableModel model = (DefaultTableModel) fd.averageTableReorder.getModel();
                Integer index1 = null;
                Integer index2 = null;
                for (int i = 0; i < fd.averageTableReorder.getRowCount(); i++) {
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[0])) {
                        index1 = i;
                    }
                    if (String.valueOf(fd.averageTableReorder.getValueAt(i, 0)).equals(indexs[1])) {
                        index2 = i;
                    }
                }
                if (index1 != null && index2 != null) {
                    for (int i = 2; i < fd.averageTableReorder.getColumnCount(); i++) {
                        double bg1 = Double.parseDouble(fd.averageTableReorder.getValueAt(index1, i).toString());
                        double bg2 = Double.parseDouble(fd.averageTableReorder.getValueAt(index2, i).toString());
                        int[] colorIndexs = new int[]{index1, index2};
                        final TableColour tce = new TableColour(colorIndexs, color);
                        fd.averageTableReorder.getColumnModel().getColumn(i).setCellRenderer(tce);
                        if (bg1 < bg2) {
                            double ax = bg1;
                            model.setValueAt(bg2, index1, i);
                            model.setValueAt(ax, index2, i);
                        }
                    }
                } else {
                    fd.errorText.setText("This values cannot be ordered.");
                }
            }
        }

        if (!fd.dialogAverageOrder.isVisible()) {
            fd.dialogAverageOrder.setVisible(true);
        }
        fd.orderAverage = true;
        fd.dialogAverageOrder.pack();
        fd.dialogAverageOrder.setSize(1080, 720);
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
