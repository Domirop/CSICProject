package Model.log.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class used to read the table from a single file.
 *
 * @author daviddiaz
 */
public class ReadTable {

    /**
     * Get the data of the files that come in table format.
     *
     * @param path the path of the file.
     * @return list of the data.
     */
    public List<String> getTable(String path, String start) {
        List<String> table = new ArrayList<>();
        List<String> data = new ArrayList<>();
        try {
            Iterator<String> iterator = Files.lines(Paths.get(path)).iterator();
            List<List<Integer>> tables = new ArrayList<>();
            int lineStart = 0;
            int aux = 0;
            int lineEnd = 0;
            while (iterator.hasNext()) {
                if (lineEnd > 0) {
                    try {
                        String linea = iterator.next().replaceAll("\\s+", "");
                        int number = Integer.parseInt(linea.split("")[0]);
                        lineEnd++;
                    } catch (Exception e) {
                        aux += lineEnd;
                        List<Integer> values = new ArrayList<>();
                        values.add(lineStart);
                        values.add(aux);
                        lineStart += aux;
                        lineEnd = 0;
                        tables.add(values);
                    }
                } else {
                    if (iterator.next().contains(start)) {
                        lineEnd++;
                    }
                    lineStart++;
                }
            }
            lineStart = tables.get(tables.size() - 1).get(0);
            lineEnd = tables.get(tables.size() - 1).get(1);
            try (Stream<String> lines = Files.lines(Paths.get(path))) {
                table = lines.skip(lineStart).limit(lineEnd - 1).collect(Collectors.toList());
            } catch (Exception e) {
            }
            for (String string : table) {
                data.add(string);
            }
        } catch (IOException ex) {
        }
        return data;
    }

    /**
     * Gets the specific data in row and column when they come in table format.
     *
     * @param path the path of the file
     * @param column integer that represents the column
     * @param row integer that represents the row
     * @return value specified
     */
    public String getValue(String path, int column, int row, String start) {
        int numeroLineas = 0;
        try {
            List<String> valueList = getTable(path, start);
            String[] sep = valueList.get(valueList.size() - 1).split("\\s");
            ArrayList<String> lineValues = new ArrayList<>();
            ArrayList<String> singleValues = new ArrayList<>();
            int aux = 0;
            int val = column % 5 == 0 ? (int) Math.floor(column / 5.0) - 1 : (int) Math.floor(column / 5.0);
            int subs = 0;
            int count = 0;
            int index = 0;
            for (int i = 0; i < sep.length; i++) {
                if (!sep[i].isEmpty()) {
                    lineValues.add(sep[i]);
                }
            }
            numeroLineas = Integer.parseInt(lineValues.get(0));
            if (val == 0) {
                index = row;
                for (int i = 1; i <= numeroLineas; i++) {
                    singleValues.add(valueList.get(i));
                }
                index--;
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
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
