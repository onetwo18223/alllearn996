package com.bhh.imooc.alllearning996.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.bhh.imooc.alllearning996.config.ExecutorConfig;
import com.bhh.imooc.alllearning996.domain.common.PageQuery;
import com.bhh.imooc.alllearning996.domain.common.PageResult;
import com.bhh.imooc.alllearning996.domain.dto.UserDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserExportDTO;
import com.bhh.imooc.alllearning996.domain.dto.UserQueryDTO;
import com.bhh.imooc.alllearning996.service.ExcelExportService;
import com.bhh.imooc.alllearning996.service.FileService;
import com.bhh.imooc.alllearning996.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bhh
 * @description 导出服务实现类
 * @date Created in 2020-12-11 13:32
 * @modified By
 */
@Service
@Slf4j
public class ExcelExportServiceImpl implements ExcelExportService {


    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Override
    public void export(UserQueryDTO userQueryDTO, String filename) {

        //输出流
        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        //1. 实现数据导出到Excel
        toExcel(outputStream, userQueryDTO);

        //输入流
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        //2. 实现文件上传
        fileService.upload(inputStream, filename);
    }

    /**
     * 使用@Async注解，使用线程池来进行方法
     * @param userQueryDTO
     * @param filename
     */
    @Async("exportServiceExecutor")
    @Override
    public void asyncExport(UserQueryDTO userQueryDTO, String filename) {
        export(userQueryDTO, filename);
    }

    /**
     * 实现搜索数据并导出到excel
     *
     * @param outputStream
     * @param userQueryDTO
     */
    private void toExcel(
            ByteArrayOutputStream outputStream,
            UserQueryDTO userQueryDTO) {

        //1. 创建EasyExcel对象
        ExcelWriter excelWriter = EasyExcelFactory.write(
                outputStream,
                UserExportDTO.class).build();

        //2. 分批加载数据
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageSize(2);
        pageQuery.setQuery(userQueryDTO);

        int pageNo = 0;

        PageResult<List<UserDTO>> pageResult;

        do {

            pageQuery.setPageNo(++pageNo);

            pageResult = userService.query(pageQuery);

            //数据转换：UserDTO转换为UserExportDTO
            List<UserExportDTO> userExportDTOS = Optional.ofNullable(pageResult.getData()) //Optional<List<UserDTO>>
                    .map(List::stream) //Optional<stream<UserDTO>>
                    .orElseGet(Stream::empty) //stream<UserDTO>
                    .map((userDTO) -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    })
                    .collect(Collectors.toList());

            //3. 导出分批的数据
            //将数据写入不同sheet页
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第"+pageNo+"页").build();
            excelWriter.write(userExportDTOS, writeSheet);

            log.info("导出第{}页数据成功", pageNo);
            //当总页数不再大于当前页数时，结束循环
        } while (pageResult.getPageNumber() > pageNo);

        //4. 收尾
        excelWriter.finish();

        log.info("成功数据导出！");
    }
}
