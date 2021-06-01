package com.lyp.dubbo.api.service;

import com.lyp.dubbo.api.entity.Account;
import com.lyp.dubbo.api.mapper.AccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

}
