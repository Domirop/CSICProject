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

    public double getContributionValue(List<FileData> files, int index, String typeAtom) {
        double contribution = 0.0;
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
        if (files.get(index).getEnergyValue() == minValue) {
            contribution = 0;
        } else {
            double initialValue = (files.get(index).getEnergyValue() - minValue) * 2625500 / (8.315 * 298.15);
            double mediumValue = Math.pow(initialValue, -1);
            contribution = mediumValue / expS;
        }
        double result = 0.0;
        for (int i = 0; i < files.get(index).getAtoms().size(); i++) {
            if (files.get(index).getAtoms().get(i).getAtom().equals(typeAtom)) {
                result += contribution * files.get(index).getAtoms().get(i).getIsotropic();
            }
        }
        return result;
    }

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
