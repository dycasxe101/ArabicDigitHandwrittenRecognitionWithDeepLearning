/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.View;

import com.sun.awt.AWTUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author EGA
 */
public class FormUtama extends javax.swing.JFrame {

    /**
     * Creates new form FormUtama
     */
    
    int posX=0,posY=0;
    
    public FormUtama() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(this);
        AWTUtilities.setWindowOpaque(this, false);
        
        this.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
              posX=e.getX();
              posY=e.getY();
           }
        });
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent evt){
                //sets frame position when mouse dragged			
                setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
            }
       });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLklasifikasi = new javax.swing.JLabel();
        jLtraining = new javax.swing.JLabel();
        jclose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusableWindowState(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLklasifikasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLklasifikasi.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasi.png")); // NOI18N
        jLklasifikasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLklasifikasiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLklasifikasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLklasifikasiMouseExited(evt);
            }
        });
        getContentPane().add(jLklasifikasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 160, 50));

        jLtraining.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLtraining.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntraining.png")); // NOI18N
        jLtraining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLtrainingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLtrainingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLtrainingMouseExited(evt);
            }
        });
        getContentPane().add(jLtraining, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 160, 50));
        jLtraining.getAccessibleContext().setAccessibleDescription("");

        jclose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jclose.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png")); // NOI18N
        jclose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jcloseMouseExited(evt);
            }
        });
        getContentPane().add(jclose, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\FormUtama.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLklasifikasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLklasifikasiMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasiHover.png");
        jLklasifikasi.setIcon(iconHover);
    }//GEN-LAST:event_jLklasifikasiMouseEntered

    private void jLklasifikasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLklasifikasiMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasi.png");
        jLklasifikasi.setIcon(iconHover);
    }//GEN-LAST:event_jLklasifikasiMouseExited

    private void jLtrainingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLtrainingMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntrainingHover.png");
        jLtraining.setIcon(iconHover);
    }//GEN-LAST:event_jLtrainingMouseEntered

    private void jLtrainingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLtrainingMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntraining.png");
        jLtraining.setIcon(iconHover);
    }//GEN-LAST:event_jLtrainingMouseExited

    private void jcloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcloseMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png");
        jclose.setIcon(iconHover);
    }//GEN-LAST:event_jcloseMouseEntered

    private void jcloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcloseMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtnHover.png");
        jclose.setIcon(iconHover);
    }//GEN-LAST:event_jcloseMouseExited

    private void jcloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_jcloseMouseClicked

    private void jLklasifikasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLklasifikasiMouseClicked
        Form_doTesting form = new Form_doTesting();
        //this.dispose();
        form.setVisible(true);
    }//GEN-LAST:event_jLklasifikasiMouseClicked

    private void jLtrainingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLtrainingMouseClicked
        Form_doTraining form = new Form_doTraining();
        //this.dispose();
        form.setVisible(true);
    }//GEN-LAST:event_jLtrainingMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLklasifikasi;
    private javax.swing.JLabel jLtraining;
    private javax.swing.JLabel jclose;
    // End of variables declaration//GEN-END:variables
}