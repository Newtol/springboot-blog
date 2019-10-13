package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.GuestBookInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.repository.GusetBookInfoRepository;
import cn.newtol.springbootblog.services.GuestBookInfoService;
import cn.newtol.springbootblog.utils.KeyWordUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
import cn.newtol.springbootblog.utils.ResumeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:45 2019/6/16
 * @params:
 */
@Service
public class GuestBookInfoServiceImpl implements GuestBookInfoService {
    @Resource
    private GusetBookInfoRepository gusetBookInfoRepository;

    @Resource
    private KeyWordUtil keyWordUtil;

    @Resource
    private ResumeUtil resumeUtil;

    @Override
    public ResultVO saveGuestBook(GuestBookInfo guestBookInfo) {
        // 留言过滤
        String message = keyWordUtil.getSafeString(guestBookInfo.getMessage());
        guestBookInfo.setMessage(message);
        // 设置MsgId
        String msgId = resumeUtil.getResumeIdByRandom(guestBookInfo.getMessage());
        guestBookInfo.setMsgId("b"+msgId);
        return ResultUtil.success(gusetBookInfoRepository.save(guestBookInfo));
    }

    @Override
    public ResultVO getGuestBookList() {
        return ResultUtil.success(gusetBookInfoRepository.findAllByPIdOrderByCreateTimeDesc("0"));
    }

    @Override
    public ResultVO getGuestBookByPid(String pId) {
        return ResultUtil.success(gusetBookInfoRepository.findAllByPIdOrderByCreateTimeDesc(pId));
    }
}
