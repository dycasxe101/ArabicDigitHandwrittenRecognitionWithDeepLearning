/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.RunningConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Random;
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
public class Training {
    
    static final String path = "D:\\Numpang\\TA Baru\\Klasifikasi\\ArabicNumber";
    static final File dir = new File(path+"\\Data Citra\\Training");
     static int  t0_Number = 1, 
                c1_Number = 6, 
                s2_Number = 6, 
                c3_Number = 16, 
                s4_Number = 16, 
                c5_Number = 120,  
                f6_Number = 84,
                g7_Number = 10;
    
    
    static int  t0_Scale = 32, 
                c1_Scale = 28, 
                s2_Scale = 14, 
                c3_Scale = 10, 
                s4_Scale = 5, 
                c5_Scale = 1;
    
    static int  convolution_Kernel_Scale    = 5, 
                subsampling_Kernel_Scale    = 2;
    static final String[] EXTENSIONS = new String[]{
        "bmp" // and other formats you need
    };
    
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

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
    
    public static void data() throws FileNotFoundException, IOException{
        NumberImage[] numberImages;
       
        String namaFile = path+"\\Data Citra\\LabelTraining.txt";
        
        int sumfile = dir.list().length;
        int[] Label = new int[sumfile];

        String line;
        StringBuffer temp = new StringBuffer();
        File fileBobot = new File(namaFile);        
        
        FileReader in = new FileReader(fileBobot);
        BufferedReader dis = new BufferedReader(in);

        for (int i=0; i<sumfile; i++)
            if((line = dis.readLine()) != null){
                Label[i] =Integer.parseInt(line);
            }
        in.close();
        
        if (dir.isDirectory()) { // make sure it's a directory
            int i = 0;
            numberImages = new NumberImage[sumfile];
                
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                numberImages[i] = new NumberImage();
                numberImages[i].setName(f.getName());
                numberImages[i].setPath(f.getPath());
                numberImages[i].setLabel(Label[i]);

                trainingSet.addNumberImage(numberImages[i], f.getName(), f.getPath(), Label[i] );
                i++;
            }
            //trainingSet.tampilList();
        }
    }
    
    
    static DataSet trainingSet = new DataSet();
    public static void main(String[] args) throws IOException {
        data();
        double rate;
        int inc, i, epoch, sum_data, status=2;
        boolean print = false;
        Random rng          = new Random(1234);
        String[] fileName   = new String[12];
        int[] standard      = new int[g7_Number];
        CNNController cnnTraining   = new CNNController(c1_Number, s2_Number,c3_Number,s4_Number, c5_Number, f6_Number, g7_Number, c1_Scale, c3_Scale, c5_Scale, convolution_Kernel_Scale, subsampling_Kernel_Scale);
        
        if(status == 2){
            fileName = cnnTraining.set_getfilename_training(path); 
        }else{
            fileName = cnnTraining.set_getfilename_testing(path);
        }
        Convolution c1 = new Convolution(c1_Number, c1_Scale, convolution_Kernel_Scale, status, fileName[0], fileName[1]);
        Convolution c3 = new Convolution(c3_Number, c3_Scale, convolution_Kernel_Scale, status,  fileName[2], fileName[3]);
        Convolution c5 = new Convolution(c5_Number, c5_Scale, convolution_Kernel_Scale, status,  fileName[4], fileName[5]);

        Subsampling s2 = new Subsampling(s2_Number, s2_Scale, subsampling_Kernel_Scale);
        Subsampling s4 = new Subsampling(s4_Number, s4_Scale, subsampling_Kernel_Scale);

        FullyConnected f6 = new FullyConnected(f6_Number, c5_Number, status, fileName[6], fileName[7] );
        FullyConnected g7 = new FullyConnected(g7_Number, f6_Number, status, fileName[8], fileName[9] );
        
        NumberImage[] numberImagesMultiple = new NumberImage[trainingSet.getTotal()];
              
        inc         = 125; 
        epoch       = 150; 
        sum_data    = trainingSet.getTotal();
        //sum_data    = 2;
        rate        = 0.001;
        PrintStream out = new PrintStream(new FileOutputStream(path+"\\Bobot dan Bias\\Testing\\output "+inc+" - "+ epoch +".txt"));
        System.setOut(out);
        System.out.println("Training Data Number Arab "+sum_data);
        System.out.println("Saving per 10 section, rate 0.001");
        while(inc<=epoch){
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
            System.out.println(" Epoch "+inc+ " timestamp1 "+ timestamp1);
            
            for(i=0;i<sum_data;i++){
                //initial
                cnnTraining.initial(c1, s2, c3, s4, c5, f6, g7);
                
                 //Data Citra
                if((i+1) < 11) System.out.println("Data Image ke "+(i+1));
                else if ((i+1)> 59990 ) System.out.println("Data Image ke "+(i+1));
                numberImagesMultiple[i] = new NumberImage();
                numberImagesMultiple[i] = trainingSet.getNumberImage(i);
                numberImagesMultiple[i].setNumberImage();
                numberImagesMultiple[i].readPixel();
                
                for (int ii = 0; ii < g7_Number; ii++) {
                    if (ii == numberImagesMultiple[i].getLabel()){
                        standard[ii] = 1;
                    }    
                    else
                        standard[ii] = 0;
                    //System.out.println("Standar "+ standard[ii]);
                }
                
                //forward
                cnnTraining.forward(print, numberImagesMultiple[i].getPixelImage(), c1, s2, c3, s4, c5, f6, g7);   

                //feedback     
                cnnTraining.feedback((i+1), print, standard , numberImagesMultiple[i].getPixelImage(), c1, s2, c3, s4, c5, f6, g7);
                
                //renew bobot and bias
                if((i+1)%10==0)
                cnnTraining.renew_Adjust(rate, c1, c3, c5, f6, g7);
            
            }
            
            //saving bobot and bias
            cnnTraining.save_kernel_bias(path, c1, c3, c5, f6, g7);
            
            inc++;
        }
        
    }
}
