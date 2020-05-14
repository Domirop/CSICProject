/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Atomo.FileData;
import Model.Atomo.Molecule;
import Model.ModelInt;
import View.ChooseFilesFrame;
import java.io.File;
import java.util.List;

/**
 *
 * @author domit
 */
public class Controller implements ControllerInt{
    
    ModelInt model;

    public Controller(ModelInt model) {
        this.model = model;
    }

    @Override
    public List<FileData> getFileData(List<File> files) {
        return model.getFileData(files);
    }
    
    @Override
    public boolean writeCSV(List<String[]> datas, String path, String fileName) {
        return model.writeCSV(datas, path, fileName);
    }
    
    @Override
    public Molecule getMolecule(List<File> files, String key) {
        return model.getMolecule(files, key);
    }

    @Override
    public void startApp() {
        ChooseFilesFrame frame = new ChooseFilesFrame(this);
        frame.setVisible(true);
    }
    
    @Override
    public Molecule getTableMolecule(List<File> files, List<String> coordinates, String key) {
        return model.getMoleculeTable(files, coordinates, key);
    }
}
