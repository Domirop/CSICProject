/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author domit
 */
public class OrderElements {

    MainFrame fr;
    List<Integer> indexsToChange = new ArrayList<>();

    public OrderElements(MainFrame fr) {
        this.fr = fr;
    }

    public void orderSelectedTables(String order) {
        indexsToChange.clear();
        DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
        int[] index = fr.table.getSelectedColumns();
        for (int i = 0; i < model.getRowCount(); i++) {
            double columnValue1 = Double.parseDouble(fr.table.getValueAt(i, index[0]).toString());
            double columnValue2 = Double.parseDouble(fr.table.getValueAt(i, index[1]).toString());
            if (order.equals(">")) {
                if (columnValue1 > columnValue2) {
                    indexsToChange.add(i);
                    double aux = columnValue1;
                    model.setValueAt(String.valueOf(columnValue2), i, index[0]);
                    model.setValueAt(String.valueOf(aux), i, index[1]);
                }
            } else if (order.equals("<")) {
                if (columnValue1 < columnValue2) {
                    indexsToChange.add(i);
                    double aux = columnValue1;
                    model.setValueAt(String.valueOf(columnValue2), i, index[0]);
                    model.setValueAt(String.valueOf(aux), i, index[1]);
                }
            }
            fr.table.repaint();
            fr.table.revalidate();
        }
    }

    public void orderElements(String order) {
        orderSelectedTables(order);
        DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
        int[] index = fr.table.getSelectedColumns();
        String[] gaussians = getGausianToOrder(index);
        if (gaussians[0] != null && gaussians[1] != null) {
            List<Integer> firstColumns = new ArrayList<>();
            List<Integer> secondColumns = new ArrayList<>();
            fr.errorText.setText("");
            for (int i = 0; i < model.getColumnCount(); i++) {
                String columnName = model.getColumnName(i);
                if (columnName.contains("-")) {
                    String[] columnElements = columnName.replace(" ", "").replace("\\s", "").split("-");
                    String columnValue1 = columnElements[0];
                    String columnValue2 = columnElements[1];
                    if (i != index[0] && i != index[1]
                            && (columnValue1.equals(gaussians[0])
                            || columnValue2.equals(gaussians[0]))) {
                        firstColumns.add(i);
                    } else if (i != index[0] && i != index[1]
                            && (columnValue1.equals(gaussians[1])
                            || columnValue2.equals(gaussians[1]))) {
                        secondColumns.add(i);
                    }
                }
            }
            List<List<String>> indexCombination = new ArrayList<>();
            for (int i = 0; i < firstColumns.size(); i++) {
                List<String> combination = new ArrayList<>();
                String value1 = model.getColumnName(firstColumns.get(i))
                        .replace("-", "")
                        .replace(gaussians[0], "")
                        .replace(" ", "").replace("\\s", "");
                combination.add(model.getColumnName(firstColumns.get(i)));
                for (int j = 0; j < secondColumns.size(); j++) {
                    String value2 = model.getColumnName(secondColumns.get(j))
                            .replace("-", "")
                            .replace(gaussians[1], "")
                            .replace(" ", "").replace("\\s", "");
                    if (value2.equals(value1)) {
                        combination.add(model.getColumnName(secondColumns.get(j)));
                    }
                }
                if (combination.size() == 2) {
                    indexCombination.add(combination);
                }
            }
            indexCombination.forEach(v -> {
                int index1 = 0;
                int index2 = 0;
                for (int i = 0; i < model.getColumnCount(); i++) {
                    if (model.getColumnName(i).equals(v.get(0))) {
                        index1 = i;
                    } else if (model.getColumnName(i).equals(v.get(1))) {
                        index2 = i;
                    }
                }
                for (Integer integer : indexsToChange) {
                    double columnValue1 = Double.parseDouble(fr.table.getValueAt(integer, index1).toString());
                    double columnValue2 = Double.parseDouble(fr.table.getValueAt(integer, index2).toString());
                    double aux = columnValue1;
                    model.setValueAt(String.valueOf(columnValue2), integer, index1);
                    model.setValueAt(String.valueOf(aux), integer, index2);
                }
            });
            fr.table.revalidate();
            fr.table.repaint();
            fr.repaint();
            fr.revalidate();
        } else {
            fr.errorText.setText("Error this column cannot be ordered.");
        }
    }

    private String[] getGausianToOrder(int[] values) {
        String[] gausians = new String[2];
        String[] firstCombination = fr.table.getColumnName(values[0]).split("-");
        String[] secondCombination = fr.table.getColumnName(values[1]).split("-");
        String firstValue1 = firstCombination[0].replace(" ", "").replace("\\s", "");
        String firstValue2 = firstCombination[1].replace(" ", "").replace("\\s", "");
        String secondValue1 = secondCombination[0].replace(" ", "").replace("\\s", "");
        String secondValue2 = secondCombination[1].replace(" ", "").replace("\\s", "");
        if (firstValue1.equals(secondValue1) || firstValue1.equals(secondValue2)
                || firstValue2.equals(secondValue1) || firstValue2.equals(secondValue2)) {
            if (firstValue1.equals(secondValue1) || firstValue1.equals(secondValue2)) {
                gausians[0] = firstValue2;
            } else {
                gausians[0] = firstValue1;
            }
            if (secondValue1.equals(firstValue1) || secondValue1.equals(firstValue2)) {
                gausians[1] = secondValue2;
            } else {
                gausians[1] = secondValue1;
            }
        }
        return gausians;
    }

}
