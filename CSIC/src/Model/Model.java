/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Files.ReadEnergyValue;
import Model.Files.ReadIsotropic;
import Model.Files.ReadTable;
import java.util.List;

/**
 *
 * @author domit
 */
public class Model implements ModelInt {

    ReadIsotropic readIso = new ReadIsotropic();
    ReadTable readTable = new ReadTable();
    ReadEnergyValue readEnergy = new ReadEnergyValue();

    @Override
    public List<String> getIsotropic(String path) {
        return readIso.getIsotropic(path);
    }

    @Override
    public List<String> formatLine(List<String> lines) {
        return readIso.formatLine(lines);
    }

    @Override
    public List<String> getTable(String path) {
        return readTable.getTable(path);
    }

    @Override
    public String SCFDone(String keyword, String path) {
        return readEnergy.SCFDone(keyword, path);
    }

    @Override
    public String getValue() {
        return readTable.getValue();
    }
}
