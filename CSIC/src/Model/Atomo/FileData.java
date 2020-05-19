/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

import java.util.List;

/**
 * Represents all the data from the files.
 *
 * @author domit
 */
public class FileData {

    private String fileName;
    private List<Atom> atoms;
    private List<AtomTable> atomsTable;
    private double energyValue;

    public FileData() {
    }

    public List<AtomTable> getAtomsTable() {
        return atomsTable;
    }

    public void setAtomsTable(List<AtomTable> atomsTable) {
        this.atomsTable = atomsTable;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public double getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(double energyValue) {
        this.energyValue = energyValue;
    }

}
