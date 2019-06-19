package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.SoupInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:48 2019/6/19
 * @params:
 */
@Service
public interface ApiService {
    /**
     * 随机获取毒鸡汤
     * @return：随机回复一条
     */
    ResultVO getSoup();


    /**
     *  保存毒鸡汤
     * @return
     */
    ResultVO saveSoup(SoupInfo soupInfo);




}
