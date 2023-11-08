/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.fidnortech.xjx.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * File工具类，扩展 hutool 工具包
 *
 * @author Zheng Jie
 * @date 2018-12-27
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 系统临时目录
     * <br>
     * windows 包含路径分割符，但Linux 不包含,
     * 在windows \\==\ 前提下，
     * 为安全起见 同意拼装 路径分割符，
     * <pre>
     *       java.io.tmpdir
     *       windows : C:\Users/xxx\AppData\Local\Temp\
     *       linux: /temp
     * </pre>
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static final String IMAGE = "图片";
    public static final String TXT = "文档";
    public static final String MUSIC = "音乐";
    public static final String VIDEO = "视频";
    public static final String OTHER = "其他";


    public static List<String> getListFiles(String path){
        if (path==null){
            return new ArrayList();
        }
        ArrayList<String> list = new ArrayList<>();
        for (File file : new File(path).listFiles()) {
            //如果是文件夹
            if (file.isDirectory()){
                list.addAll(getListFiles(file.getPath()));
            }else {
                list.add(file.getName());
                //下面是带有路径的写法
                //list.add(file.getPath());
            }
        }
        return list;
    }

 
    

    /**
     * 文件大小转换
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }


    /**
     * 拷贝文件
     *
     * @param path
     * @param file
     * @return
     */
    public static File copyFile(String path, MultipartFile file) throws IOException {
        File file1 = new File(path + file.getOriginalFilename());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return file1;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static String createPath(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        return path;
    }

    /**
     * 输入流转byte
     *
     * @author Administrator
     * @date 2022/4/21 17:04
     * @version 1.0
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static String createDir() {
        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String str = dataForm + File.separatorChar + FileUtil.getUUID() + File.separatorChar;
        return str;
    }



    /**
     * 创建文件
     *
     * @throws
     */
    public static  boolean creatTxtFile(String fileName, String value) throws IOException {
        boolean flag = false;

        File file = new File(fileName);

        //Java判断是否存在文件夹，不存在则新建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //判断是否存在文件，不存在则新建
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
        out.write(value);
        out.close();
        return flag;
    }

    /**
     * 创建文件
     *
     * @throws
     */
    public static  boolean creatTxtFile(String filePath, InputStream value) throws IOException {
        boolean flag = false;

        File file = new File(filePath);

        //Java判断是否存在文件夹，不存在则新建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //判断是否存在文件，不存在则新建
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedInputStream in = new BufferedInputStream(value);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        int len = -1;
        byte[] b = new byte[1024];
        while((len = in.read(b)) != -1){
            out.write(b, 0, len);
        }
        out.close();
        return flag;
    }



    private static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c == 0xfffd) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生产uuid
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /** 
     * 获取文件扩展名
     * @param filename xxxx.xxx
     * @return java.lang.String 
     * @author Administrator
     * @date 2022/7/11 16:51
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

    /**
     * 获取不带扩展名的文件名
     *
     * @param filename xxxx.xxx
     * @return java.lang.String
     * @author Administrator
     * @date 2022/7/11 16:41
     */
    public static String getFileNameNoExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String getFileNameWithoutSuffix(String fileName){
        int suffixIndex = fileName.lastIndexOf('.');
        if(suffixIndex<0)
            return fileName;
        return fileName.substring(0, suffixIndex);
    }

    public static String getFileSuffix(String fileName){
        int suffixIndex = fileName.lastIndexOf('.');
        if(suffixIndex<0)
            return "";
        return fileName.substring(suffixIndex+1);
    }

    public static String getSegmentName(String fileName, int segmentIndex){
        return fileName + "#" + segmentIndex;
    }

    public static String createSaveFileName(String key, String fileName){
        String suffix = getFileSuffix(fileName);
        return key + "." + suffix;
    }

    public static String createUUIDFileName(String fileName){
//        String suffix = getFileSuffix(fileName);
//        String name = UUID.randomUUID().toString();
//        return name + "." + suffix;
        return fileName;
    }

    public static void main(String[] args) {
        String extensionName = getExtensionName("ssss.pdf");
        System.out.println(extensionName);
    }
}
