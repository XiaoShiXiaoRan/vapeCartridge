package com.fidnortech.xjx.article;


import com.fidnortech.xjx.common.ImageData;
import com.fidnortech.xjx.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class articleController {

    @PostMapping("/ImageData")
    public ResponseMessage addUser(@RequestParam MultipartFile file) {

        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = System.getProperty("user.dir") + "/upload/";
        //按照月份进行分类：
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1) + "";

//        dirPath = dirPath + month;

        System.out.println(dirPath);

        File filePath=new File(dirPath);
        if(!filePath.exists()) {
            filePath.mkdirs();
        }


        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();

            //获取最后一个.的位置
            int lastIndexOf = originalFilename.lastIndexOf(".");

            //获取文件的后缀名 .jpg
            String suffix = originalFilename.substring(lastIndexOf);
//                	System.out.println(suffix);

            //接收、校验逻辑请自行编写

            //suffix:前面代码获取的图片后缀名，结合uuid生成一个新的文件名称
            String filename = UUID.randomUUID() + suffix;

            //拼接路劲，保存文件
            String path = filePath+"/"+filename;
            try {
                file.transferTo(new File(path));
            }  catch (IOException e) {
                e.printStackTrace();
            }

            //new一个imageData保存前端所需要的数据
            ImageData image = new ImageData(filename);
            //返回数据
            return ResponseMessage.success(image);
        } else {
            return ResponseMessage.error("上传失败");
        }
    }


    @RequestMapping(value = "/image/{imageName}")
    public void downloadFile(@PathVariable String imageName, HttpServletRequest request, HttpServletResponse response) {

        try{
            response.setHeader("Content-Disposition","attachment;filename="+ new String(imageName.getBytes("UTF-8"),"ISO8859-1"));
            writeFile(response, new java.io.File("/home/data/upload/"+imageName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 写文件 方法
     *
     * @param response
     * @param file
     * @throws IOException
     */
    public void writeFile(HttpServletResponse response, java.io.File file) {
        byte[] buff = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            ServletOutputStream outputStream = response.getOutputStream();
            int i = bis.read(buff);
            while(i != -1){
                outputStream.write(buff, 0, i);
                i = bis.read(buff);
            }
            bis.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
