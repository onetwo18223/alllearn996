package com.bhh.imooc.alllearning996.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author bhh
 * @description 公共元数据处理器
 * @date Created in 2020-12-09 16:47
 * @modified By
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新建数据时，自动填充系统字段!");

        /**
         * 自动填充创建时间
         */
        this.strictInsertFill(metaObject, "created",
                LocalDateTime.class, LocalDateTime.now());

        /**
         * 自动填充最后修改时间
         */
        this.strictInsertFill(metaObject, "modified",
                LocalDateTime.class, LocalDateTime.now());

        /**
         * 自动填充创建人
         */
        this.strictInsertFill(metaObject, "creator",
                String.class, "TODO:根据上下文获取创建人");

        /**
         * 自动填充最后修改人
         */
        this.strictInsertFill(metaObject, "operator",
                String.class, "TODO:根据上下文获取修改人");

        /**
         * 自动填充新增数据的状态（0为存在，1为逻辑删除）
         */
        this.strictInsertFill(metaObject, "status",
                Integer.class, 0);

        /**
         * 自动填充新增数据的版本号
         */
        this.strictInsertFill(metaObject, "version",
                Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("对数据行修改时，自动修改系统字段");

        /**
         * 自动修改最后修改时间
         */
        this.strictUpdateFill(metaObject, "modified",
                LocalDateTime.class, LocalDateTime.now());

        /**
         * 自动修改最后修改人信息
         */
        this.strictUpdateFill(metaObject, "operator",
                String.class, "TODO:根据上下文获取最后修改人");
    }
}
