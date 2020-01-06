package com.zyn.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhaoyanan
 * @create 2020-01-06-11:21
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    //insertFill在mp添加操作的时候执行
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(), metaObject);
        this.setFieldValByName("updateTime",new Date(), metaObject);
        this.setFieldValByName("version",1, metaObject);
        this.setFieldValByName("deleted",0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(), metaObject);
    }
}
