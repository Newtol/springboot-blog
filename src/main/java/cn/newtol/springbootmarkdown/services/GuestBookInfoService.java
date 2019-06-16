package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.GuestBookInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:45 2019/6/16
 * @params:
 */
@Service
public interface GuestBookInfoService {
    /**
     * 保存留言
     * @param guestBookInfo
     * @return
     */
    ResultVO saveGuestBook(GuestBookInfo guestBookInfo);


    /**
     * 获取留言
     * @return
     */
    ResultVO getGuestBookList();

    /**
     * 获取站长回复
     * @param pId：留言ID
     * @return
     */
    ResultVO getGuestBookByPid(String pId);
}
