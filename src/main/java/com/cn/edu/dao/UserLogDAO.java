package com.cn.edu.dao;

import com.cn.edu.domain.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jiangjianfei
 */
@Repository
public interface UserLogDAO extends JpaRepository<UserLog, Long> {
}
