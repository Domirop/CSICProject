/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author daviddiaz
 */
public class DecorateFrame {

    MainFrame mf;

    public DecorateFrame(MainFrame mf) {
        this.mf = mf;
    }

    
    /**
     * Method used to add icons to the buttons.
     */
    public void addIcons() {

        ImageIcon imageIcon;
        try {
            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/decrease.png"))); // load the image to a imageIcon

            Image imagedecrease = imageIcon.getImage(); // transform it 
            Image newimgdecrease = imagedecrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgdecrease);  // transform it back
            mf.buttonDesc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/increase.png"))); // load the image to a imageIcon
            Image imageincrease = imageIcon.getImage(); // transform it 
            Image newimgincrease = imageincrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgincrease);  // transform it back
            mf.buttonAsc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/filter.png"))); // load the image to a imageIcon
            Image imagefilter = imageIcon.getImage(); // transform it 
            Image newimgfilter = imagefilter.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgfilter);  // transform it back
            mf.buttonFilter.setIcon(imageIcon);

            mf.repaint();

        } catch (IOException ex) {
        }
    }
}
