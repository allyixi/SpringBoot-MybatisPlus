package com.test.mybatis.controller;

import com.test.mybatis.mapper.EmployeeMapper;
import com.test.mybatis.po.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/t")
    public List<Employee> getE(){
        //参数是一个Wrapper，条件构造器
        return employeeMapper.selectList(null);
    }
}
