/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author daviddiaz
 */
public class ReadEnergyValue {

    /**
     * 
     * @param keyword to find the value
     * @param path path of the file
     * @return value
     */
    
    public String SCFDone(String keyword, String path) {
        String line = "";
        String value = "";
        String[] separatedLine;
        try {
            line = Files.lines(Paths.get(path))
                    .filter(s -> s.contains(keyword)).findFirst().get();
            separatedLine = line.split("=");

            String formula = separatedLine[1];
            formula = formula.replaceAll("\\s", "");
            String regex = "(?!(\\d+\\.?\\d*)|(\\.\\d+))(([Ee][+-]?)?\\d+)?";
            String[] values = formula.split(regex);
            value = values[0];

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;

    }
}
