/**
 *
 * @author dewyy
 */
package recognitionarabicnumeral.View;

import com.sun.awt.AWTUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import recognitionarabicnumeral.Model.DataSet;
import recognitionarabicnumeral.Model.NumberImage;
import recognitionarabicnumeral.Util.FileFilterBMP;

public class Form_doTraining extends javax.swing.JFrame {
    
    NumberImage[] numberImages;
    int number;
    FileFilter filter;
    File[] Imagefiles;
    String[] label[];
    List<String> labelDataSet = new ArrayList<String>();
    
    int posX =0;
    int posY =0;
    int row = 0;
    
    DataSet trainingSet;
    
    public enum Option{MultipleDirect, MultipleSelection}
    
    public Form_doTraining() {
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
        tableListRetina.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = tableListRetina.getSelectedRow();
                if (row != -1) {
                    setPreview(trainingSet.getNumberImage(row));
                }
            }
        });
        
        trainingSet = new DataSet();
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
        Object data[][] = new Object[trainingSet.getTotal()][4];
        
        for(int i=0;i<trainingSet.getTotal();i++){
            data[i][0] = (i+1);
            data[i][1] = trainingSet.getNamaFile(i);
            data[i][2] = trainingSet.getPath(i);
            data[i][3] = trainingSet.getLabelFile(i);
        }
        
        String judul[] = {"No","Nama Gambar", "Path","Target"};
        tableListRetina.setModel(new DefaultTableModel(data, judul));
        jScrollPane2.setViewportView(tableListRetina);   
    }
    
    public void data(int n) throws FileNotFoundException, IOException{
        
        String path = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber",
               namaFile = path+"\\Data Citra\\LabelTesting.txt",
               line,
               EXTENSIONS[] = new String[]{ "bmp" };
        
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

                trainingSet.addNumberImage(numberImages[i], f.getName(), f.getPath(), Label[i] );
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
        bGclass = new javax.swing.ButtonGroup();
        bGbobot = new javax.swing.ButtonGroup();
        btClose = new javax.swing.JLabel();
        lblPreview = new javax.swing.JLabel();
        btBack = new javax.swing.JLabel();
        btTraining = new javax.swing.JLabel();
        btInputData = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListRetina = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btClose.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png")); // NOI18N
        btClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btCloseMouseExited(evt);
            }
        });
        getContentPane().add(btClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, -1, 30));
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 133, 143, 126));

        btBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        getContentPane().add(btBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 50, -1));

        btTraining.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntraining1.png")); // NOI18N
        btTraining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btTrainingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btTrainingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btTrainingMouseExited(evt);
            }
        });
        getContentPane().add(btTraining, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 130, 50));

        btInputData.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInput.png")); // NOI18N
        btInputData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btInputDataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btInputDataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btInputDataMouseExited(evt);
            }
        });
        getContentPane().add(btInputData, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 130, 50));

        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(320, 280));

        tableListRetina.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Nama Gambar", "Path", "Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListRetina.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableListRetina.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableListRetina.setFocusable(false);
        tableListRetina.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(tableListRetina);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 530, 300));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\FormPelatihan.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTrainingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTrainingMouseClicked
        if(trainingSet.getTotal()==0)
            showMessage("Dataset Pengujian belum diisi. Mohon diinputkan kembali");
        else{
            ProgressBar pbTraining = new ProgressBar(trainingSet,1);
            this.dispose();
            pbTraining.setVisible(true);
            pbTraining.doTraining();
        }
        
    }//GEN-LAST:event_btTrainingMouseClicked

    private void btInputDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputDataMouseClicked
               
        Option[] choice = {Option.MultipleDirect,Option.MultipleSelection};
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

                            trainingSet.addNumberImage(numberImages[i], rImage[i].getName(), rImage[i].getPath(),label );
                        }
                    }
                } 
                break;
        }
        isiTabelDaftarGambar();
    }//GEN-LAST:event_btInputDataMouseClicked

    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void btCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCloseMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtn.png");
        btClose.setIcon(iconHover);
    }//GEN-LAST:event_btCloseMouseEntered

    private void btCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCloseMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\closeBtnHover.png");
        btClose.setIcon(iconHover);
    }//GEN-LAST:event_btCloseMouseExited

    private void btCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_btCloseMouseClicked

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
        //form.setVisible(true);;
    }//GEN-LAST:event_btBackMouseClicked

    private void btTrainingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTrainingMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntraining1.png");
        btTraining.setIcon(iconHover);
    }//GEN-LAST:event_btTrainingMouseEntered

    private void btTrainingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTrainingMouseExited
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btntrainingHover1.png");
        btTraining.setIcon(iconHover);
    }//GEN-LAST:event_btTrainingMouseExited

    private void btInputDataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputDataMouseEntered
        ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInput.png");
        btInputData.setIcon(iconHover);
    }//GEN-LAST:event_btInputDataMouseEntered

    private void btInputDataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInputDataMouseExited
         ImageIcon iconHover = new ImageIcon("C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Design Interface\\btnInputHover.png");
         btInputData.setIcon(iconHover);
    }//GEN-LAST:event_btInputDataMouseExited

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_doTraining().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGbobot;
    private javax.swing.ButtonGroup bGclass;
    private javax.swing.JLabel btBack;
    private javax.swing.JLabel btClose;
    private javax.swing.JLabel btInputData;
    private javax.swing.JLabel btTraining;
    private javax.swing.JFileChooser chooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JTable tableListRetina;
    // End of variables declaration//GEN-END:variables

  
}
