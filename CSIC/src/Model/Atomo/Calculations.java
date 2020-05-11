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

    public TotalDifferentiator getContributionValue(List<FileData> files, String gausianData) {
        TotalDifferentiator totalDifferentiator = new TotalDifferentiator();
        totalDifferentiator.setGausian(gausianData);
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
                if (files.get(i).getAtoms().get(j).getGausianData().equals(gausianData)) {
                    if (i == 0) {
                        totalDifferentiator.setAtomo(files.get(i).getAtoms().get(j).getAtom());
                    }
                    result += contribution * files.get(i).getAtoms().get(j).getIsotropic();
                }
            }
        }
        totalDifferentiator.setValue(result);
        return totalDifferentiator;
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
