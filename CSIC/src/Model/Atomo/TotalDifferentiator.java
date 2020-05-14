/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

import java.math.BigDecimal;

/**
 *
 * @author domit
 */
public class TotalDifferentiator {
    private String gaussian;
    private String atom;
    private BigDecimal value;

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public TotalDifferentiator() {
    }

    public String getGaussian() {
        return gaussian;
    }

    public void setGaussian(String atomo) {
        this.gaussian = atomo;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
