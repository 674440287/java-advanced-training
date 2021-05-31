package com.yb.dubbo.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * @author yangbiao
 * @since 2021-05-28 16:18:58
 */
@Setter
@Getter
@ApiModel(description = "信息类")
public class Account extends Model<Account> implements Serializable {
    private static final long serialVersionUID = 344247190763523161L;

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户余额")
    private Double totalMoney;

    @ApiModelProperty("金钱类型(人民币/美元)")
    private String moneyType;

}
