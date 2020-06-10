/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.csv;

import Model.csv.ModelIntCsv;
import Model.log.Files.ExportCSV;
import java.util.List;

/**
 *
 * @author domit
 */
public class ControllerCsv implements ControllerIntCsv {

    ModelIntCsv model;
    private ExportCSV csv = new ExportCSV();

    public ControllerCsv(ModelIntCsv model) {
        this.model = model;
    }

    @Override
    public List<Object[]> readFile(String path, String separator) {
        return model.readFile(path, separator);
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

}
