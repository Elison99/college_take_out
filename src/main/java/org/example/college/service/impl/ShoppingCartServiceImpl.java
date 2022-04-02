package org.example.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.college.entity.ShoppingCart;
import org.example.college.mapper.ShoppingCartMapper;
import org.example.college.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
