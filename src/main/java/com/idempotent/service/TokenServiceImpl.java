package com.idempotent.service;

import com.idempotent.common.Constant;
import com.idempotent.common.R;
import com.idempotent.common.ResponseCode;
import com.idempotent.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author hex1n
 * @Time 2020/5/4 17:26
 */
@Service
@Transactional(readOnly = true)
public class TokenServiceImpl implements TokenService {
    private static final String API_IDEMPOTENT_TOKEN_NAME = "apiIdempotentToken";

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public R createToken() {
        String str = UUID.randomUUID().toString().replace("-", "");
        StringBuilder token = new StringBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);
        redisUtil.set(token.toString(),token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token.toString());
        return R.ok().data(tokenMap);
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(API_IDEMPOTENT_TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(API_IDEMPOTENT_TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                throw new RuntimeException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        if (!redisUtil.exists(token)) {
            throw new RuntimeException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
        Boolean del = redisUtil.del(token);
        if (!del) {
            throw new RuntimeException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
