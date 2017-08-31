package com.shadowfyrestudios.fyreengine.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileManager {

	public static Object desSerializeClass(String path)
	{
		Object o = null;
		
		try {
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         o =  in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	      }catch(ClassNotFoundException c) {
	         System.out.println("Class not found");
	      }
		
		return o;
		
	}
	
	public static void serializeClass(String path, Object _class)
	{
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream(path);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(_class);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in " + path);
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public static void writeToFile(String path, String data)
	{
		try {
			PrintWriter out = new PrintWriter(path);
			out.print(data);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFileToEnd(String path){
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			
			try {
				fis.read(data);
			} catch (IOException e1) {
				e1.printStackTrace();
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			String str = new String(data, "UTF-8");
			
			return str;
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void zipFile(String[] files, String outputFile)
	{
		byte[] buffer = new byte[1024];

    	try{

    		FileOutputStream fos = new FileOutputStream(outputFile);
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		
    		for(String s : files)
    		{
    			ZipEntry ze = new ZipEntry(new File(s).getName());
        		zos.putNextEntry(ze);
        		
        		FileInputStream in = new FileInputStream(s);

        		int len;
        		while ((len = in.read(buffer)) > 0) {
        			zos.write(buffer, 0, len);
        		}

        		in.close();
        		zos.closeEntry();
        		
    		}

    		//remember close it
    		zos.close();
    		
    		System.out.println("Done");

    	}catch(IOException ex){
    	   ex.printStackTrace();
    	}
	}
	
	public static void unzipFile(String zipFile, String outputFolder){
		
		byte[] buffer = new byte[1024];

	     try{

	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}

	    	//get the zip file content
	    	ZipInputStream zis =
	    		new ZipInputStream(new FileInputStream(zipFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();

	    	while(ze!=null){

	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);

	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();

	            FileOutputStream fos = new FileOutputStream(newFile);

	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }

	            fos.close();
	            ze = zis.getNextEntry();
	    	}

	        zis.closeEntry();
	    	zis.close();

	    	System.out.println("Done");

	    }catch(IOException ex){
	       ex.printStackTrace();
	    }
		
	}
	
}

