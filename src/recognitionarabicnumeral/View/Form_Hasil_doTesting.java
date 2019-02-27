/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.View;

import com.sun.awt.AWTUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import recognitionarabicnumeral.Model.DataSet;
import recognitionarabicnumeral.Model.NumberImage;

/**
 *
 * @author dewyy
 */
public class Form_Hasil_doTesting extends javax.swing.JFrame {
    
    NumberImage[] numberImages;
    DataSet testingSet;
    
    File[] Imagefiles;
    int LabelPrediksi[];
    double Output[][];
    
    int number;
    int posX =0;
    int posY =0;
    int row = 0;
    
    public Form_Hasil_doTesting(double akurasi, double[][] output, double[][] MatrikValidasi, DataSet TestingSet, int[] labelprediksi) {
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
        
        testingSet = new DataSet();
        this.testingSet = TestingSet;
        this.Output = output;
        this.LabelPrediksi = labelprediksi;
        isiTabelDaftarGambar();
        isiMatriksValidasi(MatrikValidasi);
        String accuracy = String.valueOf(akurasi);
        lblAkurasi.setText(accuracy+"%");
        
        jTableDataImage.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jTableDataImage.getSelectedRow();
                if (row != -1) {
                    setPreview(testingSet.getNumberImage(row));
                    isiOutput(getOutput(row));
                }
            }
        });
    }
    
    double[] getOutput(int i){
        return  Output[i];
    }
    
    void setPreview(NumberImage numberImages){
        try {
            BufferedImage preview;
            File f = new File(numberImages.getFilePath());
            preview = ImageIO.read(f);
            lblPreview.setIcon(new ImageIcon(numberImages.resize(preview, lblPreview.getHeight(), lblPreview.getWidth())));
        } catch (IOException ex) {
            Logger.getLogger(Form_doTraining.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void isiTabelDaftarGambar(){
        Object data[][] = new Object[testingSet.getTotal()][5];
        String ii;
        for(int i=0;i<testingSet.getTotal();i++){
            data[i][0] = (i+1);
            data[i][1] = testingSet.getNamaFile(i);
            data[i][2] = testingSet.getLabelFile(i);
            data[i][3] = LabelPrediksi[i];
            if(testingSet.getLabelFile(i)==LabelPrediksi[i])  ii = "Benar";
            else ii = "Salah";
            data[i][4] = ii;
        }
        
        String judul[] = {"No","Nama Gambar", "Label","Hasil Prediksi", "Validasi"};
        jTableDataImage.setModel(new DefaultTableModel(data, judul));
        jScrollPane1.setViewportView(jTableDataImage);   
    }
    
    void isiOutput(double[] output){
        Object data[][] = new Object[output.length][2];
        for(int i=0;i<output.length;i++){
            data[i][0] = i;
            data[i][1] = output[i];
        }
        String[] judul = {"Output","Nilai Output"};
        jTableOutput.setModel(new DefaultTableModel(data, judul));
        jScrollPane2.setViewportView(jTableOutput);
    }
    
    void isiMatriksValidasi(double[][] MatrikValidasi){
        Object data[][] = new Object[11][11];
        for(int i=0;i<MatrikValidasi.length;i++){
                data[i][0] = i;
                data[i][1] = MatrikValidasi[i][0];
                data[i][2] = MatrikValidasi[i][1];
                data[i][3] = MatrikValidasi[i][2];
                data[i][4] = MatrikValidasi[i][3];
                data[i][5] = MatrikValidasi[i][4];
                data[i][6] = MatrikValidasi[i][5];
                data[i][7] = MatrikValidasi[i][6];
                data[i][8] = MatrikValidasi[i][7];
                data[i][9] = MatrikValidasi[i][8];
                data[i][10] = MatrikValidasi[i][9];
        }
        String[] judul = {"Aktual\\Prediksi", "0","1","2","3","4","5","6","7","8","9"};
        jTableMatriks.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(jTableMatriks);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDataImage = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOutput = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMatriks = new javax.swing.JTable();
        lblPreview = new javax.swing.JLabel();
        lblAkurasi = new javax.swing.JLabel();
        jBack = new javax.swing.JLabel();
        jClose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableDataImage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Nama File", "Path", "Label", "Prediksi"
            }
        ));
        jScrollPane1.setViewportView(jTableDataImage);
        if (jTableDataImage.getColumnModel().getColumnCount() > 0) {
            jTableDataImage.getColumnModel().getColumn(2).setHeaderValue("Path");
            jTableDataImage.getColumnModel().getColumn(3).setHeaderValue("Label");
            jTableDataImage.getColumnModel().getColumn(4).setHeaderValue("Prediksi");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 183, 435, 370));

        jTableOutput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No", "Nama File"
            }
        ));
        jScrollPane2.setViewportView(jTableOutput);
        if (jTableOutput.getColumnModel().getColumnCount() > 0) {
            jTableOutput.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableOutput.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 370, 230, 185));

        jTableMatriks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Aktual\\Prediksi", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            }
        ));
        jScrollPane3.setViewportView(jTableMatriks);
        if (jTableMatriks.getColumnModel().getColumnCount() > 0) {
            jTableMatriks.getColumnModel().getColumn(0).setMinWidth(50);
            jTableMatriks.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(777, 370, 370, 185));
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 160, 120));

        lblAkurasi.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblAkurasi.setForeground(new java.awt.Color(153, 0, 0));
        lblAkurasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblAkurasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 185, 150, 120));

        jBack.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBack.png")); // NOI18N
        jBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBackMouseExited(evt);
            }
        });
        getContentPane().add(jBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 30, 30));

        jClose.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png")); // NOI18N
        jClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCloseMouseExited(evt);
            }
        });
        getContentPane().add(jClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 570, 30, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\FormHasilKlasifikasi 4.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 630));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 173, 455, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png");
        jClose.setIcon(iconHover);
    }//GEN-LAST:event_jCloseMouseEntered

    private void jCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtnHover.png");
        jClose.setIcon(iconHover);
    }//GEN-LAST:event_jCloseMouseExited

    private void jCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_jCloseMouseClicked

    private void jBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jBackMouseClicked

    private void jBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackMouseEntered
        // TODO add your handling code here:
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBack.png");
        jBack.setIcon(iconHover);
    }//GEN-LAST:event_jBackMouseEntered

    private void jBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackMouseExited
        // TODO add your handling code here:
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBackHover.png");
        jBack.setIcon(iconHover);
    }//GEN-LAST:event_jBackMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBack;
    private javax.swing.JLabel jClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableDataImage;
    private javax.swing.JTable jTableMatriks;
    private javax.swing.JTable jTableOutput;
    private javax.swing.JLabel lblAkurasi;
    private javax.swing.JLabel lblPreview;
    // End of variables declaration//GEN-END:variables
}
