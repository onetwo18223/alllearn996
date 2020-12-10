package com.bhh.imooc.alllearning996.controller;

import com.bhh.imooc.alllearning996.domain.common.PageQuery;
import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.common.ResponseResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import com.bhh.imooc.alllearning996.domain.vo.UserVO;
import com.bhh.imooc.alllearning996.exception.CodeEnum;
import com.bhh.imooc.alllearning996.service.UserService;
import com.bhh.imooc.alllearning996.util.InsertValidationGroup;
import com.bhh.imooc.alllearning996.util.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bhh
 * @description 用户Controller
 * @date Created in 2020-12-09 10:39
 * @modified By
 */
@RestController
@RequestMapping("/api/users")
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户信息
     * POST /api/users UserDTO
     *
     * @param userDTO 新增用户信息
     * @return ResponseResult
     */
    @PostMapping
    public ResponseResult<Object> save(
            @Validated(InsertValidationGroup.class)
            @RequestBody UserDTO userDTO) {

        int save = userService.save(userDTO);

        return save == 1 ? ResponseResult.success(CodeEnum.SUCCESS, CodeEnum.SUCCESS)
                : ResponseResult.fail(CodeEnum.FAIL);
    }

    /**
     * 修改用户信息
     * PUT /api/users/{id} UserDTO
     *
     * @return ResponseResult
     */
    @PutMapping("/{id}")
    public ResponseResult<Object> update(
            @NotNull @PathVariable("id") Long id,
            @Validated(UpdateValidationGroup.class)
            @RequestBody UserDTO userDTO) {

        int update = userService.update(id, userDTO);

        return update == 1 ? ResponseResult.success(CodeEnum.SUCCESS, CodeEnum.SUCCESS)
                : ResponseResult.fail(CodeEnum.FAIL);
    }

    /**
     * 删除用户信息
     * DELETE /api/users/{id}
     *
     * @param id id
     * @return ResponseResult
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Object> delete(
            @NotNull @PathVariable("id") Long id) {

        int delete = userService.delete(id);

        return delete == 1 ? ResponseResult.success(CodeEnum.SUCCESS, CodeEnum.SUCCESS)
                : ResponseResult.fail(CodeEnum.FAIL);
    }

    /**
     * 获取用户信息
     *
     * @param id id
     * @return ResponseResult
     */
    @GetMapping("/user/{id}")
    public ResponseResult<Object> get(
            @NotNull @PathVariable("id") Long id) {

        UserDTO userDTO = userService.queryById(id);

        return userDTO != null ? ResponseResult.success(userDTO)
                : ResponseResult.fail(CodeEnum.FAIL);
    }

    /**
     * 获取用户信息，分页形势
     * 建立查询条件
     * 获取查询结果
     * 实体转换 UserDTO转换为UserVO
     * 获取最终结果
     *
     * @param pageNo       需求页数
     * @param pageSize     每页数据数
     * @param userQueryDTO 搜索信息
     * @return 需求数据
     */
    @GetMapping("/{pageNo}")
    public ResponseResult<PageResult<List<UserVO>>> getByPage(
            @NotNull @PathVariable("pageNo") Integer pageNo,
            @NotNull @RequestParam(defaultValue = "2") Integer pageSize ,
            @Validated UserQueryDTO userQueryDTO) {

        //建立查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(userQueryDTO);

        //获取查询结果
        PageResult<List<UserDTO>> query = userService.query(pageQuery);

        //实体转换 UserDTO转换为UserVO
        List<UserVO> userVOList = Optional
                .ofNullable(query.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    userVO.setPassword("******");
                    userVO.setPhone(userDTO.getPhone()
                            .replaceAll("(\\d{3})\\d{4}(\\d{4})",
                                    "$1****$2"));
                    return userVO;
                })
                .collect(Collectors.toList());

        //获取最终结果
        PageResult<List<UserVO>> pageResult = new PageResult<>();
        BeanUtils.copyProperties(query, pageResult);
        pageResult.setData(userVOList);

        return ResponseResult.success(CodeEnum.SUCCESS, pageResult);
    }
}
