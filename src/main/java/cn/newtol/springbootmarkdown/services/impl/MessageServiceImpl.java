package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.MessageInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.repository.MessageInfoRepository;
import cn.newtol.springbootmarkdown.services.MessageService;
import cn.newtol.springbootmarkdown.utils.KeyWordUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:24 2019/6/15
 * @params:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageInfoRepository messageInfoRepository;

    @Resource
    private KeyWordUtil keyWordUtil;

    @Resource
    private ResumeUtil resumeUtil;

    @Override
    public ResultVO saveMsg(MessageInfo messageInfo) {
        // 对于评论内容进行过滤
        String content = keyWordUtil.getSafeString(messageInfo.getMessage());
        messageInfo.setMessage(content);

        // 获取评论ID
        String msgId = resumeUtil.getResumeIdByRandom(messageInfo.getMessage());
        messageInfo.setMsgId("a"+msgId);

        return ResultUtil.success(messageInfoRepository.save(messageInfo));
    }
    @Override
    public ResultVO getMsgByContentId(String contentId) {
        return ResultUtil.success(messageInfoRepository.findAllByContentIdAndPIdOrderByCreateTimeDesc(contentId,"0"));
    }

    @Override
    public ResultVO getMsgByPId(String pId) {
        return ResultUtil.success(messageInfoRepository.findAllByPIdOrderByCreateTimeDesc(pId));
    }
}
