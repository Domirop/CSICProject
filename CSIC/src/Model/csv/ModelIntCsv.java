/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.csv;

import java.util.List;

/**
 *
 * @author domit
 */
public interface ModelIntCsv {
    public List<Object[]> readFile(String path);
}
