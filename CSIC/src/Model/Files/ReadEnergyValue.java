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
 * Class used to read the energy value from a single file.
 *
 * @author daviddiaz
 */
public class ReadEnergyValue {

    /**
     * This method obtains the energy value.
     *
     * @param path path of the file.
     * @return Energy value.
     */
    public String SCFDone(String path) {
        String line = "";
        String value = "";
        String[] separatedLine;
        try {
            line = Files.lines(Paths.get(path))
                    .filter(s -> s.contains("SCF Done")).findFirst().get();
            separatedLine = line.split("=");

            String formula = separatedLine[1];
            formula = formula.replaceAll("\\s", "");
            String regex = "(?!(\\d+\\.?\\d*)|(\\.\\d+))(([Ee][+-]?)?\\d+)?";
            String[] values = formula.split(regex);
            value = values[0];
            return value;
        } catch (IOException e) {
            return value;
        }
    }
}
