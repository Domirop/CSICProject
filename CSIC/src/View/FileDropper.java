package View;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

/**
 *
 * @author domit
 */
public class FileDropper extends DropTargetAdapter {

    public interface FileDropListener {
        public void onDropFile(File file);
    }

    private FileDropListener listener;

    public FileDropper(FileDropListener listener) {
        super();
        this.listener = listener;
    }

    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        Transferable transferable = event.getTransferable();
        DataFlavor[] transferFlavors = transferable.getTransferDataFlavors();
        for (DataFlavor flavor : transferFlavors) {
            try {
                if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                    @SuppressWarnings("unchecked")
                    List<File> fileList = (List<File>) transferable.getTransferData(flavor);
                    for (File file : fileList) {
                        listener.onDropFile(file);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            event.dropComplete(true);
        }
    }
}


