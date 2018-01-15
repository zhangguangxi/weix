package com.weix.commons;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Enumeration;

/**
 * 压缩工具类
 */
public class CompressUtils {

    /**
     * 压缩
     * @param source 文件或目录
     * @param zipOut 压缩输出流
     * @param zipEntryName 压缩文件名称
     * @throws IOException
     */
    private static  void compress(File source, ZipArchiveOutputStream zipOut,String zipEntryName) throws IOException{
        byte[] buff = new byte[512];
        int readLen = -1;
        if(source.isFile()){
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(zipEntryName);
            zipOut.putArchiveEntry(zipArchiveEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            while((readLen = bis.read(buff))!=-1){
                zipOut.write(buff,0,readLen);
            }
            zipOut.closeArchiveEntry();
            IOUtils.closeQuietly(bis);
        }else if(source.isDirectory()){
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(zipEntryName.concat(File.separator));
            zipOut.putArchiveEntry(zipArchiveEntry);
            zipOut.closeArchiveEntry();
            File[] files = source.listFiles();
            for (File file : files) {
                compress(file,zipOut,zipEntryName+File.separator+file.getName());
            }
        }
    }

    /**
     * 解压缩
     * @param source 源(压缩文件)
     * @param target 解压缩后输出文件
     * @throws Exception
     */
    public  static  void unzip(File source,File target)throws Exception{
        if(!source.isFile()){
            throw  new IllegalArgumentException("The source must be a file");
        }

        if(!target.isDirectory()){
            throw new IllegalAccessException("The target must be a directory");
        }

        //解压后文件的名称
        ZipFile zipFile = new ZipFile(source);
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        while (entries.hasMoreElements()){
            ZipArchiveEntry zipArchiveEntry = entries.nextElement();
            if(zipArchiveEntry.isDirectory()){
                FileUtils.forceMkdir(new File(target,zipArchiveEntry.getName()));
            }else{
                IOUtils.copy(zipFile.getInputStream(zipArchiveEntry),new FileOutputStream(new File(target,zipArchiveEntry.getName())));
            }
        }
        zipFile.close();
    }
}
