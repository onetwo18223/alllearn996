package com.bhh.imooc.alllearning996.controller;

import com.bhh.imooc.alllearning996.domain.common.ResponseResult;
import com.bhh.imooc.alllearning996.exception.BusinessException;
import com.bhh.imooc.alllearning996.exception.CodeEnum;
import com.bhh.imooc.alllearning996.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author bhh
 * @description 文件服务Controller
 * @date Created in 2020-12-11 9:19
 * @modified By
 */
@RequestMapping("/api/files")
@RestController
@Slf4j
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult upload(
            @NotNull MultipartFile file) {

        try {

            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {

            log.error("文件上传异常！", e);
            throw new BusinessException(CodeEnum.FAIL, e);
        }

        return ResponseResult.success(file.getOriginalFilename());
    }
}
