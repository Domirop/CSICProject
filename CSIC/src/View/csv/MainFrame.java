/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.csv;

<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
=======
import java.util.List;
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
import java.util.List;
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table

/**
 *
 * @author domit
 */
public class MainFrame extends javax.swing.JFrame {
    
    List<String[]> datas;
<<<<<<< HEAD
<<<<<<< HEAD
    FilterOptions fo = new FilterOptions(this);

=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }
<<<<<<< HEAD
<<<<<<< HEAD

=======
    
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
    
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ComboColumn = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textMinValue = new javax.swing.JTextField();
        textMaxValue = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonFilter = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
<<<<<<< HEAD
<<<<<<< HEAD
        errorText = new javax.swing.JLabel();
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Column:");

        jLabel2.setText("Min value:");

        jLabel3.setText("Max Value:");

<<<<<<< HEAD
<<<<<<< HEAD
        textMinValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMinValueActionPerformed(evt);
            }
        });

        textMaxValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMaxValueActionPerformed(evt);
            }
        });

=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
<<<<<<< HEAD
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
=======
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
        );

        buttonFilter.setText("Filter");
        buttonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilterActionPerformed(evt);
            }
        });

        jButton1.setText("Order desc");

<<<<<<< HEAD
<<<<<<< HEAD
        jButton2.setText("Order asc");

        errorText.setForeground(new java.awt.Color(255, 0, 0));
=======
        jButton1.setText("Order desc");

        jButton2.setText("Order asc");
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
        jButton1.setText("Order desc");

        jButton2.setText("Order asc");
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(ComboColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
<<<<<<< HEAD
<<<<<<< HEAD
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
                        .addGap(0, 93, Short.MAX_VALUE)))
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
                        .addGap(0, 93, Short.MAX_VALUE)))
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFilter)
                    .addComponent(ComboColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
<<<<<<< HEAD
<<<<<<< HEAD
                .addGap(18, 18, 18)
                .addComponent(errorText)
                .addGap(18, 18, 18)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD
<<<<<<< HEAD
    private void buttonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFilterActionPerformed
        removeElements();
    }//GEN-LAST:event_buttonFilterActionPerformed

    private void textMaxValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMaxValueActionPerformed
        removeElements();
    }//GEN-LAST:event_textMaxValueActionPerformed

    private void textMinValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMinValueActionPerformed
        removeElements();
    }//GEN-LAST:event_textMinValueActionPerformed

    private boolean controlText() {
        if (!textMaxValue.getText().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$") || textMaxValue.getText().length() == 0) {
            textMaxValue.setBorder(BorderFactory.createLineBorder(Color.red));
            textMaxValue.setToolTipText("The format is (number).(2 number)");
            return false;
        } else {
            textMaxValue.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            textMaxValue.setToolTipText("The format is (number).(2 number)");
        }
        if (!textMinValue.getText().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$") || textMinValue.getText().length() == 0) {
            textMinValue.setBorder(BorderFactory.createLineBorder(Color.red));
            textMinValue.setToolTipText("The format is (number).(2 number)");
            return false;
        } else {
            textMinValue.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        return true;
    }

    private void removeElements() {
        if (controlText()) {
            double maxValue = Double.parseDouble(textMaxValue.getText());
            double minValue = Double.parseDouble(textMinValue.getText());
            int column = ComboColumn.getSelectedIndex();
            if (minValue <= maxValue) {
                fo.removeElements(minValue, maxValue, column);
            } else {
                errorText.setText("Min value can't be less than max value");
            }
        }
    }

=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
    
    
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
    
    
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> ComboColumn;
    private javax.swing.JButton buttonFilter;
<<<<<<< HEAD
<<<<<<< HEAD
    public javax.swing.JLabel errorText;
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
=======
>>>>>>> parent of f4329d2... Merge pull request #45 from Domirop/new-values-table
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table;
    public javax.swing.JTextField textMaxValue;
    public javax.swing.JTextField textMinValue;
    // End of variables declaration//GEN-END:variables
}
