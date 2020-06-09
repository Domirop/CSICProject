/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.log;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author daviddiaz
 */
public class DecorateFrame {

    FrameDifferentiator fd;
    AverageTable avg;

    public DecorateFrame(FrameDifferentiator fd, AverageTable avg) {
        this.fd = fd;
        this.avg = avg;
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
        fd.setSize(1080, 720);
        fd.buttonRemoveColumn.setVisible(false);
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
        fd.labelSelect.setVisible(false);
        fd.comboSelectRowsOrColumns.setVisible(false);

        if (!fd.buttonRemoveTable.isEnabled()) {
            fd.buttonRemoveTable.setToolTipText("\"Average\" table cannot be deleted.");
        }

    }

    public void removeTable() {
        if (fd.tabbedPane.isVisible()) {
            JPanel myPanel = (JPanel) (fd.tabbedPane.getSelectedComponent());
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();
            String name = myTable.getColumnName(2);

            avg.values.remove(name);
            avg.index = avg.index - 1;

            if (fd.usedTables.contains(myTable)) {
                fd.usedTables.remove(myTable);
            }

            if (fd.specialTables.contains(myTable)) {
                fd.specialTables.remove(myTable);
            }
            if (fd.keywordsUsed.contains(fd.tabbedPane.getTitleAt(fd.tabbedPane.getSelectedIndex()))) {
                fd.keywordsUsed.remove(fd.tabbedPane.getTitleAt(fd.tabbedPane.getSelectedIndex()));
            }

            for (int i = 0; i < fd.allFiles.size(); i++) {
                if (fd.allFiles.get(i).equals(name)) {
                    fd.allFiles.remove(i);
                    break;
                }
            }

            if (fd.tabbedPane.getSelectedIndex() == 0) {
                myPanel.remove(1);
                fd.multiTable = false;
                myPanel.repaint();
                fd.tabbedPane.repaint();
            } else {
                JPanel genericPane = (JPanel) (fd.tabbedPane.getComponentAt(0));
                JScrollPane scrollPaneGeneric = (JScrollPane) genericPane.getComponent(0);
                JViewport viewportGeneric = scrollPaneGeneric.getViewport();
                JTable genericTable = (JTable) viewportGeneric.getView();
                int index = 0;
                for (int i = 0; i < fd.normalTables.size(); i++) {
                    if (fd.normalTables.get(i) == myTable) {
                        index = i;
                    }
                }
                removeColumn(index + 2, genericTable);

                if (fd.normalTables.contains(myTable)) {
                    fd.normalTables.remove(myTable);
                }

                for (JTable specialTable : fd.specialTables) {
                    for (int i = 0; i < specialTable.getColumnCount(); i++) {
                        if (name.equals(specialTable.getColumnName(i))) {
                            removeColumn(i, specialTable);
                        }
                    }
                }
                fd.tabbedPane.remove(fd.tabbedPane.getSelectedComponent());
            }
        }
    }

    /**
     *
     * @param table
     * @param col_index
     */
    private void removeColumn(int index, JTable myTable) {
        int nRow = myTable.getRowCount();
        int nCol = myTable.getColumnCount() - 1;
        Object[][] cells = new Object[nRow][nCol];
        String[] names = new String[nCol];

        for (int j = 0; j < nCol; j++) {
            if (j < index) {
                names[j] = myTable.getColumnName(j);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j);
                }
            } else {
                names[j] = myTable.getColumnName(j + 1);
                for (int i = 0; i < nRow; i++) {
                    cells[i][j] = myTable.getValueAt(i, j + 1);
                }
            }
        }
        DefaultTableModel newModel = new DefaultTableModel(
                cells, names
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                Class<?> returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();

                } else {
                    returnValue = Object.class;
                }

                return returnValue;

            }
        ;
        };
        
        myTable.setModel(newModel);

        for (int i = 0; i < myTable.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            myTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) myTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        myTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        TableColumn column = null;
        for (int i = 0; i < myTable.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = myTable.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = myTable.getColumnModel().getColumn(i);
                column.setMinWidth(200);
            }
        }

        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }
}
