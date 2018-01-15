package com.weix.service.repository;

import com.weix.service.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByWeixinId(String weixinId);

    SysUser findByUserName(String userName);

    SysUser findByUserNameAndPassword(String userName, String password);

    @Transactional
    @Modifying
    @Query("update SysUser e set e.weixin=?3,e.weixinId=?2 where e.id=?1")
    void updateWeixinStatus(Long id, String weixinId, Integer weixinStatus);
}
