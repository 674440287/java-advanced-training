package com.lyp.dubbo.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@Setter
@Getter
@ApiModel(description = "信息类")
public class FrozenAssets extends Model<FrozenAssets> implements Serializable {
    private static final long serialVersionUID = 272364779390902990L;

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("冻结金额")
    private Double frozenMoney;

    @ApiModelProperty("金钱类型(人民币/美元)")
    private String moneyType;

}
