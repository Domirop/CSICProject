/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.log.Atomo;

/**
 * Represents the average value calculated.
 *
 * @author domit
 */
public class AverageValue {

    private String gaussian;
    private String atom;
    private double value;

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public AverageValue() {
    }

    public String getGaussian() {
        return gaussian;
    }

    public void setGaussian(String atomo) {
        this.gaussian = atomo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AverageValue{" + "gaussian=" + gaussian + ", atom=" + atom + ", value=" + value + '}';
    }
    
    
}
