package com.idempotent.service;

import com.idempotent.common.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hex1n
 * @Time 2020/5/4 17:22
 */
public interface TokenService {
    R createToken();

    void checkToken(HttpServletRequest request);
}
