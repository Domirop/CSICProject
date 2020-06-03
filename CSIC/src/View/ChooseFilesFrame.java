/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerInt;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * View that is used to let the user pick files.
 *
 * @author domit
 */
public class ChooseFilesFrame extends javax.swing.JFrame {

    /**
     * Creates new form ChooseFilesFrame so the user can either choose files
     * from a directory or drop them into the frame.
     */
    List<String> filesTypes = new ArrayList<>(Arrays.asList("log"));
    List<File> listFiles = new ArrayList<>();
    ControllerInt controller;

    public ChooseFilesFrame(ControllerInt controller) {
        initComponents();
        enableDragAndDrop();
        BufferedImage bi = null;
        this.controller = controller;
        try {
            bi = ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/add-file.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        decorate(dropTextArea, bi);
    }

    /**
     * Method used to get the files dropped.
     */
    private void enableDragAndDrop() {
        DropTarget target = new DropTarget(dropTextArea, new DropTargetListener() {
            public void dragEnter(DropTargetDragEvent e) {
            }

            public void dragExit(DropTargetEvent e) {
            }

            public void dragOver(DropTargetDragEvent e) {
            }

            public void dropActionChanged(DropTargetDragEvent e) {
            }

            public void drop(DropTargetDropEvent e) {
                BufferedImage bi = null;
                decorate(dropTextArea, bi);
                try {
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    List list = (java.util.List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    list.forEach(v -> {
                        File file = (File) v;
                        if (filesTypes.contains(file.getName().split("\\.")[1])) {
                            dropTextArea.append(file.getName() + "\n");
                            listFiles.add(file);
                        }
                    });
                } catch (Exception ex) {
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonChooseFiles = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dropTextArea = new javax.swing.JTextArea();
        buttonNext = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldTemp = new javax.swing.JTextField();
        fieldValue = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataPicker4J");

        jLabel1.setText("Choose files:");

        buttonChooseFiles.setText("Open");
        buttonChooseFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseFilesActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        dropTextArea.setColumns(20);
        dropTextArea.setRows(5);
        jScrollPane1.setViewportView(dropTextArea);

        buttonNext.setText("Next");
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        jLabel2.setText("Temperature:");

        jLabel3.setText("Max Value:");

        fieldTemp.setText("298.15");
        fieldTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldTempActionPerformed(evt);
            }
        });

        fieldValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonChooseFiles)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(fieldValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonNext)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonChooseFiles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNext))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the button is clicked, opens a new window so the user can select
     * files from a directory.
     *
     * @param evt
     */
    private void buttonChooseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseFilesActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Files", "log"));
        fileChooser.setMultiSelectionEnabled(true);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedImage bi = null;
            decorate(dropTextArea, bi);
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                dropTextArea.append(file.getName() + "\n");
                listFiles.add(file);
            }
        }
    }//GEN-LAST:event_buttonChooseFilesActionPerformed

    /**
     * When the files are selected, closes this frame and opens the next one.
     *
     * @param evt
     */
    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        confirm();
    }//GEN-LAST:event_buttonNextActionPerformed

    private void fieldValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValueActionPerformed
        confirm();
    }//GEN-LAST:event_fieldValueActionPerformed

    private void fieldTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldTempActionPerformed
       confirm();
    }//GEN-LAST:event_fieldTempActionPerformed

    private void confirm() {
        if (dropTextArea.getText().length() > 0) {
            if (fieldValue.getText().matches("^[0-9]+(.)?[0-9]{0,2}")) {
                if (fieldTemp.getText().matches("^[0-9]+(.)?[0-9]{0,2}")) {
                    List<String> fileNames = new ArrayList<>();
                    for (File listFile : listFiles) {
                        fileNames.add(listFile.getName());
                    }
                    FrameDifferentiator frameDiff = new FrameDifferentiator(fileNames, listFiles, controller, fieldTemp.getText(), fieldValue.getText());
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frameDiff.setLocation(dim.width / 2 - frameDiff.getSize().width / 2, dim.height / 2 - frameDiff.getSize().height / 2);
                    this.dispose();
                    frameDiff.setVisible(true);
                } else {
                    fieldTemp.setBorder(BorderFactory.createLineBorder(Color.red));
                    fieldTemp.setToolTipText("The format is (number).(2 number)");
                }
            } else {
                fieldValue.setBorder(BorderFactory.createLineBorder(Color.red));
                fieldValue.setToolTipText("The format is (number).(2 number)");
            }
        }
    }

    /**
     * Method used to put the icon as a background.
     *
     * @param a the TextArea used to drop files.
     * @param img icon shown.
     */
    public void decorate(JTextArea a, final BufferedImage img) {
        if (img != null) {
            int x = (this.getWidth() - img.getWidth(null) - 20) / 2;
            int y = (this.getHeight() - img.getHeight(null)) / 4;

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
        this.setResizable(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChooseFiles;
    private javax.swing.JButton buttonNext;
    private javax.swing.JTextArea dropTextArea;
    private javax.swing.JTextField fieldTemp;
    private javax.swing.JTextField fieldValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
