/**
 *
 * @author dewyy
 */
package recognitionarabicnumeral.View;

import com.sun.awt.AWTUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import recognitionarabicnumeral.Model.DataSet;
import recognitionarabicnumeral.Model.NumberImage;
import recognitionarabicnumeral.Util.FileFilterBMP;

public class Form_doTesting extends javax.swing.JFrame {
    int number;
    int posX =0;
    int posY =0;
    int row = 0;
    NumberImage[] numberImages;
    DataSet testingSet;
    FileFilter filter;
    public enum Option{MultipleDirect, MultipleSelection}
    File[] Imagefiles;
    String label[];
    
    public Form_doTesting() {
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
        
        jTableListRetina.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jTableListRetina.getSelectedRow();
                if (row != -1) {
                    setPreview(testingSet.getNumberImage(row));
                }
            }
        });
        testingSet = new DataSet();
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
        Object data[][] = new Object[testingSet.getTotal()][4];
        
        for(int i=0;i<testingSet.getTotal();i++){
            data[i][0] = (i+1);
            data[i][1] = testingSet.getNamaFile(i);
            data[i][2] = testingSet.getPath(i);
            data[i][3] = testingSet.getLabelFile(i);
        }
        
        String judul[] = {"No","Nama Gambar", "Path","Label"};
        jTableListRetina.setModel(new DefaultTableModel(data, judul));
        jScrollPane3.setViewportView(jTableListRetina);   
    }
    
    public void data(int n) throws FileNotFoundException, IOException{
        
        String path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber",
               namaFile = path+"\\Data Citra\\LabelTesting.txt",
               line,
               EXTENSIONS[] = new String[]{ "bmp" };
        
        //File dir = new File(path+"\\Data Citra\\ArabicBMP");
        File dir = new File(path+"\\Data Citra\\Testing");
        int Label[] = new int[n];

        StringBuffer temp = new StringBuffer();
        File fileBobot = new File(namaFile);  
        FilenameFilter IMAGE_FILTER = new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
        FileReader in = new FileReader(fileBobot);
        BufferedReader dis = new BufferedReader(in);

        for (int i=0; i<n; i++) if((line = dis.readLine()) != null) Label[i] =Integer.parseInt(line);
        in.close();
        
        int i = 0;
        if (dir.isDirectory() && i<n) { 
            numberImages = new NumberImage[n];
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                numberImages[i] = new NumberImage();
                numberImages[i].setName(f.getName());
                numberImages[i].setPath(f.getPath());
                numberImages[i].setLabel(Label[i]);

                testingSet.addNumberImage(numberImages[i], f.getName(), f.getPath(), Label[i] );
                i++;
                if (i==n)break;
            }
            //trainingSet.tampilList();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooser1 = new javax.swing.JFileChooser();
        jR8 = new javax.swing.JRadioButton();
        jR7 = new javax.swing.JRadioButton();
        jR6 = new javax.swing.JRadioButton();
        jR5 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListRetina = new javax.swing.JTable();
        lblPreview = new javax.swing.JLabel();
        btExit = new javax.swing.JLabel();
        btInput = new javax.swing.JLabel();
        btBack = new javax.swing.JLabel();
        btKlasifikasi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jR8.setText("Epoch 250");
        jR8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR8ActionPerformed(evt);
            }
        });
        getContentPane().add(jR8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, -1, 20));

        jR7.setText("Epoch 200");
        jR7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR7ActionPerformed(evt);
            }
        });
        getContentPane().add(jR7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, -1, 20));

        jR6.setText("Epoch 150");
        jR6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR6ActionPerformed(evt);
            }
        });
        getContentPane().add(jR6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, -1, -1));

        jR5.setText("Epoch 100");
        jR5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jR5ActionPerformed(evt);
            }
        });
        getContentPane().add(jR5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, -1, -1));

        jTableListRetina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Nama File", "Path", "Label"
            }
        ));
        jScrollPane3.setViewportView(jTableListRetina);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 140, 525, 290));
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 145, 150, 130));

        btExit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png")); // NOI18N
        btExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btExitMouseExited(evt);
            }
        });
        getContentPane().add(btExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, 30, 30));

        btInput.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInput.png")); // NOI18N
        btInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btInputMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btInputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btInputMouseExited(evt);
            }
        });
        getContentPane().add(btInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 130, 50));

        btBack.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBack.png")); // NOI18N
        btBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btBackMouseExited(evt);
            }
        });
        getContentPane().add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 30, -1));

        btKlasifikasi.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasi2.png")); // NOI18N
        btKlasifikasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btKlasifikasiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btKlasifikasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btKlasifikasiMouseExited(evt);
            }
        });
        getContentPane().add(btKlasifikasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\FormKlasifikasi.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 810, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void btInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputMouseClicked
        
        Option[] choice = {Option.MultipleDirect, Option.MultipleSelection};
        Option Select = (Option) JOptionPane.showInputDialog(null, "Pilih Opsi Input Data Gambar", "Opsi", JOptionPane.INFORMATION_MESSAGE, null, choice, choice[1]);
        switch (Select){
            case MultipleDirect:
                String sum = JOptionPane.showInputDialog("Masukkan Jumlah Data Testing : ");
                int sumImage = Integer.valueOf(sum);
                {
                    try {
                        data(sumImage);
                    } catch (IOException ex) {
                        Logger.getLogger(Form_doTesting.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case MultipleSelection:
                chooser1 = new JFileChooser("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Data Citra\\Testing");
                chooser1.setDialogTitle("Pilih Gambar");
                chooser1.setFileFilter(new FileFilterBMP());
                chooser1.setMultiSelectionEnabled(true);
                if(chooser1.showOpenDialog(jPanel1)==JFileChooser.APPROVE_OPTION){
                   File[] rImage = chooser1.getSelectedFiles();
                   this.numberImages = new NumberImage[rImage.length];
                   String target = JOptionPane.showInputDialog("Masukkan target : ");
                   int label;
                   if(target == null){
                        showMessage("Dibatalkan");
                    }else if(!target.equals("0") && !target.equals("1")&& !target.equals("2")){
                        showMessage("Masukan harus 0 = Normal atau 1 = NPDR atau 2 untuk PDR");
                    }else{
                        if (target.equals("0")) label=0;
                        else if(target.equals("1")) label=1;
                        else label = 2;
                        for(int i=0;i<rImage.length;i++){
                            numberImages[i] = new NumberImage();
                            numberImages[i].setName(rImage[i].getName());
                            numberImages[i].setPath(rImage[i].getPath());
                            numberImages[i].setLabel(label);

                            testingSet.addNumberImage(numberImages[i], rImage[i].getName(), rImage[i].getPath(),label );
                        }
                    }
                } 
                break;
        }
        isiTabelDaftarGambar();
    }//GEN-LAST:event_btInputMouseClicked

    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    private void btKlasifikasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btKlasifikasiMouseClicked
        
        if(testingSet.getTotal()==0)
            showMessage("Dataset Pengujian belum diisi. Mohon diinputkan kembali");
        else{
            if(!jR5.isSelected() && !jR6.isSelected() && !jR7.isSelected() && !jR8.isSelected() )
                showMessage("Pilih Opsi Epoch");
            else {
                
                String path;
                if(jR5.isSelected()) 
                    path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Bobot dan Bias\\Testing\\HasilTraining 50 - 100 Epoch";
                else if(jR6.isSelected()) 
                    path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Bobot dan Bias\\Testing\\HasilTraining 101 - 150 Epoch";
                else if(jR7.isSelected()) 
                    path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Bobot dan Bias\\Testing\\HasilTraining 151 - 200 Epoch";
                else
                    path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Bobot dan Bias\\Testing\\HasilTraining 201 - 250 Epoch";
                System.out.println(" "+path);
                ProgressBar pbTesting  = new ProgressBar(testingSet, path, 2);
                pbTesting.doTesting();
                pbTesting.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_btKlasifikasiMouseClicked
    
    private void btExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExitMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png");
        btExit.setIcon(iconHover);
    }//GEN-LAST:event_btExitMouseEntered

    private void btExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExitMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtnHover.png");
        btExit.setIcon(iconHover);
    }//GEN-LAST:event_btExitMouseExited

    private void btExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_btExitMouseClicked

    private void btBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBack.png");
        btBack.setIcon(iconHover);
    }//GEN-LAST:event_btBackMouseEntered

    private void btBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnBackHover.png");
        btBack.setIcon(iconHover);
    }//GEN-LAST:event_btBackMouseExited

    private void btBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btBackMouseClicked
        //FormUtama form = new FormUtama();
            this.dispose();
        //form.setVisible(true);
    }//GEN-LAST:event_btBackMouseClicked

    private void btKlasifikasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btKlasifikasiMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasi2.png");
        btKlasifikasi.setIcon(iconHover);
    }//GEN-LAST:event_btKlasifikasiMouseEntered

    private void btKlasifikasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btKlasifikasiMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnklasifikasiHover1.png");
        btKlasifikasi.setIcon(iconHover);
    }//GEN-LAST:event_btKlasifikasiMouseExited

    private void btInputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInput.png");
        btInput.setIcon(iconHover);
    }//GEN-LAST:event_btInputMouseEntered

    private void btInputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInputHover.png");
        btInput.setIcon(iconHover);
    }//GEN-LAST:event_btInputMouseExited

    private void jR5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR5ActionPerformed
        jR6.setSelected(false);
        jR7.setSelected(false);
        jR8.setSelected(false);
    }//GEN-LAST:event_jR5ActionPerformed

    private void jR6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR6ActionPerformed
        jR5.setSelected(false);
        jR7.setSelected(false);
        jR8.setSelected(false);
    }//GEN-LAST:event_jR6ActionPerformed

    private void jR7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR7ActionPerformed
        jR6.setSelected(false);
        jR5.setSelected(false);
        jR8.setSelected(false);
    }//GEN-LAST:event_jR7ActionPerformed

    private void jR8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jR8ActionPerformed
        jR6.setSelected(false);
        jR5.setSelected(false);
        jR7.setSelected(false);
    }//GEN-LAST:event_jR8ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_doTesting().setVisible(true);
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btBack;
    private javax.swing.JLabel btExit;
    private javax.swing.JLabel btInput;
    private javax.swing.JLabel btKlasifikasi;
    private javax.swing.JFileChooser chooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jR5;
    private javax.swing.JRadioButton jR6;
    private javax.swing.JRadioButton jR7;
    private javax.swing.JRadioButton jR8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableListRetina;
    private javax.swing.JLabel lblPreview;
    // End of variables declaration//GEN-END:variables
}
