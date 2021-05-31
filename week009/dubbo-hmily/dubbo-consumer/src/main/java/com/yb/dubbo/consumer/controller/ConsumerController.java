package com.yb.dubbo.consumer.controller;

import com.yb.dubbo.api.response.ResultInfo;
import com.yb.dubbo.api.service.AccountService;
import com.yb.dubbo.api.service.FrozenAssetsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangbiao
 * @since 2021-05-28 16:18:58
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class ConsumerController {
    private final AccountService accountService;
    private final FrozenAssetsService frozenAssetsService;

    @ApiOperation("")
    @GetMapping("/")
    public ResultInfo<String> get() {
        return ResultInfo.success();
    }

}
