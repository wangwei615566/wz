package com.wz.cashloan.manage.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

/**
 * @author LM
 * @version V1.0
 * @Title: GZIPUtil.java
 * @Description: gzip文件压缩和解压缩工具类
 * @date 2009-11-4 下午06:23:29
 */
public class GZIPUtil {


    /**
     * @param sources 要打包的原文件数组
     * @param target  打包后的文件
     * @param hasPath 是否包含目录
     * @return File 返回打包后的文件
     * @throws
     * @Title: pack
     * @Description: 将一组文件打成tar包
     */
    public static File pack(File[] sources, File target, boolean hasPath) throws IOException {
        FileOutputStream out = null;
        FileInputStream in = null;
        TarArchiveOutputStream os = null;
        try {
            out = new FileOutputStream(target);
            os = new TarArchiveOutputStream(out);
            for (File file : sources) {
                File tmpFile = null;
                try {
                    if (!hasPath) {
                        String parent = file.getParent();
                        if (StringUtils.isNotEmpty(parent)) {
                            tmpFile = FileUtil.newTmpFile(file.getName());
                            fileToFile(file, tmpFile);
                            file = tmpFile;
                        }
                    }

                    os.putArchiveEntry(new TarArchiveEntry(file));
                    in = new FileInputStream(file);

                    IOUtils.copy(in, os);
                    os.closeArchiveEntry();

                } finally {
                    in.close();
                    if(tmpFile != null)tmpFile.delete();
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (os != null) {
                os.flush();
                os.close();

            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }

        }

        return target;
    }

    /**
     * @param source 需要压缩的文件
     * @return File 返回压缩后的文件
     * @throws
     * @Title: compress
     * @Description: 将文件用tar压缩
     */
    public static File compress(File target, File source) throws IOException {
        FileInputStream in = null;
        FileOutputStream fout = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(source);
            fout = new FileOutputStream(target);
            out = new GZIPOutputStream(fout);
            byte[] array = new byte[1024];
            int number = -1;
            while ((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
            if (fout != null) fout.close();
        }
        return target;
    }

    public static void compress(File target, boolean hasPath, File... sources) throws IOException {

        File packFile = pack(sources, new File(UUID.randomUUID().toString() + ".tar"), hasPath);
        compress(target, packFile);

        if(packFile != null)packFile.delete();

    }

    public static void fileToFile(File source, File target) throws IOException {

        //声明输入输出流
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(target);
            IOUtils.copy(in, out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输入输出流
            if (in != null) in.close();
            if (out != null) out.close();
        }

    }

    /***
     * 指定文件夹下的所有文件
     * @param path
     * @return
     */
    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

}