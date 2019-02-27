/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.Util;

/**
 *
 * @author Dewy Yuliana
 */
public class ActivationDeactivation {
        
    public static double drelu(double x){
        if (x<=0) return 0;
        return 1;
    }
    
    public static double relu(double x){
       if (x<=0) return 0;
       return x;
    }
    
    public static double dleakyrelu(double x){
        if (x<=0) return 0.5;
        return 1;
    }
    
    public static double leakyrelu(double x){
        if (x<=0) return x*0.5;
        return x;
    }
    
    public static double tanh(double x) {
        return Math.tanh(x);
    }

        public static double dtanh(double x) {
        double d=1.7159*0.667*(1-Math.pow(tanh(0.667*x), 2));
        return d;
    }
        
    public static double RandomFan_In(int kernel){
        double start = (double) ((-2.4) / kernel);
        double end = (double) (2.4 / kernel);
        double range=(end-start);
        double rrandom=(double) ((double) (Math.random()*range) + start);
        return (rrandom);
    }
}
