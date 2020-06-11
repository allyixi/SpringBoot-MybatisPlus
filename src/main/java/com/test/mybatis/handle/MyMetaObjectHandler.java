package com.test.mybatis.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.0(推荐使用)
         this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
         //可用 注意一下类型匹配 使用LocalDateTime 实体类也需要是LocalDateTime 如果实体类是Date 这里使用new Date()
        // this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        /* 下面两个方法过时了 */
        //this.setFieldValByName(字段名, 字段值, metaObject);
//        this.setFieldValByName("createTime", new Date(), metaObject);
        //this.setInsertFieldValByName("createTime", new Date(), metaObject);
    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.0(推荐使用)
         this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//         this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); 字段类型需要是LocalDateTime Date类型报错
        /* 下面两个方法过时了 */
        //this.setFieldValByName(字段名, 字段值, metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setUpdateFieldValByName("updateTime", new Date(), metaObject);
    }
}
