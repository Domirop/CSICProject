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
        fd.keywordsUsed.clear();
        fd.names.clear();
        fd.specialTables.clear();
        fd.rows.clear();
        fd.coorValues.clear();
        fd.colAndRows.clear();
        JTabbedPane pane = fd.tabbedPane;
        fd.tabPaneSCF.removeAll();
        fd.tableGeneric = null;
        
        for (int j = 0; j < fd.allFiles.size(); j++) {
            JPanel myPanel = (JPanel) pane.getComponentAt(j + 1);
            JScrollPane scrollPane = (JScrollPane) myPanel.getComponent(0);
            JViewport viewport = scrollPane.getViewport();
            JTable table = (JTable) viewport.getView();
            fd.usedFiles.clear();
            fd.names.clear();  
            if (fd.normalTables.contains(table)) {
                for (int k = 0; k < fd.allFiles.get(j).size(); k++) {
                    String name = fd.allFiles.get(j).get(k);
                    fd.filesData.stream().filter((item) -> (item.getName().contains(name))).forEach((item) -> {
                        fd.usedFiles.add(item);
                        fd.names.add(name);

                    });
                }
                fd.normalTables.remove(table);
                ate.actionButtonAdd(pane.getTitleAt(j + 1));
                fd.tableGeneric.revalidate();
                fd.tableGeneric.repaint();
            }
        }

        String name = pane.getTitleAt(pane.getTabCount() - 1);
        for (int i = 0; i < pane.getTabCount(); i++) {
            if (i != 0) {
                if (pane.getTitleAt(i).equals(name)) {
                    pane.removeTabAt(i);
                    break;
                } else {
                    pane.removeTabAt(i);
                    i = 0;
                }

            }
        }

    }
}
