/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Atomo.Atom;
import Model.Atomo.Calculations;
import Model.Atomo.Molecule;
import Model.Atomo.FileData;
import Model.Atomo.TotalDifferentiator;
import Model.Files.ExportCSV;
import Model.Files.ReadEnergyValue;
import Model.Files.ReadLines;
import Model.Files.ReadTable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author domit
 */
public class Model implements ModelInt {

    private ReadLines readIso = new ReadLines();
    private ReadTable readTable = new ReadTable();
    private ReadEnergyValue readEnergy = new ReadEnergyValue();
    private Calculations calculations = new Calculations();
    private ExportCSV csv = new ExportCSV();

    @Override
    public List<String> getLines(String path, String filter) {
        return readIso.getLines(path, filter);
    }

    @Override
    public List<String> formatLine(List<String> lines) {
        return readIso.formatLineIsotropic(lines);
    }

    @Override
    public List<String> getTable(String path, String start) {
        return readTable.getTable(path, start);
    }

    @Override
    public String SCFDone(String path) {
        return readEnergy.SCFDone(path);
    }

    @Override
    public String getValue(String path, int column, int row, String start) {
        return readTable.getValue(path, column, row, start);
    }

    public Molecule getMolecule(List<File> files, String key) {
        Molecule materia = new Molecule(getFileData(files), key);
        List<String> gausianAtom = getAtomsGausian(materia.getFiles());
        List<TotalDifferentiator> total = new ArrayList<>();

        for (String string : gausianAtom) {
            TotalDifferentiator totalDifferentiator = new TotalDifferentiator();
            totalDifferentiator = calculations.getContributionValue(materia.getFiles(), string);
            total.add(totalDifferentiator);
        }
        materia.setResult(total);
        return materia;
    }

    private List<String> getAtomsGausian(List<FileData> files) {
        List<String> gaussianTypes = new ArrayList<>();
        for (FileData file : files) {
            for (Atom atom : file.getAtoms()) {
                if (!gaussianTypes.contains(atom.getGausianData())) {
                    gaussianTypes.add(atom.getGausianData());
                }
            }
        }
        return gaussianTypes;
    }

    public List<FileData> getFileData(List<File> files) {
        List<FileData> fileData = new ArrayList<>();
        for (File file : files) {
            List<String> atomsData = formatLine(getLines(file.getAbsolutePath(), "Isotropic"));
            String energyValue = SCFDone(file.getAbsolutePath());
            String fileName = file.getName().split("\\.")[0];
            fileData.add(calculations.getFileData(atomsData, energyValue, fileName));
        }
        return fileData;
    }

    public void writeCSV(List<String[]> datas) {
        List<String[]> lines = csv.getDataLines();
        csv.setDataLines(datas);
        try {
            csv.createCSV();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
