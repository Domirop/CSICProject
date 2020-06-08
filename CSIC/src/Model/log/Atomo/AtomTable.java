/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.log.Atomo;

/**
 * Represents the table that contains the atom values.
 *
 * @author domit
 */
public class AtomTable {

    private int column;
    private int row;
    private double value;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AtomTable{" + "column=" + column + ", row=" + row + ", value=" + value + '}';
    }
    
}
