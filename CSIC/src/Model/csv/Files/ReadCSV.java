/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.csv.Files;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daviddiaz
 */
public class ReadCSV {

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public List<Object[]> readFileLineByLine(String path, String separator) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        List<Object[]> list = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(separator.charAt(0))
                .withIgnoreQuotations(true)
                .build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();
        
        String[] line;

        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }
}
