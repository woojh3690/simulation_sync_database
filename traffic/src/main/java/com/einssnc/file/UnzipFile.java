package com.einssnc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import com.einssnc.updater.realtime.NationWideUpdater;

public class UnzipFile {
	
    public void unzip(String fileZipDir, String fileName, String destinationDir, Calendar date) {
    	String fileZip = fileZipDir + fileName;
    	
    	FileOutputStream fos = null;
    	ZipInputStream zis = null;
    	
    	try {
	        File destDir = new File(destinationDir);
	        byte[] buffer = new byte[1024];
	        zis = new ZipInputStream(new FileInputStream(fileZip));
	        ZipEntry zipEntry = zis.getNextEntry();
	        
	        System.out.println("Unzipping \"" + fileName + "\"...");
	        while (zipEntry != null) {
	            File newFile = newFile(destDir, zipEntry);
	            fos = new FileOutputStream(newFile);
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
	            }
	            fos.close();
	            zipEntry = zis.getNextEntry();
	        }
	        
	        System.out.println("Unzipping Complete!");
    	} catch (ZipException e) {
    		System.out.println("ZipException 발생. 다시 다운로드 시도");
    		NationWideUpdater updater = new NationWideUpdater(fileZipDir, fileName);
    		updater.insertOneDate(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			zis.closeEntry();
    	        zis.close();
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
    }
     
    public File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
}