/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {
        try {
            /*String string = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))
                    .filter(s -> s.contains("Isotropic"))
                    .findFirst()
                    .get();*/
            Iterator<String> iterator = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log")).iterator();
            int lineNumber1 = 0;
            int lineNumber2 = 0;
            while (iterator.hasNext()) {
                if(lineNumber2 > 0){
                    if(iterator.next().contains("End of Minotr F.D. properties file   721 does not exist.")){
                        lineNumber2--;
                        break;
                    }
                    lineNumber2++;
                }else{
                    if (iterator.next().contains("Fermi Contact (FC) contribution to K (Hz):")){
                        lineNumber2++;
                    }
                    lineNumber1++;
                }
            }
            List<String> x = new ArrayList<>();
            /*x = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))
                    .filter(s -> s.contains("Fermi Contact (FC) contribution to K (Hz):"))
                    .collect(Collectors.toList());*/
            try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))) {
                x = lines.skip(lineNumber1).limit(lineNumber2).collect(Collectors.toList());
            }
            for (String string : x) {
                System.out.println(string);
            }
            /*x.forEach((string) -> {
                string = string.replaceAll("\\s","");
                string = string.replaceAll("\\s", "");
                String[] prueba = string.split("=");
                StringBuilder builder = new StringBuilder();
                builder.append(prueba[0].replaceAll("Isotropic", ""));
                builder.append(prueba[1].replaceAll("Anisotropy", ""));
                System.out.println(builder.toString());
            });*/
        } catch (IOException ex) {
            System.out.println(ex);
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
<----------------------------MAPA-------------------------------->
        Hashtable<Integer, List> table = new Hashtable<Integer, List>();
            List l1 = new ArrayList<>();
            List l2 = new ArrayList<>();
            List l3 = new ArrayList<>();
            List l4 = new ArrayList<>();
            List l5 = new ArrayList<>();
            l1.add("1");
            l2.add("2");
            l3.add("3");
            l4.add("4");
            l5.add("5");
            table.put(1, l1);
            table.put(2, l2);
            table.put(3, l3);
            table.put(4, l4);
            table.put(5, l5);
            
            table = table.containsKey(5) ? adjuntar(table, 5, "eseeee") : anadir(table, 5, "holaa");
            System.out.println(table.toString());
            }
            
            public static Hashtable<Integer, List> anadir (Hashtable<Integer, List> table, int key, String value){
            List nueva = new ArrayList<>();
            nueva.add(value);
            table.put(key, nueva);
            return table;
            }
            public static Hashtable<Integer, List> adjuntar (Hashtable<Integer, List> table, int key, String value){
            List nueva = table.get(key);
            nueva.add(value);
            table.put(key, nueva);
            return table;
         */
    }
}
