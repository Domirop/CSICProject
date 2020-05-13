/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author domit
 */
public class Calculations {

    /**
     * This method is responsible for obtaining a list with all the total.
     * values ​​That the file list has for each gaussian.
     * @param files List of files in which we are going to compare the value.
     * @param gaussianData Reference to the gaussian for which the value is being searched.
     * @return A differentiator object with all the necessary data.
     */
    public TotalDifferentiator getTotalMoleculeValue(List<FileData> files, String gaussianData) {
        TotalDifferentiator totalDifferentiator = new TotalDifferentiator();
        totalDifferentiator.setGaussian(gaussianData);
        double minValue = files.get(0).getEnergyValue();
        for (FileData file : files) {
            if (file.getEnergyValue() < minValue) {
                minValue = file.getEnergyValue();
            }
        }
        double expS = 0.0;
        for (FileData file : files) {
            double initialValue = (file.getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
            if (initialValue == 0.0) {
                expS = expS;
            } else {
                double i = Math.pow(initialValue, -1);
                expS = expS + i;
            }
        }
        double result = 0.0;
        for (int i = 0; i < files.size(); i++) {
            double contribution = 0.0;
            if (files.get(i).getEnergyValue() == minValue) {
                contribution = 0;
            } else {
                double initialValue = (files.get(i).getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
                double mediumValue = Math.pow(initialValue, -1);
                contribution = mediumValue / expS;
            }
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
     * This method gets all the data from a fileData object.
     * @param atomsData List with all the data of the atoms that we are going to need for this file.
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
        FileData fileData = new FileData(fileName, atoms, Double.parseDouble(energyValue));
        return fileData;
    }
}
