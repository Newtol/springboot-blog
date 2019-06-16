package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.GuestBookInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.repository.GusetBookInfoRepository;
import cn.newtol.springbootmarkdown.services.GuestBookInfoService;
import cn.newtol.springbootmarkdown.utils.KeyWordUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
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
