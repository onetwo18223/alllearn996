package com.bhh.imooc.alllearning996.service.impl;

import com.bhh.imooc.alllearning996.exception.BusinessException;
import com.bhh.imooc.alllearning996.exception.CodeEnum;
import com.bhh.imooc.alllearning996.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author bhh
 * @description 本地文件上传服务实现类
 * @date Created in 2020-12-11 9:28
 * @modified By
 */
@Slf4j
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    private static final String BUCKET = "F:\\maker\\JAVA\\JavaProject\\workspace01\\alllearning996\\uploads";

    @Override
    public void upload(InputStream inputStream, String fileName) {
        //修改文件名称
        String newFileName = UUID
                .randomUUID()
                .toString()
                .substring(0, 8) +
                LocalDateTime
                        .now()
                        .toString()
                        .replaceAll("-", "")
                        .replaceAll(":", "")
                        .replaceAll(".", "") + ".jpg";

        //拼接文件的存储路径
        String path = BUCKET + "/" + newFileName;

        try (
                //JDK 8 twr不可以直接关闭外部资源，只能关闭内部资源
                InputStream innerInputStream = inputStream;

                FileOutputStream outputStream =
                        new FileOutputStream(new File(path));
        ) {
            //拷贝缓存区
            byte[] bytes = new byte[1024];
            //读取流的长度
            int len;

            //循环读取InputSteam中数据写入到outputStream
            while ((len = innerInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }

            //冲刷outputStream
            outputStream.flush();

        } catch (Exception e) {

            log.error("文件上传失败！", e);
            throw new BusinessException(CodeEnum.FAIL, e);
        }

    }

    @Override
    public void upload(File file) {
        try {

            upload(new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {

            log.error("文件上传失败", e);
            throw new BusinessException(CodeEnum.FAIL, e);
        }
    }
}
