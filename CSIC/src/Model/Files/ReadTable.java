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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author daviddiaz
 */
public class ReadTable {

    private final String start = "Fermi Contact (FC) contribution to K (Hz):";

    private final String end = "End of Minotr";

    /**
     *
     * @param path the path of the file
     * @return list of the data
     */
    public List<String> getTable(String path) {
        List<String> table = new ArrayList<>();
        List<String> data = new ArrayList<>();

        try {
            Iterator<String> iterator = Files.lines(Paths.get(path)).iterator();
            int lineStart = 0;
            int lineEnd = 0;
            while (iterator.hasNext()) {
                if (lineEnd > 0) {
                    if (iterator.next().contains(end)) {
                        lineEnd--;
                        break;
                    }
                    lineEnd++;
                } else {
                    if (iterator.next().contains(start)) {
                        lineEnd++;
                    }
                    lineStart++;
                }
            }

            try (Stream<String> lines = Files.lines(Paths.get(path))) {
                table = lines.skip(lineStart).limit(lineEnd).collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String string : table) {
                data.add(string);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    
    /**
     * 
     * @param path the path of the file
     * @param column integer that represents the column
     * @param row integer that represents the row
     * @return value specified
     */

    public String getValue(String path, int column, int row) {
        List<String> valueList = getTable(path);
        String[] sep = valueList.get(valueList.size() - 1).split("\\s");
        ArrayList<String> lineValues = new ArrayList<>();
        ArrayList<String> singleValues = new ArrayList<>();

        int aux = 0;
        int val = (int) Math.floor(column / 5.0);
        int subs = 0;
        int count = 0;
        int index = 0;
        for (int i = 0; i < sep.length; i++) {
            if (!sep[i].isEmpty()) {
                lineValues.add(sep[i]);

            }
        }

        int numeroLineas = Integer.parseInt(lineValues.get(0));

        if (val == 0) {
            index = row;
            for (int i = 0; i < numeroLineas; i++) {
                singleValues.add(valueList.get(i));
            }
        } else {
            for (int i = 0; i < val; i++) {
                subs = numeroLineas - (i * 5);
                aux = aux + subs;
                count++;
            }
            aux = aux + val;
            for (int i = aux; i < subs + aux - 4; i++) {
                singleValues.add(valueList.get(i));
            }
            index = row - (count * 5);
        }

        String[] splitVal = singleValues.get(index).split("\\s+");

        lineValues.clear();
        for (String string : splitVal) {
            if (!string.isEmpty()) {
                lineValues.add(string);
            }
        }
        return lineValues.get(column - (val * 5));
    }

}
