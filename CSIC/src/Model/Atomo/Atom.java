/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

/**
 * Represents a molecule.
 * 
 * @author domit
 */
public class Atom {

    private String atom;
    private String gaussianData;
    private double isotropic;

    public String getAtom() {
        return atom;
    }

    public Atom(String atom, String gaussianData, double isotropic) {
        this.atom = atom;
        this.gaussianData = gaussianData;
        this.isotropic = isotropic;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public String getGaussianData() {
        return gaussianData;
    }

    public void setGaussianData(String gaussianData) {
        this.gaussianData = gaussianData;
    }

    public double getIsotropic() {
        return isotropic;
    }

    public void setIsotropic(double isotropic) {
        this.isotropic = isotropic;
    }
}
