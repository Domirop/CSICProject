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

    //private final String start = "Fermi Contact (FC) contribution to K (Hz):";
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

    public String getValue() {
        List<String> lista = getTable("/Users/daviddiaz/Downloads/out/01_lsw3_2R3S5R12R_1_DP4J.log");
        String[] sep = lista.get(lista.size() - 1).split("\\s");
        ArrayList<String> valor = new ArrayList<>();
        ArrayList<String> valores = new ArrayList<>();

        int columna = 13;
        int fila = 24;
        int auxiliar = 0;
        int val = (int) Math.floor(columna / 5.0);
        int resta = 0;
        int a = 0;
        int indice = 0;
        for (int i = 0; i < sep.length; i++) {
            if (!sep[i].isEmpty()) {
                valor.add(sep[i]);

            }
        }

        int numeroLineas = Integer.parseInt(valor.get(0));

        if (val == 0) {
            indice = fila;
            for (int i = 0; i < numeroLineas; i++) {
                valores.add(lista.get(i));
            }
        } else {
            for (int i = 0; i < val; i++) {
                resta = numeroLineas - (i * 5);
                auxiliar = auxiliar + resta;
                a++;
            }
            auxiliar = auxiliar + val;
            for (int i = auxiliar; i < resta + auxiliar - 4; i++) {
                valores.add(lista.get(i));
            }
            indice = fila - (a * 5);
        }

        String[] asd = valores.get(indice).split("\\s+");

        valor.clear();
        for (String string : asd) {
            if (!string.isEmpty()) {
                valor.add(string);
            }
        }
        return valor.get(columna - (val * 5));
    }

}
