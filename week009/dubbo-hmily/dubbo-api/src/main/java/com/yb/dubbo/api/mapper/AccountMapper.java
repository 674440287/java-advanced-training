package com.yb.dubbo.api.mapper;

import com.yb.dubbo.api.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Account)表数据库访问层
 *
 * @author yangbiao
 * @since 2021-05-28 16:18:58
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
