package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;


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

}
