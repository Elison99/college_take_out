package org.example.college.service.impl;

import org.example.college.entity.Employee;
import org.example.college.mapper.EmployeeMapper;
import org.example.college.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * 服务层
 */
@Service
public class ServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

}
