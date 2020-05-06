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

    public List<String> formatLine(List<String> lines, String type);

    public List<String> getTable(String path);
    
    public String SCFDone(String keyword, String path);
}
