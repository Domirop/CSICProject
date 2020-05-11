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
public class Atom {
    private String atom;
    private String GausianData;
    private double isotropic;
    private double contribution;

    public String getAtom() {
        return atom;
    }

    public Atom(String atom, String GausianData, double isotropic) {
        this.atom = atom;
        this.GausianData = GausianData;
        this.isotropic = isotropic;
    }
    

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public String getGausianData() {
        return GausianData;
    }

    public void setGausianData(String GausianData) {
        this.GausianData = GausianData;
    }

    public double getIsotropic() {
        return isotropic;
    }

    public void setIsotropic(double isotropic) {
        this.isotropic = isotropic;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    @Override
    public String toString() {
        return "Atom{" + "atom=" + atom + ", GausianData=" + GausianData + ", isotropic=" + isotropic + ", contribution=" + contribution + '}';
    }
    
}
