/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author daviddiaz
 */
public class ReadLines {
    /**
     *
     * @param path the path of the file
     * @return a list of the data
     */
    public List<String> getLines(String path, String filter) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.lines(Paths.get(path))
                    .filter(s -> s.contains(filter))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 
     * @param lines lines without format
     * @return formatted output
     */
    public List<String> formatLineAnisotropy(List<String> lines) {
        List<String> formattedOutput = new ArrayList<>();
        lines.forEach((string) -> {
            string = string.replaceAll("\\s", "");
            String[] prueba = string.split("=");
            StringBuilder builder = new StringBuilder();
            builder.append(prueba[0].replaceAll("Isotropic", ""));
            builder.append(prueba[2]);
            formattedOutput.add(builder.toString());
        });
        return formattedOutput;
    }
    /**
     * 
     * @param lines format line
     * @return formatted output
     */
    public List<String> formatLineIsotropic(List<String> lines) {
        List<String> formattedOutput = new ArrayList<>();
        lines.forEach((string) -> {
            string = string.replaceAll("\\s", "");
            String[] prueba = string.split("=");
            StringBuilder builder = new StringBuilder();
            builder.append(prueba[0].replaceAll("Isotropic", "/"));
            builder.append(prueba[1].replaceAll("Anisotropy", ""));
            formattedOutput.add(builder.toString());
        });
        return formattedOutput;
    }
    /*
    <-------------------------------SEPARAR NUMEROS Y LETRAS----------------------------------->        
        String formula = string;

            insert "1" in atom-atom boundry 
            formula = formula.replaceAll("(?<=[A-Z])(?=[A-Z])|(?<=[a-z])(?=[A-Z])|(?<=\D)$", "100");

            //split at letter-digit or digit-letter boundry
            String regex = "(?<=\D)(?=\d)|(?<=\d)(?=\D)";
            String[] atoms = formula.split(regex);
            for (String atom : atoms) {
                System.out.println(atom);
            }
    */

}
