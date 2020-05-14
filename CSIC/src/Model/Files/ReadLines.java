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
     * Gets the lines that that filter contains.
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
            return lines;
        } catch (IOException ex) {
            return lines;
        }
    }

    /**
     * It is in responsible of formatting the lines that arrive to obtain the
     * important values.
     *
     * @param lines format line
     * @return formatted output
     */
    public List<String> formatLineIsotropic(List<String> lines) {
        List<String> formattedOutput = new ArrayList<>();
        if (!lines.isEmpty()) {
            lines.forEach((string) -> {
                string = string.replaceAll("\\s", "");
                String[] prueba = string.split("=");
                StringBuilder builder = new StringBuilder();
                builder.append(prueba[0].replaceAll("Isotropic", "/"));
                builder.append(prueba[1].replaceAll("Anisotropy", ""));
                formattedOutput.add(builder.toString());
            });
            return formattedOutput;

        } else {
            return formattedOutput;
        }
    }
}
