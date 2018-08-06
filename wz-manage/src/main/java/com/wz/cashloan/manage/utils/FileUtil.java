package com.wz.cashloan.manage.utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

@SuppressWarnings("deprecation")
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 修改文件名，添加后缀.done
     *
     * @param file
     */
    public static void renameTo(String basePath, File file) {

        String newName = file.getName() + ".done";

        file.renameTo(new File(basePath + "/" + newName));
    }

    /**
     * 修改文件名，删除后缀.done
     *
     * @param basePath
     * @param file
     */
    public static void removeTo(String basePath, File file) {

        String fileName = file.getName();

        if (fileName.endsWith(".done")) {
            file.renameTo(new File(basePath + "/" + fileName.substring(0, fileName.indexOf(".done"))));
        }
    }


    /**
     * 获取 或创建文件
     *
     * @param path
     * @return
     */
    public static File getFile(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        File file = new File(path);
        try {

            if (file.exists()) {
                return file;
            } else {
                String logDirPath = file.getParent();

                if (StringUtils.isNotEmpty(logDirPath)) {
                    File logDir = new File(logDirPath);
                    if (!logDir.exists()) {
                        logDir.mkdirs();
                    }
                }

                file.createNewFile();

                return file;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }


    /**
     * 写入log 文件
     *
     * @param insertLog
     * @param file
     * @param append
     * @throws IOException
     */
    public static File createLog(String insertLog, File file, boolean append) throws IOException {

        // sync log
        if (insertLog != null) {
            String separtor = getSystemLineSeparator();
            FileUtils.write(file, insertLog + separtor, "UTF-8", append);

        }
        return file;
    }


    /**
     * 获取当前系统换行符
     *
     * @return 系统换行符
     */
    public static String getSystemLineSeparator() {
        return System.getProperty("line.separator");
    }


    public static void printFileLog(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);//要读的文件路径
        InputStreamReader isr = new InputStreamReader(fis);//字符流

        BufferedReader br = new BufferedReader(isr);    //缓冲
        String line = null;

        while ((line = br.readLine()) != null) {//字符不等于空
            System.out.println(line);//一行一行地输出
        }
        br.close();//关闭文件
    }

    public static File newTmpFile(String fileName) throws IOException {
        File file = new File(fileName);
        //删除原文件
        if (file.exists()) {
            if (file.isFile())
                deleteFile(fileName);
            else
                deleteDirectory(fileName);
        }
        return file;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file){
        String fileName = file.getName();
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                logger.warn("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            logger.warn("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    public static void printFileLength(File f) {
        if (f.exists() && f.isFile()){
            logger.info(f.length()+"");
        }else{
            logger.info("file doesn't exist or is not a file");
        }
    }
    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            logger.warn("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            logger.warn("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            logger.warn("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }


    /*
 * Java文件操作 获取文件扩展名
 *
 *  Created on: 2011-8-2
 *      Author: blueeagle
 */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    /*
     * Java文件操作 获取不带扩展名的文件名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
