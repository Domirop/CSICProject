/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csic;

import Model.Files.ReadTable;
import View.Frame;
import java.util.ArrayList;
import java.util.List;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {
        ReadTable re = new ReadTable();
        Frame f = new Frame();
        f.setVisible(true);
        List<String> lista = re.getTable("/Users/daviddiaz/Downloads/out/01_lsw3_2R3S5R12R_1_DP4J.log");
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
        System.out.println(valor.get(columna - (val * 5)));

        /*

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
