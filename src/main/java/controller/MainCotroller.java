package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@Controller
public class MainCotroller {
    @RequestMapping(value = "/img.do",produces = "image/png")
    @ResponseBody
    public byte[] downImage(HttpServletResponse response,HttpServletRequest  request) throws IOException {
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"demo.png\"");
        FileInputStream fin=new FileInputStream(new File(request.getServletContext().getRealPath("/001.png")));
        byte[] imgs=new byte[fin.available()];
        fin.read(imgs);
        return imgs;
    }
    @RequestMapping(value = "/upImg.do",method = RequestMethod.POST)

    public String  upImg(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException {

        String dir=request.getServletContext().getRealPath("/dir");
        File dirFile=new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        System.out.println(dir);
       file.transferTo(new File(dir,"img.png"));
       return "redirect:result.html";
    }
}
