/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.log;

import Model.log.Atomo.AtomTable;
import Model.log.Atomo.FileData;
import Model.log.Atomo.Molecule;
import java.io.File;
import java.util.List;

/**
 *
 * @author daviddiaz
 */
public interface ModelIntLog {

    public List<String> getLines(String path, String filter);

    public List<String> formatLine(List<String> lines);

    public List<String> getTable(String path, String start);

    public String getValue(String path, int column, int row, String start) throws Exception;

    public String SCFDone(String path);

    public Molecule getMolecule(List<File> files, String key, double temp, double cutOff);

    public List<String> getAtomsGaussian(List<FileData> files);

    public List<FileData> getFileData(List<File> files, double temp, double cutOff);

    public boolean writeCSV(List<String[]> datas, String path, String fileName);

    public List<FileData> getFileDataTable(List<File> files, List<String> coordinates, String start, double temp, double cutOff) throws Exception;

    public Molecule getMoleculeTable(List<File> files, List<String> coordinates, String key, double temp, String start, double cutOff) throws Exception;

    public List<AtomTable> getAtomTables(List<String> coordinates, String path, String start) throws Exception;

}
