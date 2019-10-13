package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.MessageInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.repository.MessageInfoRepository;
import cn.newtol.springbootblog.services.MessageService;
import cn.newtol.springbootblog.utils.KeyWordUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
import cn.newtol.springbootblog.utils.ResumeUtil;
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
