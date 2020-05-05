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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 *
 * @author domit
 */
public class CSIC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String string = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))
                    .filter(s -> s.contains("Isotropic"))
                    .findFirst()
                    .get();
            /*List string = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))
                    .filter(s -> s.contains("Isotropic"))
                    .collect(Collectors.toList());
            string.forEach((t) -> {
                System.out.println(t);
            
            });*/
            char[] prueba = string.toCharArray();
            for (int i = 0; i < prueba.length; i++) {
                if(Character.isWhitespace(prueba[i])){
                    System.out.println("hola");
                    System.out.println(prueba[i]);
                    System.out.println(string.charAt(prueba[i]));
                    string.replace(prueba[i], 'x'); 
               }
            }
            System.out.println(string);
            /*Hashtable<Integer, List> table = new Hashtable<Integer, List>();
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
            return table;*/
        } catch (IOException ex) {
            Logger.getLogger(CSIC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
