package org.example.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.college.entity.AddressBook;
import org.example.college.mapper.AddressBookMapper;
import org.example.college.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
