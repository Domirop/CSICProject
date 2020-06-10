/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.csv;

import Model.csv.Files.ReadCSV;
import java.util.List;

/**
 *
 * @author domit
 */
public class ModelCsv implements ModelIntCsv {

    ReadCSV read = new ReadCSV();

    @Override
    public List<Object[]> readFile(String path, String separator) {
        try {
            return read.readFileLineByLine(path, separator);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
