/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recognitionarabicnumeral.Util;

/**
 *
 * @author dewyy
 */

import java.io.File;
import javax.swing.filechooser.*;

public class FileFilterBMP extends FileFilter{
	private String ImageFormat = "bmp";
	private char DotIndex = '.';

	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		if(extension(f).equalsIgnoreCase(ImageFormat)){
			return true;
		} else {
			return false;
		}
	}
	public String getDescription(){
		return " bmp formats Only";
	}
	public String extension(File f){
		String FileName = f.getName();
		int IndexFile = FileName.lastIndexOf(DotIndex);
		if(IndexFile > 0 && IndexFile < FileName.length()-1){
			return FileName.substring(IndexFile+1);
		} else {
			return " ";
		}
	}
}

