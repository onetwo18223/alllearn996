package com.bhh.imooc.alllearning996.service;

import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;

/**
 * @author bhh
 * @description Excel导出服务接口
 * @date Created in 2020-12-11 13:30
 * @modified By
 */
public interface ExcelExportService {

    /**
     * 同步导出execl服务
     * @param userQueryDTO
     * @param filename
     */
    void export(UserQueryDTO userQueryDTO, String filename);

    /**
     * 实现异步线程导出execl服务
     * @param userQueryDTO
     * @param filename
     */
    void asyncExport(UserQueryDTO userQueryDTO, String filename);
}
