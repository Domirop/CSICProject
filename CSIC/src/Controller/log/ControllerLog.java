/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.log;

import Controller.*;
import Model.log.Atomo.FileData;
import Model.log.Atomo.Molecule;
import Model.log.ModelIntLog;
import java.io.File;
import java.util.List;

/**
 * Control all the methods used on the view.
 *
 * @author domit
 */
public class ControllerLog implements ControllerIntLog {

    ModelIntLog model;

    public ControllerLog(ModelIntLog model) {
        this.model = model;
    }

    @Override
    public List<FileData> getFileData(List<File> files, double temp, double cutOff) {
        return model.getFileData(files, temp, cutOff);
    }

    @Override
    public boolean writeCSV(List<String[]> datas, String path, String fileName) {
        return model.writeCSV(datas, path, fileName);
    }

    @Override
    public Molecule getMolecule(List<File> files, String key, double temp, double cutOff) {
        return model.getMolecule(files, key, temp, cutOff);
    }

    @Override
    public Molecule getTableMolecule(List<File> files, List<String> coordinates, String key, double temp) throws Exception{
        return model.getMoleculeTable(files, coordinates, key, temp);
    }
}
