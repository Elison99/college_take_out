package org.example.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.college.entity.User;
import org.example.college.mapper.UserMapper;
import org.example.college.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
