/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Controller.csv.ControllerCsv;
import Controller.csv.ControllerIntCsv;
import Model.csv.ModelCsv;
import Model.csv.ModelIntCsv;
import View.SplashScreenInit;
import View.csv.MainFrame;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {

        SplashScreenInit test = new SplashScreenInit(30);
        test.showSplash();
        ModelIntCsv modelCSV = new ModelCsv();
        ControllerIntCsv controllerCSV = new ControllerCsv(modelCSV);
        
        MainFrame menu = new MainFrame(controllerCSV);
        menu.setVisible(true);
        
        
        
        /*ModelInt model = new Model();
        ControllerInt controller = new Controller(model);
        ChooseFilesFrame frame = new ChooseFilesFrame(controller);
        frame.setVisible(true);*/
    }
}
