package com.test.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.DynamicAny.DynArray;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_employee")
public class Employee {

//    默认
    @TableId(type= IdType.AUTO)
    private Long id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;
}
