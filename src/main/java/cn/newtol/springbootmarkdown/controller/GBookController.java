package cn.newtol.springbootmarkdown.controller;

import cn.newtol.springbootmarkdown.dao.GuestBookInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;
import cn.newtol.springbootmarkdown.services.GuestBookInfoService;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ValidCodeImgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:13 2019/6/16
 * @params:
 */
@Controller
public class GBookController {
    @Resource
    private GuestBookInfoService guestBookInfoService;

    @Resource
    private ValidCodeImgUtil validCodeImgUtil;

    /**
     * 保存留言
     * @param request
     * @param guestBookInfo
     * @return
     */
    @PostMapping("/guestBook")
    @ResponseBody
    public ResultVO saveGusetBook(HttpServletRequest request,@Valid GuestBookInfo guestBookInfo){
        if(!validCodeImgUtil.isRightValidCode(request,guestBookInfo.getValidCode())){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }
        return guestBookInfoService.saveGuestBook(guestBookInfo);
    }

    /**
     * 获取留言（一级留言）
     * @return
     */
    @GetMapping("/guestBook")
    @ResponseBody
    public ResultVO getGuestBook(){
        return guestBookInfoService.getGuestBookList();
    }

    /**
     * 获取站长回复
     * @param pId
     * @return
     */
    @GetMapping("/guestBook2")
    @ResponseBody
    public ResultVO getGuestBookByPId(@RequestParam String pId){
        return guestBookInfoService.getGuestBookByPid(pId);
    }


}
