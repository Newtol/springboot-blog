package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.MessageInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:22 2019/6/15
 * @params:
 */
@Service
public interface MessageService {
    /**
     * 保存评论
     * @param messageInfo
     * @return
     */
    ResultVO saveMsg(MessageInfo messageInfo);

    /**
     * 获取一级评论
     * @param contentId
     * @return
     */
    ResultVO getMsgByContentId(String contentId);

    /**
     * 获取二级评论
     * @param pId:二级评论的父亲ID
     * @return
     */
    ResultVO getMsgByPId(String pId);
}
