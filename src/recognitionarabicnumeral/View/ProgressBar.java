/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.View;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import recognitionarabicnumeral.Controller.CNNController;
import recognitionarabicnumeral.Controller.Convolution;
import recognitionarabicnumeral.Controller.FullyConnected;
import recognitionarabicnumeral.Controller.Subsampling;
import recognitionarabicnumeral.Model.DataSet;
import recognitionarabicnumeral.Model.NumberImage;

/**
 *
 * @author dewyy
 */
public class ProgressBar extends javax.swing.JFrame {

    /**
     * Creates new form ProgressBar
     */
    
    int posX = 0, posY = 0;   
    
    static int  t0_Number = 1, c1_Number = 6, 
                s2_Number = 6, c3_Number = 16, s4_Number = 16, 
                c5_Number = 120, f6_Number = 84,g7_Number = 10;
    
    static int  t0_Scale = 32, c1_Scale = 28, s2_Scale = 14, 
                c3_Scale = 10, s4_Scale = 5, c5_Scale = 1;
    
    static int  convolution_Kernel_Scale    = 5, 
                subsampling_Kernel_Scale    = 2;
    
    int status, Label[];
    String Path;
    
    DataSet trainingSet, testingSet;
    NumberImage[] numberImageMultiples;
    
    public ProgressBar() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(this);
        AWTUtilities.setWindowOpaque(this, false);
        this.setBackground(new Color(0,0,0,0));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged			
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });
    }
    
    public ProgressBar(DataSet Training, int status) {
        
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setBackground(new Color(0,0,0,0));
        AWTUtilities.setWindowOpaque(this, false);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged			
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });
        
        this.trainingSet = Training;
        this.status = status;
    }
    
    public ProgressBar(DataSet Testing, String path, int status) {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(this);
        AWTUtilities.setWindowOpaque(this, false);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged			
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });
        
        this.testingSet = Testing;
        this.status = status;
        this.Path = path;
    }
    
    public void data(int n) throws FileNotFoundException, IOException{
        String namaFile = "C:\\Users\\Dewy Yuliana\\Documents\\NetBeansProjects\\TA Baru\\Program\\RecognitionArabicNumeral\\ArabicNumber\\Data Citra\\LabelTesting.txt";
        Label = new int[n];

        String line;
        StringBuffer temp = new StringBuffer();
        File fileBobot = new File(namaFile);        
        
        FileReader in = new FileReader(fileBobot);
        BufferedReader dis = new BufferedReader(in);

        for (int i=0; i<n; i++)
            if((line = dis.readLine()) != null){
                Label[i] =Integer.parseInt(line);
            }
        in.close();
    }
    
    public void doTesting(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                try {
                    data(testingSet.getTotal());
                } catch (IOException ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                double  max, now, accuracy = 0, sum_akurasi = 0,
                        output[][]                      = new double[testingSet.getTotal()][10],
                        matriksValid[][]                = new double[10][10];
                
                int     maxNumber, sum_data,
                        labelprediksi[]    = new int[testingSet.getTotal()],
                        standard[]              = new int[g7_Number];
                
                String[]    fileName                    = new String[12];
                            Random rng                  = new Random(1234);
                            
                boolean print                           = false;
                try {
                    
                    sum_data                            = testingSet.getTotal();
                    CNNController cnnTesting            = new CNNController(c1_Number, s2_Number,c3_Number,s4_Number, c5_Number, f6_Number, g7_Number, c1_Scale, c3_Scale, c5_Scale, convolution_Kernel_Scale, subsampling_Kernel_Scale);
                    NumberImage[] numberImagesMultiple  = new NumberImage[sum_data];
                    
                    fileName            = cnnTesting.set_getfilename_testing(Path);
                    print               = false;
                    String valid;
                    
                    Convolution c1 = new Convolution(c1_Number, c1_Scale, convolution_Kernel_Scale, status, fileName[0], fileName[1]);
                    Convolution c3 = new Convolution(c3_Number, c3_Scale, convolution_Kernel_Scale, status,  fileName[2], fileName[3]);
                    Convolution c5 = new Convolution(c5_Number, c5_Scale, convolution_Kernel_Scale, status,  fileName[4], fileName[5]);

                    Subsampling s2 = new Subsampling(s2_Number, s2_Scale, subsampling_Kernel_Scale);
                    Subsampling s4 = new Subsampling(s4_Number, s4_Scale, subsampling_Kernel_Scale);

                    FullyConnected f6 = new FullyConnected(f6_Number, c5_Number, status, fileName[6], fileName[7] );
                    FullyConnected g7 = new FullyConnected(g7_Number, f6_Number, status, fileName[8], fileName[9] );
                    
                    for (int ii = 0; ii < 10; ii++) {
                        for (int j = 0; j < 10; j++) {
                            matriksValid[ii][j]=0;
                        }
                    }
                    double progress = 360.0/sum_data;
                    int k=0;
                    for(int i=0;i<sum_data;i++){
                        
                        //initial
                        now = max = maxNumber   = 0;
                        valid                   = "false";
                        cnnTesting.initial(c1, s2, c3, s4, c5, f6, g7);

                        //Data Citra
                        numberImagesMultiple[i] = new NumberImage();
                        numberImagesMultiple[i] = testingSet.getNumberImage(i);
                        numberImagesMultiple[i].setNumberImage();
                        numberImagesMultiple[i].readPixel();
                        
                        Label[i] = numberImagesMultiple[i].getLabel();

                        //forward
                        cnnTesting.forward(print, numberImagesMultiple[i].getPixelImage(), c1, s2, c3, s4, c5, f6, g7);   
                        
                        for (int ii = 0; ii < g7_Number; ii++) {
                            output[i][ii] = g7.get_Layer(ii);
                            now = g7.get_Layer(ii);
                            if (now > max) {
                                max = now;
                                maxNumber = ii;
                            }
                            //System.out.println("max - " + max + " label "+ maxNumber);
                        }
                        labelprediksi[i]=maxNumber;    
                        if(maxNumber == Label[i]) {
                            sum_akurasi++;
                            valid = "true";
                        }
                        if (valid == "false") {
                            System.out.print("\nData Image ke_"+(i+1) +" "+numberImagesMultiple[i].getImageName() );
                            System.out.print("\tLabel prediksi "+ maxNumber +"\tLabel Aktual "+ Label[i] + " \tValidation " +valid );
                          
                        }
                        
                        if(Label[i]==0 && maxNumber == 0)       matriksValid[0][0]++;
                        else if (Label[i]==0 && maxNumber == 1) matriksValid[0][1]++;
                        else if (Label[i]==0 && maxNumber == 2) matriksValid[0][2]++;
                        else if (Label[i]==0 && maxNumber == 3) matriksValid[0][3]++;
                        else if (Label[i]==0 && maxNumber == 4) matriksValid[0][4]++;
                        else if (Label[i]==0 && maxNumber == 5) matriksValid[0][5]++;
                        else if (Label[i]==0 && maxNumber == 6) matriksValid[0][6]++;
                        else if (Label[i]==0 && maxNumber == 7) matriksValid[0][7]++;
                        else if (Label[i]==0 && maxNumber == 8) matriksValid[0][8]++;
                        else if (Label[i]==0 && maxNumber == 9) matriksValid[0][9]++;
                        
                        else if(Label[i]==1 && maxNumber == 0)  matriksValid[1][0]++;
                        else if (Label[i]==1 && maxNumber == 1) matriksValid[1][1]++;
                        else if (Label[i]==1 && maxNumber == 2) matriksValid[1][2]++;
                        else if (Label[i]==1 && maxNumber == 3) matriksValid[1][3]++;
                        else if (Label[i]==1 && maxNumber == 4) matriksValid[1][4]++;
                        else if (Label[i]==1 && maxNumber == 5) matriksValid[1][5]++;
                        else if (Label[i]==1 && maxNumber == 6) matriksValid[1][6]++;
                        else if (Label[i]==1 && maxNumber == 7) matriksValid[1][7]++;
                        else if (Label[i]==1 && maxNumber == 8) matriksValid[1][8]++;
                        else if (Label[i]==1 && maxNumber == 9) matriksValid[1][9]++;
                        
                        else if(Label[i]==2 && maxNumber == 0)  matriksValid[2][0]++;
                        else if (Label[i]==2 && maxNumber == 1) matriksValid[2][1]++;
                        else if (Label[i]==2 && maxNumber == 2) matriksValid[2][2]++;
                        else if (Label[i]==2 && maxNumber == 3) matriksValid[2][3]++;
                        else if (Label[i]==2 && maxNumber == 4) matriksValid[2][4]++;
                        else if (Label[i]==2 && maxNumber == 5) matriksValid[2][5]++;
                        else if (Label[i]==2 && maxNumber == 6) matriksValid[2][6]++;
                        else if (Label[i]==2 && maxNumber == 7) matriksValid[2][7]++;
                        else if (Label[i]==2 && maxNumber == 8) matriksValid[2][8]++;
                        else if (Label[i]==2 && maxNumber == 9) matriksValid[2][9]++;
                       
                        else if(Label[i]==3 && maxNumber == 0)  matriksValid[3][0]++;
                        else if (Label[i]==3 && maxNumber == 1) matriksValid[3][1]++;
                        else if (Label[i]==3 && maxNumber == 2) matriksValid[3][2]++;
                        else if (Label[i]==3 && maxNumber == 3) matriksValid[3][3]++;
                        else if (Label[i]==3 && maxNumber == 4) matriksValid[3][4]++;
                        else if (Label[i]==3 && maxNumber == 5) matriksValid[3][5]++;
                        else if (Label[i]==3 && maxNumber == 6) matriksValid[3][6]++;
                        else if (Label[i]==3 && maxNumber == 7) matriksValid[3][7]++;
                        else if (Label[i]==3 && maxNumber == 8) matriksValid[3][8]++;
                        else if (Label[i]==3 && maxNumber == 9) matriksValid[3][9]++;
                        
                        else if(Label[i]==4 && maxNumber == 0)  matriksValid[4][0]++;
                        else if (Label[i]==4 && maxNumber == 1) matriksValid[4][1]++;
                        else if (Label[i]==4 && maxNumber == 2) matriksValid[4][2]++;
                        else if (Label[i]==4 && maxNumber == 3) matriksValid[4][3]++;
                        else if (Label[i]==4 && maxNumber == 4) matriksValid[4][4]++;
                        else if (Label[i]==4 && maxNumber == 5) matriksValid[4][5]++;
                        else if (Label[i]==4 && maxNumber == 6) matriksValid[4][6]++;
                        else if (Label[i]==4 && maxNumber == 7) matriksValid[4][7]++;
                        else if (Label[i]==4 && maxNumber == 8) matriksValid[4][8]++;
                        else if (Label[i]==4 && maxNumber == 9) matriksValid[4][9]++;
                        
                        else if(Label[i]==5 && maxNumber == 0)  matriksValid[5][0]++;
                        else if (Label[i]==5 && maxNumber == 1) matriksValid[5][1]++;
                        else if (Label[i]==5 && maxNumber == 2) matriksValid[5][2]++;
                        else if (Label[i]==5 && maxNumber == 3) matriksValid[5][3]++;
                        else if (Label[i]==5 && maxNumber == 4) matriksValid[5][4]++;
                        else if (Label[i]==5 && maxNumber == 5) matriksValid[5][5]++;
                        else if (Label[i]==5 && maxNumber == 6) matriksValid[5][6]++;
                        else if (Label[i]==5 && maxNumber == 7) matriksValid[5][7]++;
                        else if (Label[i]==5 && maxNumber == 8) matriksValid[5][8]++;
                        else if (Label[i]==5 && maxNumber == 9) matriksValid[5][9]++;
                        
                        else if(Label[i]==6 && maxNumber == 0)  matriksValid[6][0]++;
                        else if (Label[i]==6 && maxNumber == 1) matriksValid[6][1]++;
                        else if (Label[i]==6 && maxNumber == 2) matriksValid[6][2]++;
                        else if (Label[i]==6 && maxNumber == 3) matriksValid[6][3]++;
                        else if (Label[i]==6 && maxNumber == 4) matriksValid[6][4]++;
                        else if (Label[i]==6 && maxNumber == 5) matriksValid[6][5]++;
                        else if (Label[i]==6 && maxNumber == 6) matriksValid[6][6]++;
                        else if (Label[i]==6 && maxNumber == 7) matriksValid[6][7]++;
                        else if (Label[i]==6 && maxNumber == 8) matriksValid[6][8]++;
                        else if (Label[i]==6 && maxNumber == 9) matriksValid[6][9]++;
                        
                        else if(Label[i]==7 && maxNumber == 0)  matriksValid[7][0]++;
                        else if (Label[i]==7 && maxNumber == 1) matriksValid[7][1]++;
                        else if (Label[i]==7 && maxNumber == 2) matriksValid[7][2]++;
                        else if (Label[i]==7 && maxNumber == 3) matriksValid[7][3]++;
                        else if (Label[i]==7 && maxNumber == 4) matriksValid[7][4]++;
                        else if (Label[i]==7 && maxNumber == 5) matriksValid[7][5]++;
                        else if (Label[i]==7 && maxNumber == 6) matriksValid[7][6]++;
                        else if (Label[i]==7 && maxNumber == 7) matriksValid[7][7]++;
                        else if (Label[i]==7 && maxNumber == 8) matriksValid[7][8]++;
                        else if (Label[i]==7 && maxNumber == 9) matriksValid[2][9]++;
                        
                        else if(Label[i]==8 && maxNumber == 0)  matriksValid[8][0]++;
                        else if (Label[i]==8 && maxNumber == 1) matriksValid[8][1]++;
                        else if (Label[i]==8 && maxNumber == 2) matriksValid[8][2]++;
                        else if (Label[i]==8 && maxNumber == 3) matriksValid[8][3]++;
                        else if (Label[i]==8 && maxNumber == 4) matriksValid[8][4]++;
                        else if (Label[i]==8 && maxNumber == 5) matriksValid[8][5]++;
                        else if (Label[i]==8 && maxNumber == 6) matriksValid[8][6]++;
                        else if (Label[i]==8 && maxNumber == 7) matriksValid[8][7]++;
                        else if (Label[i]==8 && maxNumber == 8) matriksValid[8][8]++;
                        else if (Label[i]==8 && maxNumber == 9) matriksValid[8][9]++;
                        
                        else if(Label[i]==9 && maxNumber == 0)  matriksValid[9][0]++;
                        else if (Label[i]==9 && maxNumber == 1) matriksValid[9][1]++;
                        else if (Label[i]==9 && maxNumber == 2) matriksValid[9][2]++;
                        else if (Label[i]==9 && maxNumber == 3) matriksValid[9][3]++;
                        else if (Label[i]==9 && maxNumber == 4) matriksValid[9][4]++;
                        else if (Label[i]==9 && maxNumber == 5) matriksValid[9][5]++;
                        else if (Label[i]==9 && maxNumber == 6) matriksValid[9][6]++;
                        else if (Label[i]==9 && maxNumber == 7) matriksValid[9][7]++;
                        else if (Label[i]==9 && maxNumber == 8) matriksValid[9][8]++;
                        else if (Label[i]==9 && maxNumber == 9) matriksValid[9][9]++;
                        
                        k++;
                        customPanel2.uProgress(progress*k);
                        customPanel2.repaint();
                        String jj = String.valueOf(i);
                        lblProgress.setText(jj);
                        Thread.sleep(50);
                       }
                    accuracy = (sum_akurasi * 100)/sum_data;
                    System.out.println("\nAkurasi " + accuracy + "%");
                } catch (IOException ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblProgress.setText("Selesai");
                JOptionPane.showMessageDialog(null, "Proses Identifikasi Selesai");
                Form_Hasil_doTesting formMultiple = new Form_Hasil_doTesting(accuracy, output, matriksValid, testingSet, labelprediksi);
                formMultiple.setVisible(true);
                closeWindow(); 
            }
        }).start();
    }
    
    public void doTraining(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double rate;
                    int inc, i, epoch, sum_data;
                    boolean print;
                    
                    CNNController cnnTraining   = new CNNController(c1_Number, s2_Number,c3_Number,s4_Number, c5_Number, f6_Number, g7_Number, c1_Scale, c3_Scale, c5_Scale, convolution_Kernel_Scale, subsampling_Kernel_Scale);
                    numberImageMultiples        = new NumberImage[trainingSet.getTotal()];
                    
                    String[] fileName   = new String[12];
                    Random rng          = new Random(1234);
                    int[] standard      = new int[g7_Number];
                    fileName            = cnnTraining.set_getfilename_training(Path);
                    
                    Convolution c1 = new Convolution(c1_Number, c1_Scale, convolution_Kernel_Scale, status, fileName[0], fileName[1]);
                    Convolution c3 = new Convolution(c3_Number, c3_Scale, convolution_Kernel_Scale, status,  fileName[2], fileName[3]);
                    Convolution c5 = new Convolution(c5_Number, c5_Scale, convolution_Kernel_Scale, status,  fileName[4], fileName[5]);

                    Subsampling s2 = new Subsampling(s2_Number, s2_Scale, subsampling_Kernel_Scale);
                    Subsampling s4 = new Subsampling(s4_Number, s4_Scale, subsampling_Kernel_Scale);

                    FullyConnected f6 = new FullyConnected(f6_Number, c5_Number, status, fileName[6], fileName[7] );
                    FullyConnected g7 = new FullyConnected(g7_Number, f6_Number, status, fileName[8], fileName[9] );
                    
                    inc             = 1;
                    epoch           = 100;
                    sum_data        = trainingSet.getTotal();
                    //sum_data        = 100;
                    rate            = 0.01;
                    print           = false;
                    
                    while(inc<=epoch){
                        for(i=0;i<sum_data;i++){
                            //initial
                            
                            cnnTraining.initial(c1, s2, c3, s4, c5, f6, g7);

                            //Data Citra
                            System.out.println("\n\nData Image ke "+(i+1));
                            numberImageMultiples[i] = new NumberImage();
                            numberImageMultiples[i] = trainingSet.getNumberImage(i);
                            numberImageMultiples[i].setNumberImage();
                            numberImageMultiples[i].readPixel();

                            //forward
                            cnnTraining.forward(print, numberImageMultiples[i].getPixelImage(), c1, s2, c3, s4, c5, f6, g7);   
                        
                            //feedback
                            cnnTraining.feedback(i,print, standard , numberImageMultiples[i].getPixelImage(),c1, s2, c3, s4, c5, f6, g7);
                            
                            //renew bobot and bias
                            cnnTraining.renew_Adjust(rate, c1, c3, c5, f6,g7);
                        }
                        
                        //saving bobot and bias
                        cnnTraining.save_kernel_bias(Path, c1, c3, c5, f6,g7);
                        customPanel2.updateProgress(inc);
                        customPanel2.repaint();
                        String jj = String.valueOf(inc);
                        lblProgress.setText(jj);
                        Thread.sleep(50);
                        inc++;
                    }       
                } catch (IOException ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblProgress.setText("Selesai");
                JOptionPane.showMessageDialog(null, "Proses Training Selesai");
            }
        }).start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProgress = new javax.swing.JLabel();
        customPanel2 = new recognitionarabicnumeral.Util.CustomPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        lblProgress.setFont(new java.awt.Font("Caviar Dreams", 1, 16)); // NOI18N
        lblProgress.setForeground(new java.awt.Color(165, 48, 16));
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgress.setText("jLabel1");

        customPanel2.setOpaque(false);

        javax.swing.GroupLayout customPanel2Layout = new javax.swing.GroupLayout(customPanel2);
        customPanel2.setLayout(customPanel2Layout);
        customPanel2Layout.setHorizontalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );
        customPanel2Layout.setVerticalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(customPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        setSize(new java.awt.Dimension(262, 288));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void closeWindow(){
        this.dispose();
    }
        
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private recognitionarabicnumeral.Util.CustomPanel customPanel2;
    private javax.swing.JLabel lblProgress;
    // End of variables declaration//GEN-END:variables
}
