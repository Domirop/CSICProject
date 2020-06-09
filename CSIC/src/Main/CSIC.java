/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Controller.log.ControllerIntLog;
import Controller.log.ControllerLog;
import Model.log.ModelIntLog;
import Model.log.ModelLog;
import View.log.ChooseFilesFrame;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {

//        SplashScreenInit test = new SplashScreenInit(30);
//        test.showSplash();
//        ModelIntCsv modelCSV = new ModelCsv();
//        ControllerIntCsv controllerCSV = new ControllerCsv(modelCSV);
//        
//        MainFrame menu = new MainFrame(controllerCSV);
//        menu.setVisible(true);
        
        
        
        ModelIntLog model = new ModelLog();
        ControllerIntLog controller = new ControllerLog(model);
        ChooseFilesFrame frame = new ChooseFilesFrame(controller);
        frame.setVisible(true);
    }
}
