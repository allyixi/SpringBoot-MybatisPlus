package com.test.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.mybatis.mapper.EmployeeMapper;
import com.test.mybatis.po.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void search(){
        List<Employee> list=employeeMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void insert(){
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setEmail("1");
        employee.setGender(0);
        employee.setLastName("1111");
        employeeMapper.insert(employee);
    }

    @Test
    public void update(){
        Employee employee = new Employee();
        employee.setId(1266257403865972738l);
        employee.setAge(222);
        employee.setEmail("2");
        employee.setGender(0);
        employee.setLastName("222");
        employeeMapper.updateById(employee);
    }

    //测试乐观锁（成功情况）
    @Test
    public void testOptimisticLockerS(){
        Employee employee=employeeMapper.selectById(1l);
        employee.setAge(111);
        employeeMapper.updateById(employee);
    }

    //测试乐观锁（失败情况）
    @Test
    public void testOptimisticLockerF(){
        //线程1
        Employee employee=employeeMapper.selectById(1l);
        employee.setAge(111);

        //线程2 插队
        Employee employee2=employeeMapper.selectById(1l);
        employee2.setAge(555);
        int a=employeeMapper.updateById(employee2);
        System.out.println(a);
        int b=0;
        while(b==0){
            b=employeeMapper.updateById(employee);
        }
        System.out.println(b);
        //线程1未成功 通过自选锁进行重复提交
    }

    @Test
    public void select(){
        //单个查询
        Employee employee =employeeMapper.selectById(1266257403865972738l);
        System.out.println(employee);
        System.out.println(employeeMapper.s1());
//        //批量查询
//        List<Employee> employeelist =employeeMapper.selectBatchIds(Arrays.asList(1,2,3));
//        //条件查询方法之一：条件放在map里 另一个是wrapper
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("last_name","Tom");
//        List<Employee> employeelist2=employeeMapper.selectByMap(map);
    }

    //分页查询
    @Test
    public void selectByPage(){
        //是com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        //new Page<>(currentPage,pageSize)
        Page<Employee> page = new Page<>(1,3);
        //返回值存在page中
        employeeMapper.selectPage(page,null);
        List<Employee> list=page.getRecords();
        System.out.println(list);
        //返回一共多少页
        page.getTotal();
        //返回当前页
        page.getCurrent();
        //返回每页数量
        page.getSize();
        //是否存在下一页
        page.hasPrevious();
        //是否存在上一页
        page.hasNext();
    }

    //删除
    @Test
    public void delete(){
        //方法一：根据id删除
        employeeMapper.deleteById(1266257403865972738l);
//        employeeMapper.deleteBatchIds(Arrays.asList(1,2,3));
//        //方法二：根据map删除
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("last_name","Tom");
//        employeeMapper.deleteByMap(map);
    }

}
