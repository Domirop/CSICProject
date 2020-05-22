/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Atomo.FileData;
import Model.Atomo.Molecule;
import java.io.File;
import java.util.List;

/**
 *
 * @author daviddiaz
 */
public interface ControllerInt {

    public List<FileData> getFileData(List<File> files, double temp);

    public boolean writeCSV(List<String[]> datas, String path, String fileName);

    public Molecule getMolecule(List<File> files, String key, double temp);

    public Molecule getTableMolecule(List<File> files, List<String> coordinates, String key, double temp) throws Exception;

}
