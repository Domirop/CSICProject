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

    public OrderElements(MainFrame fr) {
        this.fr = fr;
    }

    public void orderElements(String order) {
        DefaultTableModel model = (DefaultTableModel) fr.table.getModel();
        int[] index = fr.table.getSelectedColumns();
        String[] gaussians = getGausianToOrder(index);
        if (gaussians.length > 0) {
            List<Integer> firstColumns = new ArrayList<>();
            List<Integer> secondColumns = new ArrayList<>();
            fr.errorText.setText("");
            for (int i = 0; i < model.getColumnCount(); i++) {
                String columnName = model.getColumnName(i);
                if (columnName.contains(gaussians[0])) {
                    firstColumns.add(i);
                } else if (columnName.contains(gaussians[1])) {
                    secondColumns.add(i);
                }
            }
            List<List<Integer>> indexCombination = new ArrayList<>();
            for (int i = 0; i < firstColumns.size(); i++) {
                List<Integer> combination = new ArrayList<>();
                String value1 = model.getColumnName(i).replace("-", "").replace(gaussians[0], "");
                combination.add(i);
                for (int j = 0; j < secondColumns.size(); j++) {
                    String value2 = model.getColumnName(j).replace("-", "").replace(gaussians[1], "");
                    if (value2.equals(value1)) {
                        combination.add(j);
                    }
                }
                if (combination.size() == 2) {
                    indexCombination.add(combination);
                }
            }

            indexCombination.forEach(v -> {
                int index1 = v.get(0);
                int index2 = v.get(1);
                for (int i = 0; i < model.getRowCount(); i++) {
                    double columnValue1 = Double.parseDouble(String.valueOf(model.getValueAt(i, index1)));
                    double columnValue2 = Double.parseDouble(String.valueOf(model.getValueAt(i, index2)));
                    if(order.equals(">")){
                        if(index1 > index2){
                            if(columnValue2 > columnValue1){
                                double aux = columnValue1;
                                model.setValueAt(columnValue2, i, index1);
                                model.setValueAt(aux, i, index2);
                            }
                        }else{
                            if(columnValue1 > columnValue2){
                                double aux = columnValue1;
                                model.setValueAt(columnValue2, i, index1);
                                model.setValueAt(aux, i, index2);
                            }
                        }
                    }else if(order.equals("<")){
                        if(index1 > index2){
                            if(columnValue2 < columnValue1){
                                double aux = columnValue1;
                                model.setValueAt(columnValue2, i, index1);
                                model.setValueAt(aux, i, index2);
                            }
                        }else{
                            if(columnValue1 < columnValue2){
                                double aux = columnValue1;
                                model.setValueAt(columnValue2, i, index1);
                                model.setValueAt(aux, i, index2);
                            }
                        }
                    }
                }
            });
        } else {
            fr.errorText.setText("Error this column cannot be orderer.");
        }
    }

    private String[] getGausianToOrder(int[] values) {
        String[] gausians = new String[2];
        String[] firstCombination = fr.table.getColumnName(values[0]).split("-");
        String[] secondCombination = fr.table.getColumnName(values[1]).split("-");
        String firstValue1 = firstCombination[0].replace(" ", "");
        String firstValue2 = firstCombination[1].replace(" ", "");
        String secondValue1 = secondCombination[0].replace(" ", "");
        String secondValue2 = secondCombination[1].replace(" ", "");
        if (firstValue1.equals(secondValue1) || firstValue1.equals(secondValue2)
                || firstValue2.equals(secondValue1) || firstValue2.equals(secondValue2)) {
            if (firstValue1.equals(secondValue1) || firstValue1.equals(secondValue2)) {
                gausians[0] = firstValue2;
            } else {
                gausians[0] = firstValue1;
            }
            if (secondValue1.equals(firstValue1) || secondValue1.equals(firstValue2)) {
                gausians[0] = secondValue1;
            } else {
                gausians[0] = secondValue1;
            }
        }
        return gausians;
    }

}