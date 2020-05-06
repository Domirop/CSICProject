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
    private List<String> table = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    
    /**
     * 
     * @param path the path of the file
     * @return list of the data
     */
    
    public List<String> getTable(String path) {
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
            }catch(Exception e){
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
    
    public void resetLists(){
        table.clear();
        data.clear();
    }
}
