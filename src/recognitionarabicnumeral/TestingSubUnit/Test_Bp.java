package recognitionarabicnumeral.TestingSubUnit;

import recognitionarabicnumeral.Controller.Convolution;
import recognitionarabicnumeral.Controller.FullyConnected;

public class Test_Bp {

    public static void main(String[] args) {
        int rate = 1;
        int c5_Number = 5, f6_Number = 3, g7_Number = 2, c5_Scale = 1;
        int convolution_Kernel_Scale = 2;
        Convolution c5 = new Convolution(c5_Number, c5_Scale, convolution_Kernel_Scale);
        FullyConnected f6 = new FullyConnected(f6_Number, c5_Number);
        FullyConnected g7 = new FullyConnected(g7_Number, f6_Number);
        for (int test = 0; test < 1000; test++) {
            c5.initial();
            f6.initial();
            g7.initial();
            c5.set_Layer();
            for (int i = 0; i < f6_Number; i++) {
                for (int j = 0; j < c5_Number; j++) {
                    System.out.println("fm c5  " + c5.get_Layer(j)[0][0]);
                    f6.set_Layer(i, c5.get_Layer(j)[0][0], j, "F6");
            
                }
            }
            f6.addBiasRelu("F6");
            for (int i = 0; i < g7_Number; i++) {
                for (int j = 0; j < f6_Number; j++) {
                    g7.set_Layer(i, f6.get_Layer(j), j,"F8");  
                }
            }
            g7.addBiasRelu("G7");
            g7.softMax();
            for (int i = 0; i < c5_Number; i++) System.out.print(c5.get_Layer(i)[0][0] + " ");
            System.out.println();
            for (int i = 0; i < f6_Number; i++) System.out.print(f6.get_Layer(i) + " ");
            System.out.println();
            for (int i = 0; i < g7_Number; i++) System.out.print(g7.get_Layer(i) + " ");
            System.out.println();
            System.out.println();
            System.out.println("feedback");
            int[] standard = new int[2];
            standard[0] = 1;
            standard[1] = 0;
            int printout=0;
            g7.set_Layer_Adjust(standard, test);
            for (int i = 0; i < g7_Number; i++) {
                for (int j = 0; j < f6_Number; j++) {
                    g7.set_Weight_Adjust(printout, "G7", j, i, f6.get_Layer(j));
                }
                g7.set_Bias_Adjust(printout, "G7", i);
            }
            for (int i = 0; i < g7_Number; i++) for (int j = 0; j < f6_Number; j++) {
                 f6.set_Layer_Adjust(printout, "F6", j, g7.get_Layer_Adjust(i), g7.get_Layer(i), g7.get_Weight(j, i));
            }
            for (int i = 0; i < f6_Number; i++) {
                for (int j = 0; j < c5_Number; j++) {
                    f6.set_Weight_Adjust(printout, "F6", j, i, c5.get_Layer(j)[0][0]);
                }
                f6.set_Bias_Adjust(printout, "F6", i);
            }
            for (int i = 0; i < f6_Number; i++) for (int j = 0; j < c5_Number; j++) {
                c5.set_Layer_Adjust_Bp(printout, j, f6.get_Layer_Adjust(i),f6.get_Layer(i),f6.get_Weight(j, i));
            }
            c5.renew_Adjust(rate);
            f6.renew_Adjust(rate);
            g7.renew_Adjust(rate);
        }
    }
}
