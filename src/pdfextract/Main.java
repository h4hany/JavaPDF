/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import com.itextpdf.text.DocumentException;
import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author hany
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cards = new javax.swing.JPanel();
        loginPanal = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        userNameTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanalFirst = new javax.swing.JPanel();
        pathText = new javax.swing.JTextField();
        jPanalSnd = new javax.swing.JPanel();
        bntSave = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();
        saveInDB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cards.setLayout(new java.awt.CardLayout());

        loginPanal.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userNameTxt)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        loginPanal.add(jPanel4);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        loginPanal.add(jPanel3);

        cards.add(loginPanal, "card2");

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        pathText.setText("                                     Your File Path");
        pathText.setEnabled(false);

        javax.swing.GroupLayout jPanalFirstLayout = new javax.swing.GroupLayout(jPanalFirst);
        jPanalFirst.setLayout(jPanalFirstLayout);
        jPanalFirstLayout.setHorizontalGroup(
            jPanalFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanalFirstLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanalFirstLayout.setVerticalGroup(
            jPanalFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanalFirstLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel1.add(jPanalFirst);

        jPanalSnd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bntSave.setText("Save");
        bntSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSaveActionPerformed(evt);
            }
        });
        jPanalSnd.add(bntSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 135, 40));

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        jPanalSnd.add(btnBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 147, 40));

        saveInDB.setText("Submit To Database");
        saveInDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveInDBActionPerformed(evt);
            }
        });
        jPanalSnd.add(saveInDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, 46));

        jPanel1.add(jPanalSnd);

        jPanel5.add(jPanel1, java.awt.BorderLayout.CENTER);

        cards.add(jPanel5, "card3");

        getContentPane().add(cards, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
String userName, password;
    ControlPDF contPdf = new ControlPDF();
    String browsePath = "";
    File savePath;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            userName = userNameTxt.getText();
            password = passwordTxt.getText();
           

            if (contPdf.authFromZend("http://zend.test.com/user/java", userName, password).equals("AUTH")) {

                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card3");

            } else {

                JOptionPane.showMessageDialog(this,
                        "User Name Or Password is Wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bntSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSaveActionPerformed
        // TODO add your handling code here:
        if (browsePath == null || browsePath.isEmpty() || browsePath.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please Choose the file first .",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                JFileChooser openFile = new JFileChooser();
                openFile.showOpenDialog(null);
                savePath = openFile.getSelectedFile();

                if (contPdf.parsePdfToText(browsePath, savePath)) {

                    JOptionPane.showMessageDialog(this,
                            "Your file is ready to use",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Somthing Went Wrong please check the support",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                Logger.getLogger(Extract.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(Extract.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bntSaveActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser openFile = new JFileChooser();
        openFile.showOpenDialog(null);
        browsePath = openFile.getSelectedFile().getAbsolutePath();
        pathText.setText(browsePath);
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void saveInDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveInDBActionPerformed
        // TODO add your handling code here:
        List<String> arrayOftext = new ArrayList<String>();
        String[] variables;
        Map<String, String> map = new HashMap<String, String>();

        // arrayOftext = up.parsePdf("vv.pdf");
        if (browsePath == null || browsePath.isEmpty() || browsePath.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please Choose the file first .",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            try {

                arrayOftext = contPdf.parsePdf(browsePath);

                if (arrayOftext.size() > 0) {
                    // variables = contPdf.fileInfoSe(arrayOftext.get(0));

                    //get the result of data in form of key and value
                    map = contPdf.fileInfoSeMap(arrayOftext.get(0));

                    //send result to server
                    contPdf.sendDataToServerMap("http://zend.test.com/user/new", map);

                    // String[] colName;
                    // String[] valueName;
                    // contPdf.sendDataToServer(serverUrl,colName,valueName);
                    JOptionPane.showMessageDialog(this,
                            "Saved in db",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Somthing Went Wrong please check the support",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                Logger.getLogger(Extract.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveInDBActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSave;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JPanel cards;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanalFirst;
    private javax.swing.JPanel jPanalSnd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel loginPanal;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField pathText;
    private javax.swing.JButton saveInDB;
    private javax.swing.JTextField userNameTxt;
    // End of variables declaration//GEN-END:variables
}