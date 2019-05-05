package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ResumeInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:43 2019/5/1
 * @params:
 */

public interface ResumeRepository extends JpaRepository<ResumeInfo,Integer>{
}
