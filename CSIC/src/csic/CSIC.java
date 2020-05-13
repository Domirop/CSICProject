/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csic;
import Controller.Controller;
import Controller.ControllerInt;
import Model.Model;
import Model.ModelInt;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {
        ModelInt model = new Model();
        ControllerInt controller = new Controller(model);
        controller.startApp();
    }
}
