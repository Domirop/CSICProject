/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to make all the calculation.
 *
 * @author domit
 */
public class Calculus {

    /**
     * This method is responsible for obtaining a list with all the total.
     * values ​​That the file list has for each gaussian.
     *
     * @param files List of files in which we are going to compare the value.
     * @param gaussianData Reference to the gaussian for which the value is
     * being searched.
     * @return A differentiator object with all the necessary data.
     */
    public AverageValue getTotalMoleculeValue(List<FileData> files, String gaussianData) {
        AverageValue totalDifferentiator = new AverageValue();
        totalDifferentiator.setGaussian(gaussianData);

        double minValue = getEnergyMinValue(files);
        double expS = getExps(files, minValue);
        double result = 0.0;
        for (int i = 0; i < files.size(); i++) {
            double mediumValue = 0.0;
            double initialValue = 0.0;
            double contribution = 0.0;
            if (files.get(i).getEnergyValue() == minValue) {
                initialValue = 0;
            } else {
                initialValue = (files.get(i).getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
            }
            mediumValue = Math.exp(initialValue * -1);

            contribution = mediumValue / expS;

            for (int j = 0; j < files.get(i).getAtoms().size(); j++) {
                if (files.get(i).getAtoms().get(j).getGaussianData().equals(gaussianData)) {
                    if (i == 0) {
                        totalDifferentiator.setAtom(files.get(i).getAtoms().get(j).getAtom());
                    }
                    result += contribution * files.get(i).getAtoms().get(j).getIsotropic();
                }
            }
        }

        totalDifferentiator.setValue(result);
        return totalDifferentiator;
    }

    /**
     * Get TotalDiferentiator elements to the molecule
     *
     * @param files files where the methods search data.
     * @param coordinates coordinates of the atoms.
     * @return return a TotalDiferentiator object.
     */
    public AverageValue getValueToMoleculeTable(List<FileData> files, String coordinates) {
        AverageValue totalDifferentiator = new AverageValue();
        totalDifferentiator.setGaussian(coordinates);
        double minValue = getEnergyMinValue(files);
        double expS = getExps(files, minValue);
        double result = 0.0;
        for (int i = 0; i < files.size(); i++) {
            double mediumValue = 0.0;
            double initialValue = 0.0;
            double contribution = 0.0;
            if (files.get(i).getEnergyValue() == minValue) {
                initialValue = 0;
            } else {
                initialValue = (files.get(i).getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
            }
            mediumValue = Math.exp(initialValue * -1);
            contribution = mediumValue / expS;
            for (int j = 0; j < files.get(i).getAtomsTable().size(); j++) {
                int column = files.get(i).getAtomsTable().get(j).getColumn();
                int row = files.get(i).getAtomsTable().get(j).getRow();
                if (totalDifferentiator.getGaussian().equals(row + "," + column)) {
                    result += contribution * files.get(i).getAtomsTable().get(j).getValue();
                }
            }

        }
        totalDifferentiator.setValue(result);
        return totalDifferentiator;
    }

    /**
     * This method get de energy value of the file.
     *
     * @param files files where the method search a energy value.
     * @return return a double value. it is the energy value.
     */
    public double getEnergyMinValue(List<FileData> files) {
        double minValue = files.get(0).getEnergyValue();
        for (FileData file : files) {
            if (file.getEnergyValue() < minValue) {
                minValue = file.getEnergyValue();
            }
        }
        return minValue;
    }

    /**
     * This methods get a mathematical formula thats necesary for other methods
     *
     * @param files files where methods search data.
     * @param minValue a double value that this methods used for calculated
     * other elements.
     * @return a double value. it is the value of the mathematical formula.
     */
    public double getExps(List<FileData> files, double minValue) {
        double expS = 0.0;
        for (FileData file : files) {
            double initialValue = (file.getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
            if (initialValue == 0.0) {
                expS += 1;
            } else {
                double i = Math.exp(initialValue * -1);

                expS = expS + i;
            }
        }
        return expS;
    }

    /**
     * This method gets all the data from a fileData object.
     *
     * @param atomsData List with all the data of the atoms that we are going to
     * need for this file.
     * @param energyValue Energy values ​​for said file.
     * @param fileName File name.
     * @return Returns a FileData object with all its values.
     */
    public FileData getFileData(List<String> atomsData, String energyValue, String fileName) {
        List<Atom> atoms = new ArrayList<>();
        String regex = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
        for (String string : atomsData) {
            String[] data = string.split("/");
            String[] elementsData = data[0].split(regex);
            Atom atom = new Atom(elementsData[1], elementsData[0], Double.parseDouble(data[1]));
            atoms.add(atom);
        }
        FileData fileData = new FileData();
        fileData.setFileName(fileName);
        fileData.setAtoms(atoms);
        fileData.setEnergyValue(Double.parseDouble(energyValue));
        return fileData;
    }
}
