package com.idempotent.controller;

import com.idempotent.annotation.Idempotent;
import com.idempotent.common.R;
import com.idempotent.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author hex1n
 * @Time 2020/5/4 17:43
 */
@RestController
@RequestMapping("/idempotent/test")
public class TestController {

    @Resource
    private TokenService tokenService;

    @GetMapping("token")
    public R getToken() {
        return tokenService.createToken();
    }

    @PostMapping("testIdempotent")
    @Idempotent
    public R testIdempotent() {
        return R.ok().msg("测试接口幂等");
    }
}
