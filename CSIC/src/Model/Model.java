/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Files.ReadEnergyValue;
import Model.Files.ReadLines;
import Model.Files.ReadTable;
import java.util.List;

/**
 *
 * @author domit
 */
public class Model implements ModelInt {

    ReadLines readIso = new ReadLines();
    ReadTable readTable = new ReadTable();
    ReadEnergyValue readEnergy = new ReadEnergyValue();

    @Override
    public List<String> getLines(String path, String filter) {
        return readIso.getLines(path, filter);
    }

    @Override
    public List<String> formatLine(List<String> lines, String type) {
        if (type.equalsIgnoreCase("Isotropic")) {
            return readIso.formatLineIsotropic(lines);
        } 
        if(type.equalsIgnoreCase("Anisotropy")){
            return readIso.formatLineAnisotropy(lines);
        }
        return null;
    }

    @Override
    public List<String> getTable(String path, String start) {
        return readTable.getTable(path, start);
    }

    @Override
    public String SCFDone(String keyword, String path) {
        return readEnergy.SCFDone(keyword, path);
    }
    @Override
    public String getValue(String path, int column, int row, String start) {
        return readTable.getValue(path, column, row, start);
    }
}
