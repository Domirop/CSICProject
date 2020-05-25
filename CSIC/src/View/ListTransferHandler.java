/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.TransferHandler;

/**
 *
 * @author domit
 */
class ListTransferHandler extends TransferHandler {

    FrameDifferentiator fr;

    public ListTransferHandler(FrameDifferentiator fr) {
        this.fr = fr;
    }

    /**
     * Perform the actual data import.
     */
    public boolean importData(TransferHandler.TransferSupport info) {
        String data = null;
        if (!canImport(info)) {
            return false;
        }
        JList list = (JList) info.getComponent();
        ListModel listModel = list.getModel();
        DefaultListModel model = (DefaultListModel) listModel;
        String[] allData = new String[]{};
        try {
            data = (String) info.getTransferable().getTransferData(
                    DataFlavor.stringFlavor);
            allData = data.split("\\s+");
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("importData: unsupported data flavor");
            return false;
        } catch (IOException ioe) {
            System.out.println("importData: I/O exception");
            return false;
        }
        for (int i = 0; i < allData.length; i++) {
            if (allData[i].matches("^(-?0[,]\\d+)$|^(-?[1-9]+\\d*([,]\\d+)?)$|^0$")) {
                String[] values = allData[i].split(",");
                int row = Integer.parseInt(values[0]);
                int column = Integer.parseInt(values[1]);
                if (row <= fr.tableGeneric.getRowCount() || column <= fr.tableGeneric.getRowCount()) {
                    if (row >= column) {
                        if (info.isDrop()) {
                            JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
                            int index = i;
                            if (dl.isInsert()) {
                                model.add(index, allData[i]);
                            } else {
                                model.set(index, allData[i]);
                            }
                        } else {
                            int index = list.getSelectedIndex();
                            if (index >= 0) {
                                model.add(list.getSelectedIndex() + 1, allData[i]);
                            } else {
                                model.addElement(allData[i]);
                            }
                        }
                    } else {
                        fr.setErrorDialogCoor("<html><body>The value of the row must be greater than the column value.</body></html>");
                        return true;
                    }
                } else {
                    fr.setErrorDialogCoor("<html><body>The value of the column and row must be greater than the number gaussians.</body></html>");
                    return true;
                }
            } else {
                fr.setErrorDialogCoor("<html><body>Incorrect values. Correct format is (number,number).)</body></html>");
                return true;
            }
        }
        return true;
    }

    /**
     * Bundle up the data for export.
     */
    protected Transferable createTransferable(JComponent c) {
        JList list = (JList) c;
        int index = list.getSelectedIndex();
        String value = (String) list.getSelectedValue();
        return new StringSelection(value);
    }

    /**
     * The list handles both copy and move actions.
     */
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    /**
     * When the export is complete, remove the old list entry if the action was
     * a move.
     */
    protected void exportDone(JComponent c, Transferable data, int action) {
        if (action != MOVE) {
            return;
        }
        JList list = (JList) c;
        DefaultListModel model = (DefaultListModel) list.getModel();
        int index = list.getSelectedIndex();
        model.remove(index);
    }

    /**
     * We only support importing strings.
     */
    public boolean canImport(TransferHandler.TransferSupport support) {
        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }
}
