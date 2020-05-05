/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CSIC{
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
            List<String> x = Files.lines(Paths.get("C:\\Users\\domit\\Desktop\\01_lsw3_2R3S5R12R_1_DP4J.log"))
                    .filter(s -> s.contains("Isotropic"))
                    .collect(Collectors.toList());
            x.forEach((string) -> {
                string = string.replaceAll("\\s","");
                string = string.replaceAll("\\s", "");
                String[] prueba = string.split("=");
                StringBuilder builder = new StringBuilder();
                builder.append(prueba[0].replaceAll("Isotropic", ""));
                builder.append(prueba[1].replaceAll("Anisotropy", ""));
                System.out.println(builder.toString());
            });
        } catch (IOException ex) {
            System.out.println(ex);
        }
        /*String formula = string;

            insert "1" in atom-atom boundry 
            formula = formula.replaceAll("(?<=[A-Z])(?=[A-Z])|(?<=[a-z])(?=[A-Z])|(?<=\D)$", "100");

            //split at letter-digit or digit-letter boundry
            String regex = "(?<=\D)(?=\d)|(?<=\d)(?=\D)";
            String[] atoms = formula.split(regex);
            for (String atom : atoms) {
                System.out.println(atom);
            }*/
    }
}
