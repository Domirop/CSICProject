/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author daviddiaz
 */
public class SplashScreenInit extends JWindow {

    private int duration;

    public SplashScreenInit(int d) {
        duration = d;
    }

    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash() {

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the window's bounds, centering the window
        int width = 330;
        int height = 330;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Build the splash screen
        try {
            JLabel label = null;
            label = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/icon.png"))));
            content.add(label, BorderLayout.CENTER);

        } catch (IOException ex) {

        }
        //JLabel copyrt = new JLabel("Copyright 2002, O'Reilly & Associates", JLabel.CENTER);
        //copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        //content.add(copyrt, BorderLayout.SOUTH);
        //Color oraRed = new Color(156, 20, 20, 255);
        //content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }

        setVisible(false);

    }

    public void showSplashAndExit() {
        showSplash();
        System.exit(0);
    }
}
