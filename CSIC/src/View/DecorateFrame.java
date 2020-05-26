/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author daviddiaz
 */
public class DecorateFrame {

    FrameDifferentiator fd;

    public DecorateFrame(FrameDifferentiator fd) {
        this.fd = fd;
    }

    /**
     * Method used to add icons to the buttons.
     */
    public void addIcons() {

        ImageIcon imageIcon;
        try {
            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/rightarrow.png"))); // load the image to a imageIcon

            Image imageadd = imageIcon.getImage(); // transform it 
            Image newimgadd = imageadd.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgadd);  // transform it back
            fd.buttonAdd.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/search.png"))); // load the image to a imageIcon
            Image imagesearch = imageIcon.getImage(); // transform it 
            Image newimgsearch = imagesearch.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgsearch);  // transform it back
            fd.buttonValue.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/csv.png"))); // load the image to a imageIcon
            Image imagecsv = imageIcon.getImage(); // transform it 
            Image newimgcsv = imagecsv.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgcsv);  // transform it back
            fd.buttonExportCSV.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/reset.png"))); // load the image to a imageIcon
            Image imagereset = imageIcon.getImage(); // transform it 
            Image newimgreset = imagereset.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgreset);  // transform it back
            fd.buttonDelete.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/delete.png"))); // load the image to a imageIcon
            Image imagedelete = imageIcon.getImage(); // transform it 
            Image newimgdelete = imagedelete.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgdelete);  // transform it back
            fd.buttonRemoveTable.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/increase.png"))); // load the image to a imageIcon
            Image imageincrease = imageIcon.getImage(); // transform it 
            Image newimgincrease = imageincrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgincrease);  // transform it back
            fd.orderAsc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/decrease.png"))); // load the image to a imageIcon
            Image imagedecrease = imageIcon.getImage(); // transform it 
            Image newimgdecrease = imagedecrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgdecrease);  // transform it back
            fd.orderDesc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/average.png"))); // load the image to a imageIcon
            Image imageaverage = imageIcon.getImage(); // transform it 
            Image newimgaverage = imageaverage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgaverage);  // transform it back
            fd.buttonAverage.setIcon(imageIcon);

            fd.repaint();

        } catch (IOException ex) {
        }
    }

    /**
     * this method decorate de textArea to the dialogAddMoreFiles.
     *
     * @param a The textArea will be decorate.
     * @param img The image which you want decorate.
     */
    public void decorate(JTextArea a, final BufferedImage img) {
        if (img != null) {
            int x = (fd.dialogAddMoreFiles.getPreferredSize().width - img.getWidth(null) - 20) / 2;
            int y = (fd.dialogAddMoreFiles.getPreferredSize().height - img.getHeight(null)) / 4;
            a.setUI(new javax.swing.plaf.basic.BasicTextAreaUI() {
                @Override
                protected void paintBackground(Graphics g) {
                    g.drawImage(img, x, y, null);
                }
            });
            a.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        } else {
            a.setUI(new javax.swing.plaf.basic.BasicTextAreaUI() {
                @Override
                protected void paintBackground(Graphics g) {
                    g.drawString("", 0, 0);
                }
            });
        }
        a.setForeground(Color.black);
        a.setCaretColor(Color.lightGray);
        a.setEditable(false);
        fd.dialogAddMoreFiles.setResizable(false);
    }

    public void searchTab() {
        fd.jMenuBar1.add(Box.createHorizontalGlue());
        JTextField textField = new JTextField(10);
        textField.setForeground(Color.GRAY);
        textField.setText("Search...");
        textField.setMaximumSize(textField.getPreferredSize());
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                textField.setForeground(Color.BLACK);
                textField.setText("");
            }

            public void focusLost(FocusEvent e) {
                textField.setForeground(Color.GRAY);
                textField.setText("Search...");

            }

        });

        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    for (int i = 0; i < fd.tabbedPane.getTabCount(); i++) {
                        if (fd.tabbedPane.getTitleAt(i).equals(textField.getText())) {
                            fd.tabbedPane.setSelectedIndex(i);
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        fd.jMenuBar1.add(textField);
    }

    public void initElements() {
        fd.setSize(1080, 480);
        fd.jMenuBar1.add(fd.buttonValue);
        fd.jMenuBar1.add(fd.buttonExportCSV);
        fd.jMenuBar1.add(fd.orderDesc);
        fd.jMenuBar1.add(fd.orderAsc);
        fd.jMenuBar1.add(fd.buttonRemoveTable);
        fd.jMenuBar1.add(fd.buttonAverage);
        fd.jMenuBar1.add(fd.buttonDelete);
        fd.tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        fd.comboOptions.setFocusable(false);
        fd.buttonAddValue.setFocusable(false);
        fd.buttonRemoveItem.setFocusable(false);
        fd.buttonAverage.setFocusable(false);
        fd.finishButton.setFocusable(false);
        fd.panelGeneric.setLayout(new GridLayout(0, 1));
        fd.tabbedPane.addTab("Average", fd.panelGeneric);
        fd.tabbedPane.setVisible(false);
        fd.orderDesc.setVisible(false);
        fd.orderAsc.setVisible(false);
        fd.buttonValue.setVisible(false);
        fd.itemExport.setEnabled(false);
        fd.itemReset.setEnabled(false);
        fd.itemChangeTemperature.setEnabled(true);
        fd.buttonExportCSV.setVisible(false);
        fd.buttonRemoveTable.setVisible(false);
        fd.buttonAverage.setVisible(false);
        fd.buttonDelete.setVisible(false);
        fd.itemSearchValue.setEnabled(false);
        fd.itemSCF.setEnabled(false);
        fd.errorText.setVisible(true);

        if (!fd.buttonRemoveTable.isEnabled()) {
            fd.buttonRemoveTable.setToolTipText("\"Average\" table cannot be deleted.");
        }

    }
    
    public void buttonDeleteActionPerformed(ActionEvent evt){
        fd.errorText.setText("");
        fd.multiTable = false;
        fd.errorText.setForeground(Color.red);
        fd.tabbedPane.removeAll();
        fd.fieldKeyword.setText("");
        fd.usedFiles.clear();
        fd.normalTables.clear();
        fd.specialTables.clear();
        fd.usedTables.clear();
        fd.rows.clear();
        fd.colAndRows.clear();
        fd.keywordsUsed.clear();
        fd.itemSearchValue.setEnabled(false);
        fd.tabbedPane.addTab("Average", fd.panelGeneric);
        fd.tabbedPane.setVisible(false);
        fd.orderDesc.setVisible(false);
        fd.orderAsc.setVisible(false);
        fd.buttonExportCSV.setVisible(false);
        fd.buttonRemoveTable.setVisible(false);
        fd.buttonAverage.setVisible(false);
        fd.buttonDelete.setVisible(false);
        fd.panelGeneric.removeAll();
        fd.tabPaneSCF.removeAll();
        fd.tableGeneric = null;
        fd.buttonValue.setVisible(false);
        fd.itemReset.setEnabled(false);
        fd.itemExport.setEnabled(false);
        fd.itemSCF.setEnabled(false);
        fd.itemChangeTemperature.setEnabled(true);
    }
    
    public void itemResetActionPerformed(ActionEvent evt){
        fd.itemChangeTemperature.setEnabled(true);
        fd.multiTable = false;
        fd.errorText.setText("");
        fd.errorText.setForeground(Color.red);
        fd.tabbedPane.removeAll();
        fd.usedFiles.clear();
        fd.normalTables.clear();
        fd.fieldKeyword.setText("");
        fd.specialTables.clear();
        fd.usedTables.clear();
        fd.rows.clear();
        fd.colAndRows.clear();
        fd.keywordsUsed.clear();
        fd.itemSearchValue.setEnabled(false);
        fd.tabbedPane.addTab("Average", fd.panelGeneric);
        fd.tabbedPane.setVisible(false);
        fd.orderDesc.setVisible(false);
        fd.orderAsc.setVisible(false);
        fd.buttonExportCSV.setVisible(false);
        fd.buttonRemoveTable.setVisible(false);
        fd.buttonAverage.setVisible(false);
        fd.buttonDelete.setVisible(false);
        fd.panelGeneric.removeAll();
        fd.tabPaneSCF.removeAll();
        fd.tableGeneric = null;
        fd.buttonValue.setVisible(false);
        fd.itemReset.setEnabled(false);
        fd.itemExport.setEnabled(false);
    }
}
