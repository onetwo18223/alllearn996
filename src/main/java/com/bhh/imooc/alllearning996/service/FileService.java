package com.bhh.imooc.alllearning996.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author bhh
 * @description 文件服务
 * @date Created in 2020-12-11 9:24
 * @modified By
 */
public interface FileService {

    /**
     * 文件上传
     * @param inputStream 文件流
     * @param fileName 文件名称
     */
    void upload(InputStream inputStream, String fileName);

    /**
     * 文件上传
     * @param file 文件
     */
    void upload(File file);
}
