package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.SoupInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.repository.SoupInfoRepository;
import cn.newtol.springbootmarkdown.services.ApiService;
import cn.newtol.springbootmarkdown.utils.RedisUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:49 2019/6/19
 * @params:
 */
@Service
public class ApiServiceImpl implements ApiService {
    @Resource
    private SoupInfoRepository soupInfoRepository;

    @Resource
    private ResumeUtil resumeUtil;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 毒鸡汤ID存储的set的KEY
     */
    @Value("${defaultKey.SOUP_KEY}")
    private String SOUP_KEY;

    @Override
    public ResultVO getSoup() {
        String id = redisUtil.srandMember(SOUP_KEY);
        return ResultUtil.success(soupInfoRepository.findByContentId(id));
    }

    @Override
    public ResultVO saveSoup(SoupInfo soupInfo) {
        // 随机获取ID
        String id = resumeUtil.getResumeIdByRandom(soupInfo.getContent());
        // 设置ID,并存在Redis的set中
        soupInfo.setContentId(id);
        redisUtil.sadd(SOUP_KEY,id);
        // 保存至数据库并返回
        return ResultUtil.success(soupInfoRepository.save(soupInfo));
    }
}
