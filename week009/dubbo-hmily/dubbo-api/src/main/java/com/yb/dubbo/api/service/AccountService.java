package com.yb.dubbo.api.service;

import com.yb.dubbo.api.entity.Account;
import com.yb.dubbo.api.mapper.AccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * (Account)表服务实现类
 *
 * @author yangbiao
 * @since 2021-05-28 16:18:59
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

}
