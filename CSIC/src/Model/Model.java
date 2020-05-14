/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Atomo.Atom;
import Model.Atomo.AtomTable;
import Model.Atomo.Calculations;
import Model.Atomo.Molecule;
import Model.Atomo.FileData;
import Model.Atomo.TotalDifferentiator;
import Model.Files.ExportCSV;
import Model.Files.ReadEnergyValue;
import Model.Files.ReadLines;
import Model.Files.ReadTable;
import java.io.File;
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

    /**
     * This method is responsible for returning. the lines that the getLines
     * method returns from a filter.
     *
     * @param path This variable refers to the file path.
     * @param filter This variable refers to the search filter.
     * @return List with the lines returned by the get Lines method.
     */
    @Override
    public List<String> getLines(String path, String filter) {
        return readIso.getLines(path, filter);
    }

    /**
     * This method is responsible for. formatting the lines that arrive by
     * parameters.
     *
     * @param lines This variable refers to the lines to be formatted.
     * @return returns the list but formatted.
     */
    @Override
    public List<String> formatLine(List<String> lines) {
        return readIso.formatLineIsotropic(lines);
    }

    /**
     * Call the getTable method of the readTable class.
     *
     * @param path This variable refers to the file path.
     * @param start This variable refers to the beginning of the method reading.
     * @return List with the lines returned by the getTable method.
     */
    @Override
    public List<String> getTable(String path, String start) {
        return readTable.getTable(path, start);
    }

    /**
     * This method is in charge of obtaining the energy data of a file.
     *
     * @param path This variable refers to the file path.
     * @return The energy value of the file.
     */
    @Override
    public String SCFDone(String path) {
        return readEnergy.SCFDone(path);
    }

    /**
     * This method is responsible for obtaining a single. value from the table
     * of values ​​that the previous method collects.
     *
     * @param path This variable refers to the file path.
     * @param column the value is in.
     * @param row the value is in.
     * @param start This variable refers to the beginning of the method reading.
     * @return The chosen value.
     */
    @Override
    public String getValue(String path, int column, int row, String start) {
        return readTable.getValue(path, column, row, start);
    }

    /**
     * This method is responsible for obtaining the total value ​​of the
     * molecule.
     *
     * @param files Files where we look for the data to calculate the value.
     * @param key variable to differentiate the molecule in the different types
     * of files.
     * @return Molecule object with the obtained data.
     */
    @Override
    public Molecule getMolecule(List<File> files, String key) {
        Molecule molecule = new Molecule(getFileData(files), key);
        List<String> gaussianAtom = getAtomsGaussian(molecule.getFilesData());
        List<TotalDifferentiator> total = new ArrayList<>();
        for (String string : gaussianAtom) {
            TotalDifferentiator totalDifferentiator = new TotalDifferentiator();
            totalDifferentiator = calculations.getTotalMoleculeValue(molecule.getFilesData(), string);
            total.add(totalDifferentiator);
        }
        molecule.setResult(total);
        return molecule;
    }

    /**
     * Returns a list with all the gaussians that exist in the files.
     *
     * @param files Files where we look for the data to calculate the gaussian n
     * umber that exist.
     * @return Lista with the all gaussian.
     */
    @Override
    public List<String> getAtomsGaussian(List<FileData> files) {
        List<String> gaussianTypes = new ArrayList<>();
        for (FileData file : files) {
            for (Atom atom : file.getAtoms()) {
                if (!gaussianTypes.contains(atom.getGaussianData())) {
                    gaussianTypes.add(atom.getGaussianData());
                }
            }
        }
        return gaussianTypes;
    }

    /**
     * This method is in charge of obtaining all the necessary data from the
     * files.
     *
     * @param files Files where we look for the data to get all the data.
     * @return List of FileData object.
     */
    @Override
    public List<FileData> getFileData(List<File> files) {
        List<FileData> fileData = new ArrayList<>();
        for (File file : files) {
            List<String> atomsData = formatLine(getLines(file.getAbsolutePath(), "Isotropic"));
            if (!atomsData.isEmpty()) {
                String energyValue = SCFDone(file.getAbsolutePath());
                String fileName = file.getName().split("\\.")[0];
                fileData.add(calculations.getFileData(atomsData, energyValue, fileName));
            }
        }
        return fileData;
    }

    /**
     * This method communicates with the class that writes the file of type csv.
     *
     * @param datas list containing the data to be written.
     */
    @Override
    public boolean writeCSV(List<String[]> datas, String path, String fileName) {
        csv.setDataLines(datas);
        return csv.createCSV(path, fileName);
    }

    /**
     * This method is in charge of obtaining all the necessary data from the table's
     * files.
     * @param files Files where we look for the data to get all the data.
     * @param coordinates Coordinates of values.
     * @return List of elements FileData.
     */
    @Override
    public List<FileData> getFileDataTable(List<File> files, List<String> coordinates) {
        List<FileData> filesDatas = new ArrayList<>();
        for (File file : files) {
            FileData fileData = new FileData();
            fileData.setFileName(file.getName().split(",")[0]);
            fileData.setEnergyValue(Double.parseDouble(SCFDone(file.getAbsolutePath())));        
            fileData.setAtomsTable(getAtomTables(coordinates, file.getAbsolutePath()));
            filesDatas.add(fileData);
        }
        return filesDatas;
    }

    /**
     * Returns a list with all the atomsTable that exist in the files.
     * @param coordinates coordinates of atoms in the table.
     * @param path path of file.
     * @return List o element AtomTable.
     */
    @Override
    public List<AtomTable> getAtomTables(List<String> coordinates, String path) {
        List<AtomTable> atomsTable = new ArrayList<>();
        for (String coordinate : coordinates) {
            AtomTable atomTable = new AtomTable();
            String[] position = coordinate.split(",");
            atomTable.setColumn(Integer.parseInt(position[1]));
            atomTable.setRow(Integer.parseInt(position[0]));
            atomTable.setValue(Double.parseDouble(this.getValue(path, atomTable.getColumn(),
                    atomTable.getRow(), "contribution to J")));
            atomsTable.add(atomTable);
        }
        return atomsTable;
    }

    /**
     * This method is responsible for obtaining the total value ​​of the
     * molecule.
     * @param files files where the methods search data.
     * @param coordinates coordinates of the elements.
     * @param key diferentiator to the rest of molecules.
     * @return A Molecule object.
     */
    @Override
    public Molecule getMoleculeTable(List<File> files, List<String> coordinates, String key) {
        Molecule molecule = new Molecule();
        molecule.setFilesData(getFileDataTable(files, coordinates));
        molecule.setDifferentiator(key);
        List<TotalDifferentiator> total = new ArrayList<>();
        for (String coordinate : coordinates) {
            TotalDifferentiator totalDifferentiator = new TotalDifferentiator();
            totalDifferentiator = calculations.getValueToAtomTable(molecule.getFilesData(), coordinate);
            total.add(totalDifferentiator);
        }
        molecule.setResult(total);
        return molecule;
    }
}
