/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unodemo;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PickColorFrame extends javax.swing.JFrame {

   private UNOCard.Color wildColor = null;
   Boolean allow = false;
   PopUp popUp;
   
    public PickColorFrame() {
        
        initComponents();
    }
    public PickColorFrame(PopUp pop)
    {
        initComponents();
        popUp = pop;
    }
    public UNOCard.Color choseColor(UNOCard card)
    {
        if(card.getColor() == UNOCard.Color.Wild)
        {
         this.setVisible(true);
         this.setResizable(false);
         this.setBounds(100,150,600,700);
        }
        return card.getColor();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        redButton = new javax.swing.JButton();
        greenButton = new javax.swing.JButton();
        yellowButton = new javax.swing.JButton();
        blueButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(250, 70, 0));

        jPanel1.setBackground(new java.awt.Color(240, 70, 0));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Pick Color of Wild Card");

        redButton.setBackground(new java.awt.Color(255, 0, 0));
        redButton.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        redButton.setForeground(new java.awt.Color(255, 0, 0));
        redButton.setText("RED");
        redButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redButtonActionPerformed(evt);
            }
        });

        greenButton.setBackground(new java.awt.Color(0, 200, 0));
        greenButton.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        greenButton.setForeground(new java.awt.Color(0, 200, 0));
        greenButton.setText("GREEN");
        greenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenButtonActionPerformed(evt);
            }
        });

        yellowButton.setBackground(new java.awt.Color(255, 255, 0));
        yellowButton.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        yellowButton.setForeground(new java.awt.Color(255, 255, 0));
        yellowButton.setText("YELLOW");
        yellowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yellowButtonActionPerformed(evt);
            }
        });

        blueButton.setBackground(new java.awt.Color(0, 0, 255));
        blueButton.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        blueButton.setForeground(new java.awt.Color(0, 0, 255));
        blueButton.setText("BLUE");
        blueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yellowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(greenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yellowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void redButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redButtonActionPerformed
       wildColor = UNOCard.Color.Red;
       JLabel message = new JLabel("The Wild Card Color is Red!");
       message.setFont(new Font("Arial", Font.BOLD, 48));
       JOptionPane.showMessageDialog(null,message);
       allow = true;
       this.dispose();
       popUp.declaredColor = UNOCard.Color.Red;
       popUp.gameStage.setPName(popUp.game.getCurrentPlayer());
       popUp.gameStage.setButtonIcons();
       popUp.topCardButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Leo\\OneDrive\\Desktop\\images\\PNGs\\small\\" + popUp.game.getTopCardImage()));
       popUp.game.setCardColor(UNOCard.Color.Red);
       popUp.dispose();
    }//GEN-LAST:event_redButtonActionPerformed

    private void blueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueButtonActionPerformed
        wildColor = UNOCard.Color.Blue;
       JLabel message = new JLabel("The Wild Card Color is Blue!");
       message.setFont(new Font("Arial", Font.BOLD, 48));
       JOptionPane.showMessageDialog(null,message);
       allow = true;
       this.dispose();
       popUp.declaredColor = UNOCard.Color.Blue;
       popUp.gameStage.setPName(popUp.game.getCurrentPlayer());
       popUp.gameStage.setButtonIcons();
       popUp.topCardButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Leo\\OneDrive\\Desktop\\images\\PNGs\\small\\" + popUp.game.getTopCardImage()));
       popUp.game.setCardColor(UNOCard.Color.Blue);
       popUp.dispose();
    }//GEN-LAST:event_blueButtonActionPerformed

    private void greenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenButtonActionPerformed
        wildColor = UNOCard.Color.Green;
       JLabel message = new JLabel("The Wild Card Color is Green!");
       message.setFont(new Font("Arial", Font.BOLD, 48));
       JOptionPane.showMessageDialog(null,message);
       allow = true;
       this.dispose();
       popUp.declaredColor = UNOCard.Color.Green;
       popUp.gameStage.setPName(popUp.game.getCurrentPlayer());
       popUp.gameStage.setButtonIcons();
       popUp.topCardButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Leo\\OneDrive\\Desktop\\images\\PNGs\\small\\" + popUp.game.getTopCardImage()));
       popUp.game.setCardColor(UNOCard.Color.Green);
       popUp.dispose();
    }//GEN-LAST:event_greenButtonActionPerformed

    private void yellowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowButtonActionPerformed
       wildColor = UNOCard.Color.Yellow;
       JLabel message = new JLabel("The Wild Card Color is Yellow!");
       message.setFont(new Font("Arial", Font.BOLD, 48));
       JOptionPane.showMessageDialog(null,message);
       allow = true;
       this.dispose();
       popUp.declaredColor = UNOCard.Color.Yellow;
       popUp.gameStage.setPName(popUp.game.getCurrentPlayer());
       popUp.gameStage.setButtonIcons();
       popUp.topCardButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Leo\\OneDrive\\Desktop\\images\\PNGs\\small\\" + popUp.game.getTopCardImage()));
       popUp.game.setCardColor(UNOCard.Color.Yellow);
       popUp.dispose();
    }//GEN-LAST:event_yellowButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PickColorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PickColorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PickColorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PickColorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PickColorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blueButton;
    private javax.swing.JButton greenButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton redButton;
    private javax.swing.JButton yellowButton;
    // End of variables declaration//GEN-END:variables
}
