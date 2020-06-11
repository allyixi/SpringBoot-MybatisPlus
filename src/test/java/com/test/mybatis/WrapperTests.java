package com.test.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.mybatis.mapper.EmployeeMapper;
import com.test.mybatis.po.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void test1() {
        //查询name不为空和邮箱不为空，年龄大于30岁
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //wrapper链式编程，
        wrapper
                .isNotNull("last_name")
                .isNotNull("email")
                //ge(键,值)大于等于
                .ge("age", 30);
        List<Employee> list = employeeMapper.selectList(wrapper);
        System.out.println(list);
    }

    @Test
    public void test2() {
        //匹配查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //eq(键,值)相等
        wrapper.eq("last_name", "Tom");
        //查询一个
        Employee employee = employeeMapper.selectOne(wrapper);
        System.out.println(employee);
    }

    @Test
    public void test3() {
        //区间查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //between(键,between,and)
        wrapper.between("age", 0, 20);
        //查询结果数量
        System.out.println(employeeMapper.selectCount(wrapper));
    }

    @Test
    public void test4() {
        //模糊查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper
                //notLike(键,值) 不包含
                .notLike("last_name","e")
                //x% 右模糊
                .likeRight("email","t")
                //%x 左模糊
                .likeLeft("email","m");
        //List<map>
        List<Map<String, Object>> maps = employeeMapper.selectMaps(wrapper);
        for (Map m : maps) {
            System.out.println(m);
        }
    }

    @Test
    public void test5() {
        //模糊查询
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //inSql(键,sql语句) 子查询
        wrapper.inSql("id","select id from tbl_employee where id<3");
        //List<Object>
        List<Employee> employeeList = employeeMapper.selectList(wrapper);
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }

    @Test
    public void test6() {
        //排序
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Employee> employeeList = employeeMapper.selectList(wrapper);
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }
}
