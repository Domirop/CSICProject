/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

/**
 *
 * @author domit
 */
public class Events {

    FrameDifferentiator fd;

    public Events(FrameDifferentiator fd) {
        this.fd = fd;
    }

    /*public void initEvents() {
        fd.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fd.names.clear();
                fd.usedFiles.clear();
                if (fd.fieldKeyword.getText().length() > 0) {
                    getUsedFiles(fd.fieldKeyword.getText(), fd.comboOptions.getSelectedItem().toString(), true);
                }
            }
        });
    }*/

}
