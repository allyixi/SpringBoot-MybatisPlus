package com.test.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.mybatis.po.Employee;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("SELECT * FROM tbl_employee WHERE id=1266257403865972738")
    public Employee s1();


}