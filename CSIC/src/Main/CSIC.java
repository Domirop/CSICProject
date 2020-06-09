/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.OptionsMenu;
import View.SplashScreenInit;
import java.awt.Dimension;
import java.awt.Toolkit;

public class CSIC {

    /**
     *
     * @author domit
     */
    public static void main(String[] args) {
    
        SplashScreenInit test = new SplashScreenInit(3000);
        test.showSplash();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        OptionsMenu menu = new OptionsMenu();
        menu.setLocation(dim.width / 2 - menu.getSize().width / 2, dim.height / 2 - menu.getSize().height / 2);
        menu.setVisible(true);

    }
}
