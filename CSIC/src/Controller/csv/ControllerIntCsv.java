/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.csv;

import java.util.List;

/**
 *
 * @author domit
 */
public interface ControllerIntCsv {

    public List<Object[]> readFile(String path);
    
    public boolean writeCSV(List<String[]> datas, String path, String fileName);

}
