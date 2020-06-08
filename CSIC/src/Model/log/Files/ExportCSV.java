/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.log.Files;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class used to export all the tables to CSV
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

    /**
     * This method is responsible for converting an array of data in CSV format.
     *
     * @param data Array to be converted.
     * @return String converted to CSV type.
     */
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    /**
     * This method is responsible for creating the CSV file.
     *
     * @param path Path where the file will be created.
     * @param fileName Name that will receive the file.
     * @return True or false whether or not an error occurs.
     */
    public boolean createCSV(String path, String fileName) {
        String separator = "";
        if (path.contains("/")) {
            separator = "/";
        } else {
            separator = "\\";
        }
        File csvOutputFile = new File(path + separator + fileName + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is in charge of controlling if the lines that arrive have
     * some kind of special character so that it is not erased.
     *
     * @param data Phrase to be controlled.
     * @return Returns a new string with the characters that we do not want
     * deleted.
     */
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
