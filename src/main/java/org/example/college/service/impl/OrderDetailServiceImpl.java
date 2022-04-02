package org.example.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.college.entity.OrderDetail;
import org.example.college.mapper.OrderDetailMapper;
import org.example.college.service.OrderDetailService;
import org.example.college.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
