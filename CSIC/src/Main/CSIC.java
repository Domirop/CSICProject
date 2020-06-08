/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import View.OptionsMenu;
import View.SplashScreenInit;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {

        SplashScreenInit test = new SplashScreenInit(3000);
        test.showSplash();
        OptionsMenu menu = new OptionsMenu();
        menu.setVisible(true);
        /*ModelInt model = new Model();
        ControllerInt controller = new Controller(model);
        ChooseFilesFrame frame = new ChooseFilesFrame(controller);
        frame.setVisible(true);*/
    }
}
