/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Atomo.FileData;
import Model.Atomo.Molecule;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import Controller.ControllerInt;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Contains all the necesary methods to show the user the application.
 *
 * @author daviddiaz
 *
 *
 */
public class FrameDifferentiator extends javax.swing.JFrame {

    /**
     * Creates new form FrameDifferentiator to show the tables.
     */
    private List<String> files = new ArrayList<>();
    private List<File> filesData = new ArrayList<>();
    private JPanel panelGeneric = new JPanel();
    private List<String> names = new ArrayList<>();
    private List<File> usedFiles = new ArrayList<>();
    private List<JTable> normalTables = new ArrayList<>();
    private List<JTable> specialTables = new ArrayList<>();
    private List<JTable> usedTables = new ArrayList<>();
    private List<String> keywordsUsed = new ArrayList<>();
    private List<List<Object>> rows = new ArrayList<>();
    public List<String> coorValues = new ArrayList<>();
    private ControllerInt controller;
    private boolean multiTable = false;
    private boolean searchAdded = false;

    List<String> filesTypes = new ArrayList<>(Arrays.asList("log"));
    private String temperature = "298.15";
    public List<String> colAndRows = new ArrayList<>();
    JTable tableGeneric;

    public FrameDifferentiator(ControllerInt controller) {
        initComponents();
        this.controller = controller;
    }

    public FrameDifferentiator(List<String> files, List<File> filesData, ControllerInt controller) {
        initComponents();
        this.setSize(1080, 480);
        jMenuBar1.add(buttonValue);
        jMenuBar1.add(buttonExportCSV);
        jMenuBar1.add(orderDesc);
        jMenuBar1.add(orderAsc);
        jMenuBar1.add(buttonRemoveTable);
        jMenuBar1.add(buttonAverage);
        jMenuBar1.add(buttonDelete);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        ListTransferHandler lh = new ListTransferHandler(this);
        listValues.setModel(new DefaultListModel());
        listValues.setDragEnabled(true);
        listValues.setTransferHandler(lh);
        listValues.setDropMode(DropMode.ON_OR_INSERT);
        setMappings(listValues);
        comboOptions.setFocusable(false);
        buttonAddValue.setFocusable(false);
        buttonRemoveItem.setFocusable(false);
        buttonAverage.setFocusable(false);
        finishButton.setFocusable(false);
        //listValues.setFocusable(false);
        this.controller = controller;
        panelGeneric.setLayout(new GridLayout(0, 1));
        tabbedPane.addTab("Average", panelGeneric);
        tabbedPane.setVisible(false);
        orderDesc.setVisible(false);
        orderAsc.setVisible(false);
        buttonValue.setVisible(false);
        itemExport.setEnabled(false);
        itemReset.setEnabled(false);
        itemChangeTemperature.setEnabled(true);
        buttonExportCSV.setVisible(false);
        buttonRemoveTable.setVisible(false);
        buttonAverage.setVisible(false);
        buttonDelete.setVisible(false);
        this.files = files;
        itemSearchValue.setEnabled(false);
        this.errorText.setVisible(true);
        this.filesData = filesData;
        itemSCF.setEnabled(false);
        if (!buttonRemoveTable.isEnabled()) {
            buttonRemoveTable.setToolTipText("\"Average\" table cannot be deleted.");
        }
        addIcons();
    }

    public void addIcons() {

        ImageIcon imageIcon;
        try {
            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/rightarrow.png"))); // load the image to a imageIcon

            Image imageadd = imageIcon.getImage(); // transform it 
            Image newimgadd = imageadd.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgadd);  // transform it back
            buttonAdd.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/search.png"))); // load the image to a imageIcon
            Image imagesearch = imageIcon.getImage(); // transform it 
            Image newimgsearch = imagesearch.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgsearch);  // transform it back
            buttonValue.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/csv.png"))); // load the image to a imageIcon
            Image imagecsv = imageIcon.getImage(); // transform it 
            Image newimgcsv = imagecsv.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgcsv);  // transform it back
            buttonExportCSV.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/reset.png"))); // load the image to a imageIcon
            Image imagereset = imageIcon.getImage(); // transform it 
            Image newimgreset = imagereset.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgreset);  // transform it back
            buttonDelete.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/delete.png"))); // load the image to a imageIcon
            Image imagedelete = imageIcon.getImage(); // transform it 
            Image newimgdelete = imagedelete.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgdelete);  // transform it back
            buttonRemoveTable.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/increase.png"))); // load the image to a imageIcon
            Image imageincrease = imageIcon.getImage(); // transform it 
            Image newimgincrease = imageincrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgincrease);  // transform it back
            orderAsc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/decrease.png"))); // load the image to a imageIcon
            Image imagedecrease = imageIcon.getImage(); // transform it 
            Image newimgdecrease = imagedecrease.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgdecrease);  // transform it back
            orderDesc.setIcon(imageIcon);

            imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/average.png"))); // load the image to a imageIcon
            Image imageaverage = imageIcon.getImage(); // transform it 
            Image newimgaverage = imageaverage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimgaverage);  // transform it back
            buttonAverage.setIcon(imageIcon);

            this.repaint();

        } catch (IOException ex) {
        }
    }

    /**
     * This method is used for ability the functions drop to add new files.
     */
    private void enableDragAndDrop() {
        DropTarget target = new DropTarget(textAreaMoreFiles, new DropTargetListener() {
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
                decorate(textAreaMoreFiles, bi);
                try {
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    List list = (java.util.List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    list.forEach(v -> {
                        File file = (File) v;
                        if (filesTypes.contains(file.getName().split("\\.")[1])) {
                            textAreaMoreFiles.append(file.getName() + "\n");
                            filesData.add(file);
                        }
                    });
                    dialogAddMoreFiles.pack();
                    dialogAddMoreFiles.revalidate();
                    dialogAddMoreFiles.repaint();
                } catch (Exception ex) {
                }
            }
        });
    }

    /**
     * this method decorate de textArea to the dialogAddMoreFiles.
     *
     * @param a The textArea will be decorate.
     * @param img The image which you want decorate.
     */
    public void decorate(JTextArea a, final BufferedImage img) {
        if (img != null) {
            int x = (dialogAddMoreFiles.getPreferredSize().width - img.getWidth(null) - 20) / 2;
            int y = (dialogAddMoreFiles.getPreferredSize().height - img.getHeight(null)) / 4;
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
        dialogAddMoreFiles.setResizable(false);
    }

    /**
     * This method used to recharge the List of the dialogCoordinates when you
     * copy an paste elements in JList.
     *
     * @param row Reference of the coordinates.
     * @param column Reference of the coordinates.
     */
    public void addElementsToRows(int row, int column) {
        coorValues.add(row + "," + column);
        colAndRows.add(row + "," + column);
        errorDialogCoor.setText("");
    }

    /**
     * This method used to control the textError of the dialogCoordinates when
     * you copy an paste elements in JList.
     *
     * @param string The message which you want put
     */
    public void setErrorDialogCoor(String string) {
        this.errorDialogCoor.setText(string);
    }

    /**
     * This methos use to map the element you copy to elements of the JList,
     *
     * @param list JList when element you copy will be paste.
     */
    private void setMappings(JList list) {
        ActionMap map = list.getActionMap();
        map.put(TransferHandler.getCutAction().getValue(Action.NAME),
                TransferHandler.getCutAction());
        map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
                TransferHandler.getCopyAction());
        map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
                TransferHandler.getPasteAction());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogCoordinates = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        fieldRow = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fieldColumn = new javax.swing.JTextField();
        buttonAddValue = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        errorDialogCoor = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listValues = new javax.swing.JList<>();
        buttonRemoveItem = new javax.swing.JButton();
        dialogNombre = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        fieldNameValues = new javax.swing.JTextField();
        buttonValues = new javax.swing.JButton();
        dialogTemperature = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        fieldTemperature = new javax.swing.JTextField();
        buttonOKTemp = new javax.swing.JButton();
        dialogAddMoreFiles = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        buttonChooseFiles = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaMoreFiles = new javax.swing.JTextArea();
        dialogSCF = new javax.swing.JDialog();
        tabPaneSCF = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        comboOptions = new javax.swing.JComboBox<>();
        fieldKeyword = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        buttonDelete = new javax.swing.JButton();
        buttonExportCSV = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        buttonValue = new javax.swing.JButton();
        orderAsc = new javax.swing.JButton();
        orderDesc = new javax.swing.JButton();
        buttonRemoveTable = new javax.swing.JButton();
        buttonAverage = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemSearchValue = new javax.swing.JMenuItem();
        itemExport = new javax.swing.JMenuItem();
        itemReset = new javax.swing.JMenuItem();
        itemChooseFiles = new javax.swing.JMenuItem();
        itemChangeTemperature = new javax.swing.JMenuItem();
        itemAddMoreFiles = new javax.swing.JMenuItem();
        itemSCF = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();

        dialogCoordinates.setResizable(false);

        jLabel2.setText("Row:");

        fieldRow.setFocusCycleRoot(true);
        fieldRow.setNextFocusableComponent(fieldColumn);
        fieldRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldRowActionPerformed(evt);
            }
        });

        jLabel3.setText("Column:");

        fieldColumn.setFocusCycleRoot(true);
        fieldColumn.setNextFocusableComponent(fieldRow);
        fieldColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldColumnActionPerformed(evt);
            }
        });

        buttonAddValue.setText(">");
        buttonAddValue.setFocusCycleRoot(true);
        buttonAddValue.setFocusable(false);
        buttonAddValue.setNextFocusableComponent(fieldRow);
        buttonAddValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddValueActionPerformed(evt);
            }
        });

        finishButton.setText("Finish");
        finishButton.setFocusable(false);
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        errorDialogCoor.setForeground(new java.awt.Color(255, 0, 0));

        jScrollPane2.setViewportView(listValues);

        buttonRemoveItem.setText("Remove");
        buttonRemoveItem.setFocusable(false);
        buttonRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogCoordinatesLayout = new javax.swing.GroupLayout(dialogCoordinates.getContentPane());
        dialogCoordinates.getContentPane().setLayout(dialogCoordinatesLayout);
        dialogCoordinatesLayout.setHorizontalGroup(
            dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorDialogCoor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                        .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogCoordinatesLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(fieldRow, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(buttonAddValue)))
                .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonRemoveItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(finishButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dialogCoordinatesLayout.setVerticalGroup(
            dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                        .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(buttonAddValue))
                            .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                                .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(fieldRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(dialogCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(fieldColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addComponent(errorDialogCoor, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addGroup(dialogCoordinatesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonRemoveItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(finishButton)))
                .addContainerGap())
        );

        jLabel4.setText("Choose a name for the values");

        buttonValues.setText("Ok");
        buttonValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValuesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogNombreLayout = new javax.swing.GroupLayout(dialogNombre.getContentPane());
        dialogNombre.getContentPane().setLayout(dialogNombreLayout);
        dialogNombreLayout.setHorizontalGroup(
            dialogNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogNombreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dialogNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonValues)
                    .addComponent(fieldNameValues, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dialogNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dialogNombreLayout.setVerticalGroup(
            dialogNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogNombreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNameValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonValues)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Change temperature:");

        fieldTemperature.setText("298.15");

        buttonOKTemp.setText("Ok");
        buttonOKTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKTempActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogTemperatureLayout = new javax.swing.GroupLayout(dialogTemperature.getContentPane());
        dialogTemperature.getContentPane().setLayout(dialogTemperatureLayout);
        dialogTemperatureLayout.setHorizontalGroup(
            dialogTemperatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogTemperatureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(fieldTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogTemperatureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonOKTemp)
                .addContainerGap())
        );
        dialogTemperatureLayout.setVerticalGroup(
            dialogTemperatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogTemperatureLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogTemperatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonOKTemp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Choose files:");

        buttonChooseFiles.setText("Open");
        buttonChooseFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseFilesActionPerformed(evt);
            }
        });

        buttonNext.setText("Add");
        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        textAreaMoreFiles.setBackground(new java.awt.Color(255, 255, 255));
        textAreaMoreFiles.setColumns(20);
        textAreaMoreFiles.setRows(5);
        jScrollPane3.setViewportView(textAreaMoreFiles);

        javax.swing.GroupLayout dialogAddMoreFilesLayout = new javax.swing.GroupLayout(dialogAddMoreFiles.getContentPane());
        dialogAddMoreFiles.getContentPane().setLayout(dialogAddMoreFilesLayout);
        dialogAddMoreFilesLayout.setHorizontalGroup(
            dialogAddMoreFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogAddMoreFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogAddMoreFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogAddMoreFilesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonNext))
                    .addGroup(dialogAddMoreFilesLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonChooseFiles)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dialogAddMoreFilesLayout.setVerticalGroup(
            dialogAddMoreFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogAddMoreFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogAddMoreFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(buttonChooseFiles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNext)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialogSCF.setResizable(false);

        javax.swing.GroupLayout dialogSCFLayout = new javax.swing.GroupLayout(dialogSCF.getContentPane());
        dialogSCF.getContentPane().setLayout(dialogSCFLayout);
        dialogSCFLayout.setHorizontalGroup(
            dialogSCFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPaneSCF)
        );
        dialogSCFLayout.setVerticalGroup(
            dialogSCFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPaneSCF)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Search by keyword:");

        comboOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Starts with", "Ends with", "Contains", "Range starts with", "Range ends with" }));
        comboOptions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOptionsItemStateChanged(evt);
            }
        });

        fieldKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldKeywordActionPerformed(evt);
            }
        });

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        buttonDelete.setText("Reset");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonExportCSV.setText("Export to CSV");
        buttonExportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportCSVActionPerformed(evt);
            }
        });

        errorText.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonValue.setText("Search value");
        buttonValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValueActionPerformed(evt);
            }
        });

        orderAsc.setText("Order asc.");
        orderAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderAscActionPerformed(evt);
            }
        });

        orderDesc.setText("Order desc.");
        orderDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderDescActionPerformed(evt);
            }
        });

        buttonRemoveTable.setText("Delete table");
        buttonRemoveTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveTableActionPerformed(evt);
            }
        });

        buttonAverage.setText("Average values");
        buttonAverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAverageActionPerformed(evt);
            }
        });

        jMenu1.setText("Options");

        itemSearchValue.setText("Search values");
        itemSearchValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSearchValueActionPerformed(evt);
            }
        });
        jMenu1.add(itemSearchValue);

        itemExport.setText("Export to CSV");
        itemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportActionPerformed(evt);
            }
        });
        jMenu1.add(itemExport);

        itemReset.setText("Reset");
        itemReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemResetActionPerformed(evt);
            }
        });
        jMenu1.add(itemReset);

        itemChooseFiles.setText("Choose files");
        itemChooseFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChooseFilesActionPerformed(evt);
            }
        });
        jMenu1.add(itemChooseFiles);

        itemChangeTemperature.setText("Change temperature");
        itemChangeTemperature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChangeTemperatureActionPerformed(evt);
            }
        });
        jMenu1.add(itemChangeTemperature);

        itemAddMoreFiles.setText("Add more files");
        itemAddMoreFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAddMoreFilesActionPerformed(evt);
            }
        });
        jMenu1.add(itemAddMoreFiles);

        itemSCF.setText("SCF and energy value");
        itemSCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSCFActionPerformed(evt);
            }
        });
        jMenu1.add(itemSCF);

        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        jMenu1.add(itemExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonValue))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fieldKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAdd)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonExportCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderAsc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRemoveTable, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(332, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(buttonAverage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(buttonRemoveTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(orderAsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(buttonValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(buttonExportCSV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(orderDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method used to get the click event.
     *
     * @param evt
     */
    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        names.clear();
        usedFiles.clear();
        if (fieldKeyword.getText().length() > 0) {
            getUsedFiles(fieldKeyword.getText(), comboOptions.getSelectedItem().toString(), true);
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    /**
     * Creates a myTable with every single keyword.
     *
     * @return the myTable.
     */
    private JTable initTablesDifferentiators() {
        JTable table = new JTable();
        table.setAutoCreateRowSorter(true);
        List<String> singleNames = new ArrayList<>();
        singleNames.add("Gaussian");
        singleNames.add("Atom");

        names.stream().filter((file) -> (!singleNames.contains(file))).forEachOrdered((file) -> {
            singleNames.add(file);
        });
        boolean[] canEditTry = new boolean[singleNames.size()];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }
        DefaultTableModel model = new DefaultTableModel(
                singleNames.toArray(new String[0]),
                0) {
            boolean[] canEdit = canEditTry;

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        table.setModel(model);
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        return table;
    }

    /**
     * Method used to add rows to the myTable.
     *
     * @param table the myTable that we want to add rows to.
     * @return myTable with rows added.
     */
    private JTable addRowsToTable(JTable table) {
        JTable tableWithElements = table;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<FileData> fileData = this.controller.getFileData(usedFiles, Double.parseDouble(temperature));
        String fileNameError = "";
        boolean correcto = false;
        for (String name : names) {
            salir:
            if (!correcto) {
                for (FileData fileData1 : fileData) {
                    if (fileData1.getFileName().equals(name)) {
                        fileNameError = "";
                        break salir;
                    } else {
                        correcto = true;
                        fileNameError = name;
                    }
                }
            }
        }
        if (fileData.size() == usedFiles.size()) {
            errorText.setText("");
            List<Object> data = new ArrayList<>();
            for (int i = 0; i < fileData.get(0).getAtoms().size(); i++) {
                data.clear();
                for (int j = 1; j < fileData.size() + 1; j++) {
                    if (j == 1) {
                        data.add(fileData.get(j - 1).getAtoms().get(i).getGaussianData());
                        data.add(fileData.get(j - 1).getAtoms().get(i).getAtom());
                    }
                    data.add(fileData.get(j - 1).getAtoms().get(i).getIsotropic());
                }
                model.addRow(data.toArray(new Object[0]));
            }
            return tableWithElements;
        } else {
            keywordsUsed.remove(fieldKeyword.getText());
            errorText.setText("Syntax error. Please, check the file " + fileNameError + ".");
            return table;
        }
    }

    /**
     * Add the used files to a List.F
     */
    private void getUsedFiles(String value, String caseValue, boolean isAdd) {
        switch (caseValue) {
            case "Starts with":
                for (int i = 0; i < files.size(); i++) {
                    String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                    if (filename.startsWith(value)) {
                        usedFiles.add(filesData.get(i));
                        if (!names.contains(filename)) {
                            names.add(filename);
                        }
                    }
                }
                if (this.getSize() != new Dimension(1080, 480)) {
            this.setSize(1080, 480);
        }
                if (isAdd) {
                    actionButtonAdd(fieldKeyword.getText());
                }
                break;
            case "Ends with":
                for (int i = 0; i < files.size(); i++) {
                    String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                    if (filename.endsWith(value)) {
                        usedFiles.add(filesData.get(i));
                        names.add(filename);
                    }
                }
                if (isAdd) {
                    actionButtonAdd(fieldKeyword.getText());
                }
                break;
            case "Contains":
                for (int i = 0; i < files.size(); i++) {
                    String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                    if (filename.contains(value)) {
                        usedFiles.add(filesData.get(i));
                        names.add(filename);
                    }
                }
                actionButtonAdd(fieldKeyword.getText());
                break;
            case "Range starts with":
                if (value.matches("^[0-9]+(\\-[0-9]+)*$")) {
                    String[] values = new String[2];
                    boolean fFileCero = false;
                    boolean sFileCero = false;
                    values = value.split("-");
                    if (values[0].startsWith("0")) {
                        values[0].replace("0", "");
                        fFileCero = true;
                    }

                    if (values[1].startsWith("0")) {
                        values[1].replace("0", "");
                        sFileCero = true;
                    }

                    for (int j = Integer.valueOf(values[0]); j <= Integer.valueOf(values[1]); j++) {
                        usedFiles.clear();
                        names.clear();
                        String charact = "";
                        if (fFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }
                        String separador = "";
                        for (int i = 0; i < files.size(); i++) {
                            String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                            searchSeparator:
                            for (char c : filename.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    separador = String.valueOf(c);
                                    break searchSeparator;
                                }
                            }
                            if (filename.split(separador)[0].equals(charact)) {
                                usedFiles.add(filesData.get(i));
                                if (!names.contains(filename)) {
                                    names.add(filename);
                                }
                            }
                        }
                        actionButtonAdd(charact);
                    }
                }
                break;
            case "Range ends with":
                if (value.matches("^[0-9]+(\\-[0-9]+)*$")) {
                    String[] values = new String[2];
                    boolean fFileCero = false;
                    boolean sFileCero = false;
                    values = value.split("-");
                    if (values[0].startsWith("0")) {
                        values[0].replace("0", "");
                        fFileCero = true;
                    }
                    if (values[1].startsWith("0")) {
                        values[1].replace("0", "");
                        sFileCero = true;
                    }
                    for (int j = Integer.valueOf(values[0]); j <= Integer.valueOf(values[1]); j++) {
                        usedFiles.clear();
                        names.clear();
                        String charact = "";
                        if (fFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }

                        if (sFileCero == true && j < 10) {
                            charact = "0" + String.valueOf(j);
                        } else {
                            charact = String.valueOf(j);
                        }
                        String separador = "";
                        for (int i = 0; i < files.size(); i++) {
                            String filename = files.get(i).contains(".log") ? files.get(i).replace(".log", "") : files.get(i).replace(".txt", "");
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append(filename);
                            sb1 = sb1.reverse();
                            String reversedFilename = sb1.toString();

                            searchSeparator:
                            for (char c : reversedFilename.toCharArray()) {
                                if (!Character.isDigit(c)) {
                                    separador = String.valueOf(c);
                                    break searchSeparator;
                                }
                            }
                            String[] separated = filename.split(separador);
                            if (separated[separated.length - 1].equals(charact)) {
                                usedFiles.add(filesData.get(i));
                                if (!names.contains(filename)) {
                                    names.add(filename);
                                }
                            }
                        }
                        actionButtonAdd(charact);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * The same function that button add.
     *
     * @param evt
     */
    private void fieldKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldKeywordActionPerformed
        names.clear();
        usedFiles.clear();
        if (fieldKeyword.getText().length() > 0) {
            getUsedFiles(fieldKeyword.getText(), comboOptions.getSelectedItem().toString(), true);
        }
    }//GEN-LAST:event_fieldKeywordActionPerformed

    /**
     * Gets column and rows.
     *
     * @param evt
     */
    private void buttonAddValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddValueActionPerformed
        addValueToList();

    }//GEN-LAST:event_buttonAddValueActionPerformed

    /**
     * This method add element to the JList of the dialogCoordinates.
     */
    public void addValueToList() {
        String regex = "\\d+";
        if (fieldRow.getText().matches(regex) && fieldColumn.getText().matches(regex)) {
            int row = Integer.parseInt(fieldRow.getText());
            int column = Integer.parseInt(fieldColumn.getText());
            if (row <= tableGeneric.getRowCount() || column <= tableGeneric.getRowCount()) {
                if (row >= column) {
                    coorValues.add(row + "," + column);
                    colAndRows.add(row + "," + column);
                    String[] vals = coorValues.toArray(new String[0]);
                    listValues.setListData(vals);
                    errorDialogCoor.setText("");
                } else {
                    errorDialogCoor.setText("<html><body>The value of the row must be greater than the column value.</body></html>");
                }
            } else {
                errorDialogCoor.setText("<html><body>The value of the column and row must be greater than the number gaussians.</body></html>");

            }
        } else {
            errorDialogCoor.setText("The values need to be integer.");
        }
        fieldRow.setText("");
        fieldColumn.setText("");
        fieldRow.requestFocusInWindow();
        fieldRow.requestFocus();
    }

    /**
     * Closes the dialog and opens the dialog that asks for a name.
     *
     * @param evt
     */
    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        errorDialogCoor.setVisible(false);
        listValues.setListData(new String[0]);
        coorValues.clear();
        errorDialogCoor.setText("");
        dialogCoordinates.dispose();
        dialogNombre.pack();
        dialogNombre.setVisible(true);
    }//GEN-LAST:event_finishButtonActionPerformed

    /**
     * Gets the name of the myTable and creates it with all the data from the
     * coordinates.
     *
     * @param evt
     */
    private void buttonValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValuesActionPerformed
        orderDesc.setVisible(true);
        orderAsc.setVisible(true);
        JPanel newPane = new JPanel();
        newPane.setLayout(new GridLayout(0, 1));
        String[] values = new String[keywordsUsed.size() + 2];
        values[0] = "Row";
        values[1] = "Column";
        for (int i = 2; i < keywordsUsed.size() + 2; i++) {
            values[i] = keywordsUsed.get(i - 2);
        }

        JTable tableCoord = new JTable();
        boolean[] canEditTry = new boolean[2 + keywordsUsed.size()];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelTable = new DefaultTableModel(values, 0) {
            boolean[] canEdit = canEditTry;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        tableCoord.setModel(modelTable);
        //Center columns
        for (int i = 0; i < tableCoord.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableCoord.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableCoord.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableCoord.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        DefaultTableModel model = (DefaultTableModel) tableCoord.getModel();

        List<Molecule> molList = new ArrayList<>();

        for (int i = 2; i < tableCoord.getColumnCount(); i++) {
            usedFiles.clear();
            getUsedFiles(tableCoord.getColumnName(i), "Starts with", false);
            Molecule mole = new Molecule();
            try {
                mole = controller.getTableMolecule(usedFiles, colAndRows, tableCoord.getColumnName(i), Double.parseDouble(temperature));
            } catch (Exception e) {
                dialogNombre.dispose();
                errorText.setVisible(true);
                errorText.setText("Some files were not imported or format file isn't correct.");
                return;
            }
            molList.add(mole);
        }

        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < colAndRows.size(); i++) {
            List<String> val = new ArrayList<>();
            String[] coord = colAndRows.get(i).split(",");
            val.add(coord[0]);
            val.add(coord[1]);
            for (int j = 0; j < molList.size(); j++) {
                double value = molList.get(j).getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                val.add(String.valueOf(df.format(value)));
            }
            rows.add(val);
        }

        rows.forEach((row) -> {
            model.addRow(row.toArray());
        });
        tableCoord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        specialTables.add(tableCoord);
        usedTables.add(tableCoord);
        JScrollPane scrollpaneHola = new JScrollPane(tableCoord, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        newPane.add(scrollpaneHola);
        tabbedPane.insertTab(fieldNameValues.getText(), new ImageIcon(""), newPane, null, 1);
        dialogNombre.dispose();
        fieldNameValues.setText("");

    }//GEN-LAST:event_buttonValuesActionPerformed

    /**
     * Button to search myTable values
     *
     * @param evt
     */
    private void itemSearchValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSearchValueActionPerformed
        if (tabbedPane.isVisible()) {
            dialogCoordinates.pack();
            dialogCoordinates.setVisible(true);
            colAndRows.clear();
        }
    }//GEN-LAST:event_itemSearchValueActionPerformed

    /**
     * Button to export tables to CSV
     *
     * @param evt
     */
    private void itemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportActionPerformed
        errorText.setForeground(Color.red);
        String folder = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.exists()) {
                new File(file.getAbsolutePath()).mkdir();
            }
            folder = file.getAbsolutePath();
        }

        keywordsUsed.add("Average");

        if (usedTables.size() > 0) {
            for (int i = 0; i < keywordsUsed.size(); i++) {
                List<String[]> datas = new ArrayList<>();
                TableModel model = usedTables.get(i).getModel();
                String[] columnNames = new String[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    columnNames[j] = model.getColumnName(j);
                }
                datas.add(columnNames);
                for (int k = 0; k < model.getRowCount(); k++) {
                    String[] data = new String[model.getColumnCount()];
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        if (model.getValueAt(k, j) != null && model.getValueAt(k, j).toString().trim().length() != 0) {
                            data[j] = String.valueOf(model.getValueAt(k, j));
                        }
                    }
                    datas.add(data);
                }
                if (this.controller.writeCSV(datas, folder, keywordsUsed.get(i))) {
                    errorText.setForeground(Color.green);
                    errorText.setText("All files have been created.");
                }
            }
        }
    }//GEN-LAST:event_itemExportActionPerformed

    /**
     * Clears all the tables.
     *
     * @param evt
     */
    private void itemResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemResetActionPerformed
        itemChangeTemperature.setEnabled(true);
        multiTable = false;
        errorText.setText("");
        errorText.setForeground(Color.red);
        tabbedPane.removeAll();
        usedFiles.clear();
        normalTables.clear();
        fieldKeyword.setText("");
        specialTables.clear();
        usedTables.clear();
        rows.clear();
        colAndRows.clear();
        keywordsUsed.clear();
        itemSearchValue.setEnabled(false);
        tabbedPane.addTab("Average", panelGeneric);
        tabbedPane.setVisible(false);
        orderDesc.setVisible(false);
        orderAsc.setVisible(false);
        buttonExportCSV.setVisible(false);
        buttonRemoveTable.setVisible(false);
        buttonAverage.setVisible(false);
        buttonDelete.setVisible(false);
        panelGeneric.removeAll();
        tabPaneSCF.removeAll();
        tableGeneric = null;
        buttonValue.setVisible(false);
        itemReset.setEnabled(false);
        itemExport.setEnabled(false);
    }//GEN-LAST:event_itemResetActionPerformed

    /**
     * Clears all the tables.
     *
     * @param evt
     */
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        errorText.setText("");
        multiTable = false;
        errorText.setForeground(Color.red);
        tabbedPane.removeAll();
        fieldKeyword.setText("");
        usedFiles.clear();
        normalTables.clear();
        specialTables.clear();
        usedTables.clear();
        rows.clear();
        colAndRows.clear();
        keywordsUsed.clear();
        itemSearchValue.setEnabled(false);
        tabbedPane.addTab("Average", panelGeneric);
        tabbedPane.setVisible(false);
        orderDesc.setVisible(false);
        orderAsc.setVisible(false);
        buttonExportCSV.setVisible(false);
        buttonRemoveTable.setVisible(false);
        buttonAverage.setVisible(false);
        buttonDelete.setVisible(false);
        panelGeneric.removeAll();
        tabPaneSCF.removeAll();
        tableGeneric = null;
        buttonValue.setVisible(false);
        itemReset.setEnabled(false);
        itemExport.setEnabled(false);
        itemSCF.setEnabled(false);
        itemChangeTemperature.setEnabled(true);
    }//GEN-LAST:event_buttonDeleteActionPerformed

    /**
     * Method used to export the tables to a CSV file
     *
     * @param evt
     */
    private void buttonExportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportCSVActionPerformed
        keywordsUsed.add("Average");
        errorText.setForeground(Color.red);
        String folder = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.exists()) {
                new File(file.getAbsolutePath()).mkdir();
            }
            folder = file.getAbsolutePath();
        }
        if (usedTables.size() > 0) {
            for (int i = 0; i < tabbedPane.getTabCount() + 1; i++) {
                List<String[]> datas = new ArrayList<>();
                if (i == 0) {
                    if (multiTable == true) {
                        datas = exportTable(null, i, 0, tabbedPane);
                        datas.add(new String[0]);
                        datas.add(new String[0]);
                        datas.addAll(exportTable(null, i, 1, tabbedPane));
                        exportCSVFunction(datas, folder, "Average");
                    } else {
                        datas = exportTable(null, i, 0, tabbedPane);
                        exportCSVFunction(datas, folder, "Average");
                    }
                } else if (i == tabbedPane.getTabCount()) {
                    JTabbedPane panes = (JTabbedPane) dialogSCF.getContentPane().getComponent(0);
                    for (int j = 0; j < panes.getTabCount(); j++) {
                        JPanel myPanel = (JPanel) panes.getComponentAt(j);
                        JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
                        JViewport viewport = scrollPane.getViewport();
                        JTable myTable = (JTable) viewport.getView();
                        datas.addAll(exportTable(myTable, j, 0, panes));
                    }
                    exportCSVFunction(datas, folder, "SCF_and_CONTRIBUTION");
                } else {
                    datas = exportTable(null, i, 0, tabbedPane);
                    exportCSVFunction(datas, folder, tabbedPane.getTitleAt(i));
                }
            }
        }
    }//GEN-LAST:event_buttonExportCSVActionPerformed

    private void exportCSVFunction(List<String[]> datas, String folder, String nameFile) {
        if (this.controller.writeCSV(datas, folder, nameFile)) {
            errorText.setForeground(Color.green);
            errorText.setText("All files have been created.");
        } else {
            errorText.setForeground(Color.RED);
            errorText.setText("Error has ocurred in table " + nameFile);
        }
    }

    private List<String[]> exportTable(JTable myTable, int indexTab, int indexComponent, JTabbedPane pane) {
        List<String[]> datas = new ArrayList<>();
        if (myTable == null) {
            JPanel myPanel = (JPanel) (pane.getComponentAt(indexTab));
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(indexComponent);
            JViewport viewport = scrollPane.getViewport();
            myTable = (JTable) viewport.getView();
        }
        String[] columnNames = new String[myTable.getColumnCount()];
        for (int j = 0; j < myTable.getColumnCount(); j++) {
            columnNames[j] = myTable.getColumnName(j);
        }
        datas.add(columnNames);
        for (int k = 0; k < myTable.getRowCount(); k++) {
            String[] data = new String[myTable.getColumnCount()];
            for (int j = 0; j < myTable.getColumnCount(); j++) {
                if (myTable.getValueAt(k, j) != null && myTable.getValueAt(k, j).toString().trim().length() != 0) {
                    data[j] = String.valueOf(myTable.getValueAt(k, j));
                }
            }
            datas.add(data);
        }
        return datas;
    }

    /**
     * Shows the dialog that gets the column and rows.
     *
     * @param evt
     */
    private void buttonValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValueActionPerformed
        dialogCoordinates.pack();
        listValues.removeAll();
        dialogCoordinates.setVisible(true);
        colAndRows.clear();
        fieldRow.requestFocusInWindow();
        dialogCoordinates.getRootPane().setDefaultButton(buttonAdd);


    }//GEN-LAST:event_buttonValueActionPerformed

    /**
     * Choose files button
     *
     * @param evt
     */
    private void itemChooseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChooseFilesActionPerformed
        ChooseFilesFrame chooseFilesFrame = new ChooseFilesFrame(controller);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        chooseFilesFrame.setLocation(dim.width / 2 - chooseFilesFrame.getSize().width / 2, dim.height / 2 - chooseFilesFrame.getSize().height / 2);
        chooseFilesFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemChooseFilesActionPerformed

    /**
     * Exit button
     *
     * @param evt
     */
    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemExitActionPerformed

    /**
     * Order selected values desc.
     *
     * @param evt
     */
    private void orderDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderDescActionPerformed
        JTable myTabla = getSelectedTable();
        errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            JPanel myPanel = (JPanel) (tabbedPane.getComponentAt(0));
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable genericTable = (JTable) viewport.getView();
            for (int i = 2; i < myTabla.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                if (bg1 < bg2) {
                    String[] datas = getGaussianToOrder();
                    double a = Double.parseDouble(genericTable.getValueAt(Integer.parseInt(datas[0]) - 1, i).toString());
                    double b = Double.parseDouble(genericTable.getValueAt(Integer.parseInt(datas[1]) - 1, i).toString());
                    double ax = a;
                    genericTable.setValueAt(String.valueOf(b), Integer.parseInt(datas[0]) - 1, i);
                    genericTable.setValueAt(String.valueOf(ax), Integer.parseInt(datas[1]) - 1, i);
                    reorderNormalTables(datas[0], datas[1], myTabla, (i - 2));
                    reorderSpecialTables(datas[0], datas[1], i);
                }
            }
        } else {
            errorText.setText("Please select 2 rows");
            errorText.setVisible(true);
        }
    }//GEN-LAST:event_orderDescActionPerformed

    /**
     * Order selected values asc.
     *
     * @param evt
     */
    private void orderAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderAscActionPerformed
        JTable myTabla = getSelectedTable();
        errorText.setText("");
        if (myTabla.getSelectedRows().length == 2) {
            JPanel myPanel = (JPanel) (tabbedPane.getComponentAt(0));
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable genericTable = (JTable) viewport.getView();
            String[] datas = getGaussianToOrder();
            for (int i = 2; i < myTabla.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[0], i).toString());
                double bg2 = Double.parseDouble(myTabla.getValueAt(myTabla.getSelectedRows()[1], i).toString());
                if (bg1 > bg2) {
                    double a = Double.parseDouble(genericTable.getValueAt(Integer.parseInt(datas[0]) - 1, i).toString());
                    double b = Double.parseDouble(genericTable.getValueAt(Integer.parseInt(datas[1]) - 1, i).toString());
                    double ax = a;
                    genericTable.setValueAt(String.valueOf(b), Integer.parseInt(datas[0]) - 1, i);
                    genericTable.setValueAt(String.valueOf(ax), Integer.parseInt(datas[1]) - 1, i);
                    reorderNormalTables(datas[0], datas[1], myTabla, (i - 2));
                    reorderSpecialTables(datas[0], datas[1], i);
                    genericTable.repaint();
                }
            }
        } else {
            errorText.setText("Please select 2 rows");
            errorText.setVisible(true);
        }
    }//GEN-LAST:event_orderAscActionPerformed

    /**
     * Method used to order the "normal" tables.
     *
     * @param gausian1
     * @param gausian2
     * @param order desc or asc
     */
    private void reorderNormalTables(String gausian1, String gausian2, JTable selectedTable, int index) {
        if (normalTables.get(index) != selectedTable) {
            DefaultTableModel model = (DefaultTableModel) normalTables.get(index).getModel();
            List<Integer> indexs1 = new ArrayList<>();
            for (int i = 0; i < normalTables.get(index).getRowCount(); i++) {
                if (String.valueOf(normalTables.get(index).getValueAt(i, 0)).equals(gausian1) || String.valueOf(normalTables.get(index).getValueAt(i, 0)).equals(gausian2)) {
                    indexs1.add(Integer.parseInt(String.valueOf(normalTables.get(index).getValueAt(i, 0))));
                }
            }
            for (int i = 2; i < model.getColumnCount(); i++) {
                double bg1 = Double.parseDouble(model.getValueAt(indexs1.get(0) - 1, i).toString());
                double bg2 = Double.parseDouble(model.getValueAt(indexs1.get(1) - 1, i).toString());
                double aux = bg1;
                model.setValueAt(String.valueOf(bg2), indexs1.get(0) - 1, i);
                model.setValueAt(String.valueOf(aux), indexs1.get(1) - 1, i);
            }
            normalTables.get(index).repaint();
        } else {

        }
    }

    /**
     * Method used to order the "special" tables.
     *
     * @param gausian1
     * @param gausian2
     * @param order desc or asc
     */
    private void reorderSpecialTables(String gausian1, String gausian2, int index) {
        for (JTable specialTable : specialTables) {
            List<TableElement> element1 = new ArrayList<>();
            List<TableElement> element2 = new ArrayList<>();
            for (int i = 0; i < specialTable.getRowCount(); i++) {
                String rowValue = specialTable.getValueAt(i, 0).toString();
                String columnValue = specialTable.getValueAt(i, 1).toString();
                if (rowValue.equals(gausian1) || columnValue.equals(gausian1)) {
                    element1.add(new TableElement(rowValue, columnValue, i));
                }
                if (rowValue.equals(gausian2) || columnValue.equals(gausian2)) {
                    element2.add(new TableElement(rowValue, columnValue, i));
                }
            }
            if (element1.size() > element2.size()) {
                reorderElements(specialTable, element2, element1, index);
            } else if (element2.size() > element1.size()) {
                reorderElements(specialTable, element1, element2, index);
            } else {
                reorderElements(specialTable, element1, element2, index);
            }

        }
    }

    /**
     * Method used to check wether a value has a pair or not
     *
     * @param specialTable myTable that is going to be ordered
     * @param element1 values used as keywords
     * @param element2 values used as keywords
     */
    public void reorderElements(JTable specialTable, List<TableElement> element1, List<TableElement> element2, int index) {
        List<TableElement> elements = new ArrayList<>();
        for (TableElement elementType1 : element1) {
            next:
            for (int j = 0; j < element2.size(); j++) {
                if (elementType1.column.equals(element2.get(j).column) || elementType1.column.equals(element2.get(j).row)
                        || elementType1.row.equals(element2.get(j).column) || elementType1.row.equals(element2.get(j).row)) {
                    double bg1 = Double.parseDouble(specialTable.getValueAt(elementType1.indexRow, index).toString());
                    double bg2 = Double.parseDouble(specialTable.getValueAt(element2.get(j).indexRow, index).toString());
                    double aux = bg1;
                    specialTable.setValueAt(String.valueOf(bg2), elementType1.indexRow, index);
                    specialTable.setValueAt(String.valueOf(aux), element2.get(j).indexRow, index);
                    element2.remove(element2.get(j));
                    break next;
                } else {
                    if (j == element2.size() - 1) {
                        elements.add(elementType1);
                    }
                }
            }
        }
        if (errorText.getText().length() == 0) {
            errorText.setVisible(false);
            errorText.setText("The following values will not be ordered: ");
            if (!elements.isEmpty()) {
                errorText.setVisible(true);
                for (TableElement element : elements) {
                    errorText.setText(errorText.getText() + " " + element.row + "," + element.column + "  ");
                }
            }
            if (!element2.isEmpty()) {
                for (TableElement element : element2) {
                    errorText.setVisible(true);
                    errorText.setText(errorText.getText() + " " + element.row + "," + element.column + "  ");
                }
            }
        }
    }

    /**
     * Remove item from list
     *
     * @param evt
     */
    private void buttonRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveItemActionPerformed

        listValues.repaint();
        listValues.revalidate();
        if (!listValues.isSelectionEmpty()) {
            coorValues.remove(listValues.getSelectedIndex());
            colAndRows.remove(listValues.getSelectedIndex());
            listValues.setListData(coorValues.toArray(new String[0]));
        }
        listValues.repaint();
        listValues.revalidate();
        this.pack();

    }//GEN-LAST:event_buttonRemoveItemActionPerformed

    /**
     * Text action Coordinations dialog box that adds elements to the jList.
     *
     * @param evt event when you press enter.
     */
    private void fieldColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldColumnActionPerformed
        addValueToList();
    }//GEN-LAST:event_fieldColumnActionPerformed

    /**
     * Text action Coordinations dialog box that adds elements to the jList.
     *
     * @param evt event when you press enter.
     */
    private void fieldRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldRowActionPerformed
        addValueToList();
    }//GEN-LAST:event_fieldRowActionPerformed

    /**
     * This button remove tab and myTable of the screen.
     *
     * @param evt Event when you press the button.
     */
    private void buttonRemoveTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveTableActionPerformed
        String name = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        JPanel genericPane = (JPanel) (tabbedPane.getComponentAt(0));
        JScrollPane scrollPaneGeneric = (JScrollPane) genericPane.getComponent(0);
        JViewport viewportGeneric = scrollPaneGeneric.getViewport();
        JTable genericTable = (JTable) viewportGeneric.getView();
        for (int i = 0; i < genericTable.getColumnCount(); i++) {
            if (name.equals(genericTable.getColumnName(i))) {
                removeColumn(i, genericTable);
            }
        }

        for (JTable specialTable : specialTables) {
            for (int i = 0; i < specialTable.getColumnCount(); i++) {
                if (name.equals(specialTable.getColumnName(i))) {
                    removeColumn(i, specialTable);
                }
            }
        }

        if (tabbedPane.isVisible()) {
            JPanel myPanel = (JPanel) (tabbedPane.getSelectedComponent());
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();

            if (usedTables.contains(myTable)) {
                usedTables.remove(myTable);
            }
            if (normalTables.contains(myTable)) {
                normalTables.remove(myTable);
            }
            if (specialTables.contains(myTable)) {
                specialTables.remove(myTable);
            }
            if (keywordsUsed.contains(name)) {
                keywordsUsed.remove(name);
            }

            tabbedPane.remove(tabbedPane.getSelectedComponent());

        }
    }//GEN-LAST:event_buttonRemoveTableActionPerformed

    /**
     * This method block the delete myTable button when the user is in the first
     * tab.
     *
     * @param evt Event when the user change the tab.
     */
    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        if (tabbedPane.getSelectedIndex() == 0) {
            buttonRemoveTable.setEnabled(false);
            buttonAverage.setEnabled(true);
            buttonAverage.setVisible(true);
        } else {
            buttonRemoveTable.setEnabled(true);
            buttonAverage.setEnabled(false);
        }
        if (!buttonRemoveTable.isEnabled()) {
            buttonRemoveTable.setToolTipText("\"Average\" table cannot be deleted.");
        } else {
            buttonRemoveTable.setToolTipText(null);

        }

    }//GEN-LAST:event_tabbedPaneStateChanged

    /**
     * This method open the dialog to change temperature.
     *
     * @param evt Event when the user press in the item.
     */
    private void itemChangeTemperatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChangeTemperatureActionPerformed
        dialogTemperature.pack();
        dialogTemperature.setVisible(true);
    }//GEN-LAST:event_itemChangeTemperatureActionPerformed

    /**
     * This method put the new value of the temperature.
     *
     * @param evt Event when press the button.
     */
    private void buttonOKTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKTempActionPerformed
        if (fieldTemperature.getText().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$")) {
            temperature = fieldTemperature.getText();
            dialogTemperature.dispose();
        } else {
            fieldTemperature.setBorder(new LineBorder(Color.red, 1));
        }
    }//GEN-LAST:event_buttonOKTempActionPerformed

    /**
     * This method open the dialog add more file.
     *
     * @param evt Event when the user press in the item.
     */
    private void itemAddMoreFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAddMoreFilesActionPerformed
        enableDragAndDrop();
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(getClass().getResourceAsStream("/ResourceFiles/add-file.png"));
        } catch (IOException ex) {
        }
        decorate(textAreaMoreFiles, bi);
        dialogAddMoreFiles.setVisible(true);
        dialogAddMoreFiles.pack();
        dialogAddMoreFiles.revalidate();
        dialogAddMoreFiles.repaint();
    }//GEN-LAST:event_itemAddMoreFilesActionPerformed

    /**
     * This method open a frame when you can choose files.
     *
     * @param evt Event when the user press in the button to choose files.
     */
    private void buttonChooseFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseFilesActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Files", "log"));
        fileChooser.setMultiSelectionEnabled(true);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedImage bi = null;
            decorate(textAreaMoreFiles, bi);
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                textAreaMoreFiles.append(file.getName() + "\n");
                filesData.add(file);
            }
            dialogAddMoreFiles.pack();
            dialogAddMoreFiles.revalidate();
            dialogAddMoreFiles.repaint();
        }
    }//GEN-LAST:event_buttonChooseFilesActionPerformed

    /**
     * This button close the diialog chooseMoreFiles and add new Files to lists.
     *
     * @param evt Event when the user press in the button.
     */
    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        if (textAreaMoreFiles.getText().length() > 0) {
            for (File listFile : filesData) {
                if (!this.files.contains(listFile.getName())) {
                    this.files.add(listFile.getName());
                }
            }
            dialogAddMoreFiles.setVisible(false);
        }
    }//GEN-LAST:event_buttonNextActionPerformed

    /**
     * This method put toolTipeText to the texField of the main frame.
     *
     * @param evt Event when the user choose a option to the combo.
     */
    private void comboOptionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOptionsItemStateChanged
        switch (String.valueOf(comboOptions.getSelectedItem())) {
            case "Starts with":
                break;
            case "Ends with":
                fieldKeyword.setToolTipText(null);
                break;
            case "Contains":
                fieldKeyword.setToolTipText(null);
                break;
            case "Range starts with":
                fieldKeyword.setToolTipText("Two values separate by \"-\". I.e: 01-09");
                break;

            default:
                break;
        }
    }//GEN-LAST:event_comboOptionsItemStateChanged

    private void itemSCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSCFActionPerformed
        dialogSCF.setVisible(true);
        dialogSCF.revalidate();
        dialogSCF.pack();


    }//GEN-LAST:event_itemSCFActionPerformed

    private void buttonAverageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAverageActionPerformed
        JPanel myPanel = (JPanel) (tabbedPane.getSelectedComponent());
        if (myPanel.getComponentCount() == 1) {
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();
            if (myTable.getSelectedRowCount() == 3) {
                averageTableNotExist(myTable);

            } else {
                errorText.setText("Sorry, but you need check 3 rows");
            }
        } else {
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(1);
            JViewport viewport = scrollPane.getViewport();
            JTable myTable = (JTable) viewport.getView();
            if (myTable.getSelectedRowCount() == 3) {
                averageTableExist(myTable);
            } else {
                errorText.setText("Sorry, but you need check 3 rows in second table.");
            }
        }
    }//GEN-LAST:event_buttonAverageActionPerformed

    private void averageTableNotExist(JTable myTable) {
        List<List<Object>> values = new ArrayList<>();
        int firstIndex = myTable.getSelectedRows()[0];
        int secondIndex = myTable.getSelectedRows()[1];
        int thirdIndex = myTable.getSelectedRows()[2];
        for (int i = 0; i < myTable.getRowCount(); i++) {
            List<Object> localRows = new ArrayList<>();
            if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                if (i == firstIndex) {
                    localRows.add(myTable.getValueAt(firstIndex, 0).toString() + "-"
                            + myTable.getValueAt(secondIndex, 0).toString() + "-"
                            + myTable.getValueAt(thirdIndex, 0).toString());
                    localRows.add(myTable.getValueAt(i, 1).toString());
                }
            } else {
                localRows.add(myTable.getValueAt(i, 0).toString());
                localRows.add(myTable.getValueAt(i, 1).toString());
            }
            for (int j = 2; j < myTable.getColumnCount(); j++) {
                if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                    if (i == firstIndex) {
                        Double averageValue = (Double.parseDouble(myTable.getValueAt(firstIndex, j).toString())
                                + Double.parseDouble(myTable.getValueAt(secondIndex, j).toString())
                                + Double.parseDouble(myTable.getValueAt(thirdIndex, j).toString())) / 3;
                        localRows.add(averageValue);
                    }
                } else {
                    localRows.add(myTable.getValueAt(i, j).toString());
                }
            }
            if (i == firstIndex || i == secondIndex || i == thirdIndex) {
                if (i == firstIndex) {
                    values.add(localRows);
                }
            } else {
                values.add(localRows);
            }

        }

        String[] headerValues = new String[myTable.getColumnCount()];
        for (int i = 0; i < myTable.getColumnCount(); i++) {
            headerValues[i] = myTable.getColumnName(i);
        }

        JPanel panel = (JPanel) tabbedPane.getComponentAt(0);
        JTable averageTable = new JTable();
        boolean[] canEditTry = new boolean[headerValues.length];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelAverage = new DefaultTableModel(headerValues, 0) {
            boolean[] canEdit = canEditTry;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        averageTable.setAutoCreateRowSorter(true);
        for (List<Object> value : values) {
            Object[] datas = value.toArray();
            modelAverage.addRow(datas);
        }
        averageTable.setModel(modelAverage);
        averageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Center columns
        for (int i = 0; i < averageTable.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            averageTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableGeneric.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        averageTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollpane = new JScrollPane(averageTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollpane);
        panel.revalidate();
        panel.repaint();
        TableColumn column = null;
        for (int i = 0; i < averageTable.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = averageTable.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = averageTable.getColumnModel().getColumn(i);
                column.setMinWidth(300);
            }
        }
        multiTable = true;
    }

    private void averageTableExist(JTable myTable) {
        int firstIndex = myTable.getSelectedRows()[0];
        int secondIndex = myTable.getSelectedRows()[1];
        int thirdIndex = myTable.getSelectedRows()[2];
        System.out.println(firstIndex);

        System.out.println(firstIndex);
        String gaussians = myTable.getValueAt(firstIndex, 0).toString() + "-"
                + myTable.getValueAt(secondIndex, 0).toString() + "-"
                + myTable.getValueAt(thirdIndex, 0).toString();
        myTable.setValueAt(gaussians, firstIndex, 0);
        for (int i = 2; i < myTable.getColumnCount(); i++) {
            Double averageValue = (Double.parseDouble(myTable.getValueAt(firstIndex, i).toString())
                    + Double.parseDouble(myTable.getValueAt(secondIndex, i).toString())
                    + Double.parseDouble(myTable.getValueAt(thirdIndex, i).toString())) / 3;
            myTable.setValueAt(averageValue, firstIndex, i);
        }
        DefaultTableModel df = (DefaultTableModel) myTable.getModel();
        df.removeRow(thirdIndex);
        df.removeRow(secondIndex);
        multiTable = true;
    }

    private void SCFTable(String keyword) {
        tabPaneSCF.add(keyword, initSCFTable());
    }

    private JPanel initSCFTable() {
        ArrayList<String> nameFiles = new ArrayList<>();
        ArrayList<Object> contribution = new ArrayList<>();
        ArrayList<Object> SCF = new ArrayList<>();
        nameFiles.add("Values");
        contribution.add("Contribution");
        SCF.add("SCF");
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(getLocale());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
        df.setRoundingMode(RoundingMode.CEILING);

        for (FileData fileData : controller.getFileData(usedFiles, Double.valueOf(temperature))) {
            nameFiles.add(fileData.getFileName());
            contribution.add(df.format(fileData.getContribution()));
            SCF.add(df.format(fileData.getEnergyValue()));
        }
        Object[][] data = new Object[][]{
            contribution.toArray(new Object[0]),
            SCF.toArray(new Object[0])
        };

        String[] headers = nameFiles.toArray(new String[0]);
        JTable tableSCF = new JTable(data, headers);

        boolean[] canEditTry = new boolean[SCF.size() + 1];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }
        DefaultTableModel newModel = new DefaultTableModel(
                data, headers
        ) {
            boolean[] canEdit = canEditTry;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        }
    ;
        tableSCF.setModel(newModel);

        for (int i = 0; i < tableSCF.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableSCF.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableSCF.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableSCF.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        TableColumn column = null;
        for (int i = 0; i < tableSCF.getColumnCount(); i++) {
            if (i == 0) {
                column = tableSCF.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = tableSCF.getColumnModel().getColumn(i);
                column.setMinWidth(250);
            }
        }

        tableSCF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollpaneGeneric = new JScrollPane(tableSCF, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(1000, 100));
        panel.add(scrollpaneGeneric);
        return panel;
    }

    /**
     *
     * @return selected myTable
     */
    private JTable getSelectedTable() {
        JPanel myPanel = (JPanel) (tabbedPane.getSelectedComponent());
        JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
        JViewport viewport = scrollPane.getViewport();
        JTable myTable = (JTable) viewport.getView();
        return myTable;
    }

    /**
     * Used to order values
     *
     * @return gaussian values ordered
     */
    private String[] getGaussianToOrder() {
        JTable myTable = getSelectedTable();
        DefaultTableModel model = (DefaultTableModel) myTable.getModel();
        int[] rows = myTable.getSelectedRows();
        String[] firstRow = String.valueOf(model.getDataVector().elementAt(rows[0])).replace("[", "").split(",");
        String[] secondRow = String.valueOf(model.getDataVector().elementAt(rows[1])).replace("[", "").split(",");
        String firstRow1 = firstRow[0].replace(" ", "");
        String firstRow2 = firstRow[1].replace(" ", "");
        String secondRow1 = secondRow[0].replace(" ", "");
        String secondRow2 = secondRow[1].replace(" ", "");
        String gausian1;
        String gausian2;
        if (firstRow1.equals(secondRow1) || firstRow1.equals(secondRow2)) {
            gausian1 = firstRow2;
        } else {
            gausian1 = firstRow1;
        }
        if (secondRow1.equals(firstRow1) || secondRow1.equals(firstRow2)) {
            gausian2 = secondRow2;
        } else {
            gausian2 = secondRow1;
        }
        String[] datas = new String[]{gausian1, gausian2};
        return datas;
    }

    /**
     * Creates the "Average" myTable with all the info from the other tables.
     *
     * @param usedFiles
     */
    private void genericTable(List<File> usedFiles) {
        Molecule molecule = this.controller.getMolecule(usedFiles, fieldKeyword.getText(), Double.parseDouble(temperature)
        );
        String[] values = new String[keywordsUsed.size() + 2];
        values[0] = "Gaussian";
        values[1] = "Atom";
        for (int i = 2; i <= keywordsUsed.size() + 1; i++) {
            values[i] = keywordsUsed.get(i - 2);
        }

        if (tableGeneric == null) {
            initGenericTable(values);
            addElementsToGeneric(molecule);
            JScrollPane scrollpaneGeneric = new JScrollPane(tableGeneric, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panelGeneric.add(scrollpaneGeneric);
        } else {
            panelGeneric.removeAll();
            initGenericTable(values);
            addElementsToGeneric(molecule);
            JScrollPane scrollpaneGeneric = new JScrollPane(tableGeneric, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panelGeneric.add(scrollpaneGeneric);
        }
        TableColumn column = null;
        for (int i = 0; i < tableGeneric.getColumnCount(); i++) {
            if (i == 0 || i == 1) {
                column = tableGeneric.getColumnModel().getColumn(i);
                column.setMinWidth(100);
            } else {
                column = tableGeneric.getColumnModel().getColumn(i);
                column.setMinWidth(200);
            }
        }
        tableGeneric.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    /**
     * Initializes the Average myTable.
     *
     * @param values are the "keywords" used.
     */
    private void initGenericTable(String[] values) {
        tableGeneric = new JTable();

        boolean[] canEditTry = new boolean[keywordsUsed.size() + 2];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }

        DefaultTableModel modelGeneric = new DefaultTableModel(values, 0) {
            boolean[] canEdit = canEditTry;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        tableGeneric.setAutoCreateRowSorter(true);
        tableGeneric.setModel(modelGeneric);
        //Center columns
        for (int i = 0; i < tableGeneric.getColumnCount(); i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableGeneric.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableGeneric.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        tableGeneric.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    }

    /**
     * Add all the elements to the Average myTable.
     *
     * @param molecule contains all the necessary info about the molecules.
     */
    private void addElementsToGeneric(Molecule molecule) {
        DefaultTableModel model = (DefaultTableModel) tableGeneric.getModel();
        List<List<Object>> auxList = new ArrayList<>(rows);
        rows.clear();
        for (int i = 0; i < molecule.getResult().size(); i++) {
            if (keywordsUsed.size() == 1) {
                List<Object> values = new ArrayList<>();
                values.add(molecule.getResult().get(i).getGaussian());
                values.add(molecule.getResult().get(i).getAtom());
                double value = molecule.getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                values.add(String.valueOf(df.format(value)));
                rows.add(values);
            } else {
                List<Object> values = new ArrayList<>(auxList.get(i));
                double value = molecule.getResult().get(i).getValue();
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(getLocale());
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("#.####", otherSymbols);
                df.setRoundingMode(RoundingMode.CEILING);
                values.add(String.valueOf(df.format(value)));
                rows.add(values);
            }
        }

        rows.forEach((row) -> {
            model.addRow(row.toArray());
        });

    }

    /**
     * Method used to add new files.
     */
    private void actionButtonAdd(String fieldText) {
        try {
            errorText.setForeground(Color.red);
            if (!keywordsUsed.contains(fieldText)) {
                keywordsUsed.add(fieldText);
                if (!usedFiles.isEmpty()) {
                    if (!searchAdded) {
                        searchTab();
                        searchAdded = true;
                    }

                    errorText.setText("");
                    JTable table = addRowsToTable(initTablesDifferentiators());
                    if (table.getRowCount() != 0) {
                        JPanel panel = new JPanel();
                        if (usedTables.isEmpty()) {
                            itemSearchValue.setEnabled(true);
                            itemExport.setEnabled(false);
                            itemReset.setEnabled(true);
                            buttonValue.setVisible(true);
                            buttonExportCSV.setVisible(true);
                            buttonRemoveTable.setVisible(true);
                            buttonDelete.setVisible(true);
                            tabbedPane.setVisible(true);
                            itemExport.setEnabled(true);
                            itemSCF.setEnabled(true);
                            buttonAverage.setVisible(true);
                    }
                    panel.setLayout(new GridLayout(0, 1));
                    JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    TableColumn column = null;
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        if (i == 0 || i == 1) {
                            column = table.getColumnModel().getColumn(i);
                            column.setMinWidth(100);
                        } else {
                            column = table.getColumnModel().getColumn(i);
                            column.setMinWidth(300);
                        }
                        panel.setLayout(new GridLayout(0, 1));
                        JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        TableColumn column = null;
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            if (i == 0 || i == 1) {
                                column = table.getColumnModel().getColumn(i);
                                column.setMinWidth(100);
                            } else {
                                column = table.getColumnModel().getColumn(i);
                                column.setMinWidth(300);
                            }
                        }
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        panel.add(scrollpane);
                        tabbedPane.addTab(fieldText, panel);
                        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                        genericTable(usedFiles);
                        SCFTable();
                        if (usedTables.isEmpty()) {
                            usedTables.add(tableGeneric);
                        }
                        normalTables.add(table);
                        usedTables.add(table);
                        revalidate();
                        //pack();
                        itemChangeTemperature.setEnabled(false);
                        itemChangeTemperature.setToolTipText("To change the temperature, import the files again.");

                    } else {
                        errorText.setText("Couldn't find any file with the provided keyword.");
                    }
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    panel.add(scrollpane);
                    tabbedPane.addTab(fieldText, panel);
                    tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                    genericTable(usedFiles);
                    SCFTable(fieldText);
                    if (usedTables.isEmpty()) {
                        usedTables.add(tableGeneric);
                    }
                    normalTables.add(table);
                    usedTables.add(table);
                    revalidate();
                    pack();
                    itemChangeTemperature.setEnabled(false);
                    itemChangeTemperature.setToolTipText("To change the temperature, import the files again.");

                } else {
                    errorText.setText("Couldn't find any file with the provided keyword.");
                }

            } else {
                errorText.setText("Some files were not imported.");
            }
        } catch (Exception e) {
            errorText.setText("Some files were not imported.");
        }
        if (this.getSize() != new Dimension(1080, 480)) {
            this.setSize(1080, 480);
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
        boolean[] canEditTry = new boolean[names.length];
        for (int i = 0; i < canEditTry.length; i++) {
            canEditTry[i] = false;
        }
        DefaultTableModel newModel = new DefaultTableModel(
                cells, names
        ) {
            boolean[] canEdit = canEditTry;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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

    public void searchTab() {
        jMenuBar1.add(Box.createHorizontalGlue());
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
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        if (tabbedPane.getTitleAt(i).equals(textField.getText())) {
                            tabbedPane.setSelectedIndex(i);
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        jMenuBar1.add(textField);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAddValue;
    private javax.swing.JButton buttonAverage;
    private javax.swing.JButton buttonChooseFiles;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonExportCSV;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonOKTemp;
    private javax.swing.JButton buttonRemoveItem;
    private javax.swing.JButton buttonRemoveTable;
    private javax.swing.JButton buttonValue;
    private javax.swing.JButton buttonValues;
    private javax.swing.JComboBox<String> comboOptions;
    private javax.swing.JDialog dialogAddMoreFiles;
    private javax.swing.JDialog dialogCoordinates;
    private javax.swing.JDialog dialogNombre;
    private javax.swing.JDialog dialogSCF;
    private javax.swing.JDialog dialogTemperature;
    private javax.swing.JLabel errorDialogCoor;
    private javax.swing.JLabel errorText;
    private javax.swing.JTextField fieldColumn;
    private javax.swing.JTextField fieldKeyword;
    private javax.swing.JTextField fieldNameValues;
    private javax.swing.JTextField fieldRow;
    private javax.swing.JTextField fieldTemperature;
    private javax.swing.JButton finishButton;
    private javax.swing.JMenuItem itemAddMoreFiles;
    private javax.swing.JMenuItem itemChangeTemperature;
    private javax.swing.JMenuItem itemChooseFiles;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemExport;
    private javax.swing.JMenuItem itemReset;
    private javax.swing.JMenuItem itemSCF;
    private javax.swing.JMenuItem itemSearchValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listValues;
    private javax.swing.JButton orderAsc;
    private javax.swing.JButton orderDesc;
    private javax.swing.JTabbedPane tabPaneSCF;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextArea textAreaMoreFiles;
    // End of variables declaration//GEN-END:variables
}
