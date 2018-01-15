package com.weix.api;

import com.weix.commons.DateUtils;
import com.weix.service.domain.SysUser;
import com.weix.service.repository.SysUserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WexApiApplicationTests {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void createUser()throws Exception{
        SysUser user = new SysUser();
        user.setUserName("zhangguangxi");
        user.setName("张光喜");
        user.setPassword(DigestUtils.md5Hex("123456"));
        user.setUserType("8");
        user.setStatus(1);
        user.setWeixin(0);
        user.setArea("440105");
        user.setCreateTime(DateUtils.getYmdHms());
        sysUserRepository.save(user);
    }
}
