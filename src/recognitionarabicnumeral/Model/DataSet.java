package recognitionarabicnumeral.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dewyy
 */
public class DataSet {
    List<NumberImage> listNumberImagesFile;
    List<String> listPathRetinaImagesFile;
    List<String> listNamaRetinaImagesFile;
    List<Integer> listLabelRetinaImagesFile;
    String namaKelasRetina;

    public DataSet() {
        listNumberImagesFile        = new ArrayList<>();
        listNamaRetinaImagesFile    = new ArrayList<>();
        listPathRetinaImagesFile   = new ArrayList<>();
        listLabelRetinaImagesFile       = new ArrayList<>();
        //tampilList();
    }
    
    public String getNamaKelasRetina(){
        return namaKelasRetina;
    }
    
    public void addNumberImage(NumberImage rImage, String namaFile, String Path, int Label){
       //rImage = new RetinaImage(namaFile,Path);
        listNumberImagesFile.add(rImage);
        listPathRetinaImagesFile.add(Path);
        listNamaRetinaImagesFile.add(namaFile);
        listLabelRetinaImagesFile.add(Label);
        //System.out.println("List Set");
        //tampilList();
        
    }
    
    public NumberImage getNumberImage(int index){
        return listNumberImagesFile.get(index);
    }
    
    public String getNamaFile(int index){
        return listNamaRetinaImagesFile.get(index);
    }
    
    public int getLabelFile(int index){
        return listLabelRetinaImagesFile.get(index);
    }
    
    public String getPath(int index){
        return listPathRetinaImagesFile.get(index);
    }
    
    public void tampilList(){
        System.out.print("List nama File : {");
        for(String listNama : listNamaRetinaImagesFile){
            System.out.println(listNama+" ");
        }
        System.out.println("}");
        
        
        System.out.print("List Label File : {");
        for(int listLabel : listLabelRetinaImagesFile){
            System.out.println(listLabel+" ");
        }
        System.out.println("}");
        
        System.out.print("List Path : {");
        for(String listPathRetinaImagesFileFile : listPathRetinaImagesFile){
            System.out.print(listPathRetinaImagesFileFile+" ");
        }
        System.out.print("}");
        int total = getTotal();
        System.out.println("Total Gambar = "+total);
    }
    
    public void deleteTrainingImage(int index){
        listNumberImagesFile .remove(index);
        listNamaRetinaImagesFile.remove(index);
        listPathRetinaImagesFile.remove(index);
    }
    
    public int getTotal(){
        return listNumberImagesFile.size();
    } 
    
}
