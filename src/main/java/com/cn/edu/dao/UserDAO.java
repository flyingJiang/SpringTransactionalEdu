package com.cn.edu.dao;

import com.cn.edu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jiangjianfei
 */
@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
