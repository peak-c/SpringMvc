package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import util.ImageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 解决图片放置与磁盘。。。。未实现
 */
@Controller
public class ImgController {

    @RequestMapping("student/image/{name}")
    public void image(@PathVariable("name") String name,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        String path = request.getServletContext().getRealPath("/upload");
        String fileName = ImageUtil.findFilePathByFileName(path,name);

        fileName.replaceAll("/","\\");
        System.out.println("findFilePathByFileName:"+fileName);
        FileInputStream in = new FileInputStream(fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();

    }
}
