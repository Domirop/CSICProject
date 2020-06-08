/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.csv;

import Model.csv.ModelIntCsv;
import java.util.List;

/**
 *
 * @author domit
 */
public class ControllerCsv implements ControllerIntCsv {

    ModelIntCsv model;

    public ControllerCsv(ModelIntCsv model) {
        this.model = model;
    }

    @Override
    public List<Object[]> readFile(String path) {
        return model.readFile(path);
    }

}
