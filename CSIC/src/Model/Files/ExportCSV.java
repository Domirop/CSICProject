/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Files;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author daviddiaz
 */
public class ExportCSV {

    private List<String[]> dataLines = new ArrayList<>();

    public List<String[]> getDataLines() {
        return dataLines;
    }

    public void setDataLines(List<String[]> dataLines) {
        this.dataLines = dataLines;
    }
    

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void createCSV() throws IOException {
        File csvOutputFile = new File("hey.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public String escapeSpecialCharacters(String data) {
        System.out.println(data);
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}