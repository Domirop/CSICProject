/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.log;

/**
 * Represents the table elements.
 *
 * @author domit
 */
public class TableElement {

    String column;
    String row;
    int indexRow;

    public TableElement(String row, String column, int indexRow) {
        this.column = column;
        this.row = row;
        this.indexRow = indexRow;
    }

    @Override
    public String toString() {
        return "TableElement{" + "column=" + column + ", row=" + row + ", indexRow=" + indexRow + '}';
    }
}
