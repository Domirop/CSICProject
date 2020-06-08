/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.log;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 *
 * @author daviddiaz
 */
public class ElementsDragAndDrop {
    FrameDifferentiator fd;

    public ElementsDragAndDrop(FrameDifferentiator fd) {
        this.fd = fd;
    }
    
    /**
     * This method use to map the element you copy to elements of the JList,
     *
     * @param list JList when element you copy will be paste.
     */
    public void setMappings(JList list) {
        ActionMap map = list.getActionMap();
        map.put(TransferHandler.getCutAction().getValue(Action.NAME),
                TransferHandler.getCutAction());
        map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
                TransferHandler.getCopyAction());
        map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
                TransferHandler.getPasteAction());
    }
    
    /**
     * This method is used for ability the functions drop to add new files.
     */
    public void enableDragAndDrop() {
        DropTarget target = new DropTarget(fd.textAreaMoreFiles, new DropTargetListener() {
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
                fd.decorate.decorate(fd.textAreaMoreFiles, bi);
                try {
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    List list = (java.util.List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    list.forEach(v -> {
                        File file = (File) v;
                        if (fd.filesTypes.contains(file.getName().split("\\.")[1])) {
                            fd.textAreaMoreFiles.append(file.getName() + "\n");
                            fd.filesData.add(file);
                        }
                    });
                    fd.dialogAddMoreFiles.pack();
                    fd.dialogAddMoreFiles.revalidate();
                    fd.dialogAddMoreFiles.repaint();
                } catch (Exception ex) {
                }
            }
        });
    }
        /**
     * This method used to recharge the List of the dialogCoordinates when you
     * copy an paste elements in JList.
     *
     * @param row Reference of the coordinates.
     * @param column Reference of the coordinates.
     */
    public void addElementsToRows(int row, int column) {
        fd.coorValues.add(row + "," + column);
        fd.colAndRows.add(row + "," + column);
        fd.errorDialogCoor.setText("");
    }

    /**
     * This method used to control the textError of the dialogCoordinates when
     * you copy an paste elements in JList.
     *
     * @param string The message which you want put
     */
    public void setErrorDialogCoor(String string) {
        fd.errorDialogCoor.setText(string);
    }
}
