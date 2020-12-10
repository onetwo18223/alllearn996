package com.bhh.imooc.alllearning996.service.impl;

import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bhh
 * @description 测试UserServiceImpl
 * @date Created in 2020-12-09 16:25
 * @modified By
 */
@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void saveTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("关羽");
        userDTO.setPassword("55555");
        userDTO.setEmail("555@wuhu.com");
        userDTO.setAge(55);
        userDTO.setPhone("1791283918");

        int save = userService.save(userDTO);
        log.info("save()返回值:{}", save);
    }

    /**
     * 乐观锁使用规则：
     * 1 如果更新数据时没有version字段：不使用乐观锁，且version不会累加
     * 2 如果更新字段中带有version，但是和数据库中的version数值不一致，则不进行修改
     * 3 如果更新数据中带有version字段，且数值与数据库中一致，则进行累加
     */
    @Test
    void updateTest() {
//     TODO:正确的更新版本的方式：
//          先根据id获取userDTO，
//          再进行修改，
//          再传回进行修改，
//          因为需要version属性的值存在才可以进行递增修改
        Long id = 1336606161261268994L;

        UserDTO userDTO = userService.queryById(id);
        userDTO.setPassword("121456");
        userDTO.setAge(14123123);

        int update = userService.update(id, userDTO);
        log.info("updateTest():{}", update);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        int delete = userService.delete(id);
        log.info("deleteTest()：{}", delete);
    }

    @Test
    void query() {
    }
}