package cn.newtol.springbootblog.services;

import cn.newtol.springbootblog.entity.ResultVO;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:44 2019/7/7
 * @params:
 */
@Service
public interface AboutMeService {
    ResultVO getAboutMe() throws IOException;
}
