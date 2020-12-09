package com.bhh.imooc.alllearning996.controller;

import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.common.ResponseResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author bhh
 * @description 用户Controller
 * @date Created in 2020-12-09 10:39
 * @modified By
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * 新增用户信息
     * POST /api/users UserDTO
     * @param userDTO
     * @return
     */
    @PostMapping
    public ResponseResult save (@RequestBody UserDTO userDTO){
        return ResponseResult.success("新增成功");
    }

    /**
     * 修改用户信息
     * PUT /api/users/{id} UserDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult update(@PathVariable("id") Long id,
                                 @RequestBody UserDTO userDTO){

        return ResponseResult.success("更新成功");
    }

    /**
     * 删除用户信息
     * DELETE /api/users/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id")Long id){
        return ResponseResult.success("删除成功");
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping
    public ResponseResult get(@PathVariable("id")Long id){
        return ResponseResult.success("查询成功");
    }

    /**
     * 获取用户信息，分页形势
     * @param pageNo
     * @param pageSize
     * @param userQueryDTOs
     * @return
     */
    @GetMapping
    public ResponseResult<PageResult> getByPage(Integer pageNo,
                                                Integer pageSize,
                                                UserQueryDTO userQueryDTOs){

        return ResponseResult.success(new PageResult());
    }

}
