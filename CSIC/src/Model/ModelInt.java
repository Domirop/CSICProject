/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author daviddiaz
 */
public interface ModelInt {

    public List<String> getLines(String path, String filter);

    public List<String> formatLine(List<String> lines);

    public List<String> getTable(String path, String start);

    public String getValue(String path, int column, int row, String start);

    public String SCFDone(String path);
        
}
