package com.bhh.imooc.alllearning996.controller;

import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.common.ResponseResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import com.bhh.imooc.alllearning996.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bhh
 * @description
 * @date Created in 2020-12-10 9:25
 * @modified By
 */
@SpringBootTest
@Slf4j
class UserControllerTest {

    @Autowired
    private UserController userController;

    public static final Long ID = 1336848625138106369L;

    @Test
    void save() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("关羽");
        userDTO.setPassword("123123");
        userDTO.setEmail("123@foxmail.com");
        userDTO.setAge(12);
        userDTO.setPhone("123123131");

        ResponseResult<Object> save = userController.save(userDTO);
        log.info("save():{}", save);
    }

    @Test
    void update() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("赵子龙");
        userDTO.setPassword("123123");
        userDTO.setEmail("123@foxmail.com");
        userDTO.setAge(10);
        userDTO.setPhone("12313132");

        ResponseResult<Object> update = userController.update(ID, userDTO);
        log.info("update():{}", update);
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
        ResponseResult<Object> objectResponseResult = userController.get(ID);
        log.info("delete():{}", objectResponseResult);
    }

    @Test
    void getByPage() {

        int pageNo = 2;
        int pageSize = 2;
        UserQueryDTO userQueryDTO = new UserQueryDTO();

        ResponseResult<PageResult<List<UserVO>>> page
                = userController.getByPage(pageNo, pageSize, userQueryDTO);
        log.info("getByPage():{}", page);
    }
}