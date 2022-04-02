package org.example.college.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.college.common.QiniuUtils;
import org.example.college.common.RedisConstant;
import org.example.college.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;


    /*
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info(file.toString());

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String filename = UUID.randomUUID().toString()+suffix;

        //创建一个文件目录
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()){
            //目录不存在
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success(filename);
    }
*/
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){

        String originalFilename = file.getOriginalFilename(); //原始文件名
        int index = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(index-1);

        String fileName = UUID.randomUUID().toString() + extention;

        //将文件上传到七牛云
        try {
            QiniuUtils.upload2Qiniu(file.getBytes(), fileName);
//            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(fileName);
    }
    /**
     * 文件下载
     * @param name
     * @param httpServletResponse
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse httpServletResponse){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            System.out.println(basePath+name);
            //输出流，通过输出流将文件写回到浏览器
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();

            httpServletResponse.setContentType("image/jpeg");
//            httpServletResponse.setContentType("text/plain");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes))!=0){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
