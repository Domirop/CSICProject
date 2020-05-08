/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

/**
 *
 * @author domit
 */
public class TotalDifferentiator {
    private String atomo;
    private double value;

    public TotalDifferentiator(String atomo, double value) {
        this.atomo = atomo;
        this.value = value;
    }

    public TotalDifferentiator() {
    }

    public String getAtomo() {
        return atomo;
    }

    public void setAtomo(String atomo) {
        this.atomo = atomo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TotalDifferentiator{" + "atomo=" + atomo + ", value=" + value + '}';
    }
}
