package cn.newtol.springbootblog.services;

import cn.newtol.springbootblog.dao.SoupInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import org.springframework.stereotype.Service;

import java.io.IOException;

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


    /**
     * 获取开放API文档
     * @return
     */
    ResultVO getOpenApiContent() throws IOException;




}
