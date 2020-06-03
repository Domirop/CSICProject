/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;

/**
 *
 * @author domit
 */
public class PredefineValues {

    FrameDifferentiator fd;
    AddTablesEvent ate;

    public PredefineValues(FrameDifferentiator fd, AddTablesEvent ate) {
        this.fd = fd;
        this.ate = ate;
    }

    public void changeValues() {
        JTabbedPane pane = fd.tabbedPane;
        fd.tabbedPane.removeAll();
        fd.tabPaneSCF.removeAll();
        for (int i = 1; i < pane.getComponentCount(); i++) {
            JPanel myPanel = (JPanel) pane.getComponentAt(i);
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable table = (JTable) viewport.getView();
            fd.usedFiles.clear();
            if(fd.normalTables.contains(table)){
                for (int j = 2; j < table.getColumnCount(); j++) {
                    String name = table.getColumnName(j);
                    fd.filesData.stream().filter((item) -> (item.getName().contains(name))).forEachOrdered((item) -> {
                        fd.usedFiles.add(item);
                    });
                }
                ate.actionButtonAdd(pane.getTitleAt(i));
            }
        }
    }
}
