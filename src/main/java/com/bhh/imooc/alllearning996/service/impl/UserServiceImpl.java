package com.bhh.imooc.alllearning996.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhh.imooc.alllearning996.domain.common.PageQuery;
import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import com.bhh.imooc.alllearning996.domain.entity.UserDO;
import com.bhh.imooc.alllearning996.mapper.UserMapper;
import com.bhh.imooc.alllearning996.service.UserService;
import com.bhh.imooc.alllearning996.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bhh
 * @description 实现UserService
 * @date Created in 2020-12-09 15:15
 * @modified By
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {

        //手动检验
        ValidatorUtils.validation(userDTO);

        UserDO userDO = new UserDO();
        // TODO: 浅拷贝,属性名称相同才可以进行拷贝
        BeanUtils.copyProperties(userDTO, userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {

        //先获取userDTO再修改
        UserDTO userDTOTemporary = queryById(id);

        UserDO userDO = new UserDO();

        //需要进行修改的属性进行替换
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(id);
        userDO.setVersion(userDTOTemporary.getVersion());

        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public UserDTO queryById(Long id) {
        UserDO userDO = userMapper.selectById(id);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
        //TODO:Page<>泛型和QueryWrapper<>泛型,和selectPage的返回值以及参数要求的关系

        //手动检验
        ValidatorUtils.validation(pageQuery);

        //参数构造
        Page<UserDO> page = new Page<>(pageQuery.getPageNo(),
                pageQuery.getPageSize());

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), userDO);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(userDO);

        //查询
        Page<UserDO> userDOPage = userMapper.selectPage(page, queryWrapper);

        //上层下层之间结果对接
        //结果解析
        //日常注意泛型类型
        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        pageResult.setPageNo((int) userDOPage.getCurrent());
        pageResult.setPageSize((int) userDOPage.getSize());
        pageResult.setTotal((int) userDOPage.getTotal());
        pageResult.setPageNumber((int) userDOPage.getPages());

        //optional对userDOPage进行判空操作，进行数据类型转换（DO转换为DTO）
        List<UserDTO> collect = Optional
                .ofNullable(userDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map((UserDO userDOTemporary) -> {
                    //需要返回的是userDTO格式
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDOTemporary, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());

        pageResult.setData(collect);

        return pageResult;
    }
}
