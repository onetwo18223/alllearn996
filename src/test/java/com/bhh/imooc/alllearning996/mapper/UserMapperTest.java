package com.bhh.imooc.alllearning996.mapper;

import com.bhh.imooc.alllearning996.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-09 13:39
 * @modified By
 */
@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void find(){
        Map<String, Object> map = new HashMap<>();
        map.put("username", "username1");

        List<UserDO> userDOS = userMapper.selectByMap(map);
        log.info("userDOS：{}",userDOS);
    }

}