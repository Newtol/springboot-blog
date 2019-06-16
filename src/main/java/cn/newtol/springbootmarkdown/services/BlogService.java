package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentPraise;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @Author: 公众号：Newtol
 * @Description: 博客Service
 * @Date: Created in 11:57 2019/5/17
 * @params:
 */
@Service
public interface BlogService {
    /**
     * 获取所有的文章类型
     * @return 返回文章所有类型
     */
    ResultVO getAllContentType();

    /**
     * 保存新的文章类型
     * @return 返回是否上传成功
     */
    ResultVO saveContentType(ContentType contentType);

    /**
     * 保存文章
     * @param contentInfo
     * @return 返回连接
     */
    ResultVO saveContentInfo(ContentInfo contentInfo) throws FileNotFoundException;

    /**
     * 获取文章详细信息
     * @param contentId：文章ID
     * @return 返回文章信息
     */
    ResultVO getContent(String contentId) throws IOException;

    /**
     * 给文章点赞
     * @param contentPraise
     * @return 点赞是否成功、当前点赞数
     */
    ResultVO addPraise(ContentPraise contentPraise);

    /**
     * 获取文章点赞数
     * @param contentId：文章ID
     * @return: 点赞数
     */
    ResultVO getPraise(String contentId);

    /**
     * 记录文章访问量
     * @param contentId
     */
    void addReadNum(String contentId);


    /**
     * 获取站长推荐
     * @return：返回最近的5条文章
     */
    ResultVO getRecommendationList();


    /**
     * 获取阅读排行榜
     * @return
     */
    ResultVO getReadRankList();


    /**
     * 获取上一篇、下一篇文章
     * @return
     */
    ResultVO getNearContent(String createTime);

    /**
     * 获取文章列表
     * @return
     */
    ResultVO getContentList(Integer page);


    /**
     * 根据文章类型来获取文章列表
     * @param contentType
     * @return
     */
    ResultVO getContentListByContentType(String contentType,Integer page);




}
