package cn.newtol.springbootmarkdown.controller;

import cn.newtol.springbootmarkdown.dao.MessageInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;
import cn.newtol.springbootmarkdown.services.MessageService;
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
 * @Date: Created in 15:16 2019/6/15
 * @params:
 */
@Controller
public class MessageController {
    @Resource
    private MessageService messageService;

    @Resource
    private ValidCodeImgUtil validCodeImgUtil;

    @PostMapping("/msg")
    @ResponseBody
    public ResultVO saveMsg(HttpServletRequest request,@Valid MessageInfo messageInfo){
        if(!validCodeImgUtil.isRightValidCode(request,messageInfo.getValidCode())){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }
        return messageService.saveMsg(messageInfo);
    }
    @GetMapping("/msg")
    @ResponseBody
    public ResultVO getMsgByContentId(@RequestParam String contentId){
        return messageService.getMsgByContentId(contentId);
    }

    @GetMapping("/msg2")
    @ResponseBody
    public ResultVO getMsgByPId(@RequestParam String pId){
        return messageService.getMsgByPId(pId);
    }
}
