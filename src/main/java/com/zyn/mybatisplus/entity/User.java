package com.zyn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author zhaoyanan
 * @create 2020-01-04-16:21
 */
@Data
public class User {

//    @TableId("IdType.ID_WORKER")
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)  //在插入时进行数据库字段的自动填充
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version; //乐观锁版本号

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted; //逻辑删除标志位
}
