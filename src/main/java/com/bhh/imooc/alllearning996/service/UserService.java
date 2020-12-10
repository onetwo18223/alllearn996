package com.bhh.imooc.alllearning996.service;

import com.bhh.imooc.alllearning996.domain.common.PageQuery;
import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import com.bhh.imooc.alllearning996.domain.entity.UserDO;

import java.util.List;

/**
 * @author bhh
 * @description UserService
 * @date Created in 2020-12-09 13:56
 * @modified By
 */
public interface UserService {

    /**
     * 新增数据
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * 修改数据
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据id查询获取UserDTO
     * @param id
     * @return
     */
    UserDTO queryById(Long id);

    /**
     * 分页查询
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);

}
