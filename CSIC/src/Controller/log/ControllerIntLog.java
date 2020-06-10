/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.log;

import Model.log.Atomo.FileData;
import Model.log.Atomo.Molecule;
import java.io.File;
import java.util.List;

/**
 *
 * @author daviddiaz
 */
public interface ControllerIntLog {

    public List<FileData> getFileData(List<File> files, double temp, double cutOff);

    public boolean writeCSV(List<String[]> datas, String path, String fileName);

    public Molecule getMolecule(List<File> files, String key, double temp, double cutOff);

    public Molecule getTableMolecule(List<File> files, List<String> coordinates, String key, double temp, String start, double cutOff) throws Exception;

}
