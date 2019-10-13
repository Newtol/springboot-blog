package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Author: 公众号：Newtol
 * @Description: 文件上传
 * @Date: Created in 21:35 2019/5/7
 * @params:
 */
@Controller
public class UploadFileController {
    /**
     * @param img :上传的图片
     * @return data: 上传地址
     * @throws IOException
     */
    @PostMapping("/upload/img")
    @ResponseBody
    public ResultVO uploadImg(@RequestParam(value = "editormd-image-file") MultipartFile img) throws IOException {
        String pathname = "upload";
        File uploadDir = new File(pathname);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        byte[] data = img.getBytes();
        String realPath = pathname + "/" + img.getOriginalFilename();
        File dest = new File(realPath);

        try {
            FileImageOutputStream fileImageOutputStream  = new FileImageOutputStream(dest);
            fileImageOutputStream.write(data);
            fileImageOutputStream.close();
            //保存文件
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return ResultUtil.success("../"+realPath);
    }
}
