/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author dewyy
 */

public class NumberImage {
    private String name = "";
    private int label = 0;
    private String filePath = "";
    private BufferedImage retinaImage;
    private double[][] pixelImage;
    private int height;
    private int width;
    
    public NumberImage(){
        
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPath(String filePath){
        this.filePath = filePath;
    }
    public void setLabel(int label){
        this.label = label;
    }

    
    public int getLabel() {
        return label;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setHeight() {
        this.height = retinaImage.getHeight();
    }

    public void setWidth() {
        this.width = retinaImage.getWidth();
    }
    
    public String getImageName(){
        return name;
    }

    public void setNumberImage() {
        try {
            File file = new File(filePath);
            retinaImage = ImageIO.read(file);
            setHeight();
            setWidth();
            //System.out.println("Citra "+file.getName()+" terbaca");
        } catch (IOException ex) {
            Logger.getLogger(NumberImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getNumberImage(){
        return retinaImage;
    }

    public void readPixel() {
        pixelImage = new double[retinaImage.getHeight()][retinaImage.getWidth()];
        for (int x = 0; x < retinaImage.getWidth(); x++) {
            for (int y = 0; y < retinaImage.getHeight(); y++) {
                Color c = new Color(retinaImage.getRGB(x, y));
                int rgb = c.getRGB();
                int pixel = (c.getRed()+c.getGreen()+c.getBlue()/3);
                pixelImage[y][x]= pixel/255;
                //System.out.println("pixelImage["+y+"]["+x+"] = " +pixelImage[y][x]);
            }
        }
        //System.out.println("pixelImage[128][128] = " +pixelImage[128][128]);
            
    }

    public double[][] getPixelImage() {
        return pixelImage;
    }

    public BufferedImage viewImage(int[][] piksel) {
        BufferedImage view = new BufferedImage(piksel[0].length, piksel.length, BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < piksel.length; i++) {
            for (int j = 0; j < piksel[i].length; j++) {
                if (piksel[i][j] == 0) {
                    int value = 0;
                    Color newColor = new Color(value, value, value);
                    view.setRGB(j, i, newColor.getRGB());
                } else {
                    int value = 255;
                    Color newColor = new Color(value, value, value);
                    view.setRGB(j, i, newColor.getRGB());
                }
            }
        }
        return view;
    }

    public BufferedImage resize(BufferedImage image, int newHeight, int newWidth) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2D = resizedImage.createGraphics();

        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
        g2D.dispose();

        return resizedImage;
    }
}
