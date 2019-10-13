package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:14 2019/6/15
 * @params:
 */
public interface MessageInfoRepository extends JpaRepository<MessageInfo,Integer> {
    /**
     * 获取一级评论
     * @param contentId
     * @param pId
     * @return
     */
    List<MessageInfo> findAllByContentIdAndPIdOrderByCreateTimeDesc(String contentId,String pId);


    /**
     * 获取二级评论
     * @param pId：一级评论ID
     * @return
     */
    List<MessageInfo> findAllByPIdOrderByCreateTimeDesc(String pId);



}
