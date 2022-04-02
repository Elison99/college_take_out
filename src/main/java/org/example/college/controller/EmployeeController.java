package org.example.college.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.college.common.Result;
import org.example.college.entity.Employee;
import org.example.college.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 职工登陆
     * @param httpServletRequest 回话信息
     * @param employee 登陆的员工
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest httpServletRequest, @RequestBody Employee employee){

        //将明文密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //用户名不存在
        if (emp == null){
            return Result.error("该用户不存在");
        }

        //密码不正确
        if (!emp.getPassword().equals(password)){
            return Result.error("该用户不存在");
        }

        //被禁用
        if (emp.getStatus() == 0){
            return Result.error("账号已被禁用");
        }

        //登陆成功
        httpServletRequest.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);

    }

    /**
     * 退出登录
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest httpServletRequest){
        //清除session中保存的员工id
        httpServletRequest.getSession().removeAttribute("employee");
        return Result.success("成功退出当前账号");
    }

    @PostMapping
    public Result<String> save(HttpServletRequest httpServletRequest, @RequestBody Employee employee){

        log.info("新增员工,员工信息:{}",employee.toString());

        //初始密码为123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //获取当前登录用户id
//        Long empId = (Long)httpServletRequest.getSession().getAttribute("employee");

//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return Result.success("新增职工成功");

    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name={}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加排序条件

        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getUsername,name);

        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 编辑和修改状态通用更新方法
     * @param httpServletRequest
     * @param employee
     * @return
     */
    @PutMapping
    public Result<String> update(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        log.info(employee.toString());

//        Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");

//        employee.setUpdateUser(empId);
//        employee.setUpdateTime(LocalDateTime.now());

        employeeService.updateById(employee);

        return Result.success("员工信息已成功修改!");
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return Result.success(employee);
        }
        return Result.error("没有查询到对应员工信息");
    }
}
