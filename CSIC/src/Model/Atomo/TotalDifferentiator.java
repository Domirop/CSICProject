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
    private String gausian;
    private String atomo;
    private double value;

    public String getAtomo() {
        return atomo;
    }

    public void setAtomo(String atomo) {
        this.atomo = atomo;
    }

    public TotalDifferentiator(String atomo, double value) {
        this.gausian = atomo;
        this.value = value;
    }

    public TotalDifferentiator() {
    }

    public String getGausian() {
        return gausian;
    }

    public void setGausian(String atomo) {
        this.gausian = atomo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TotalDifferentiator{" + "atomo=" + gausian + ", value=" + value + '}';
    }
}
