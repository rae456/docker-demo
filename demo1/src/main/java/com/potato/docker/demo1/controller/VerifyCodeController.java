package com.potato.docker.demo1.controller;

import com.potato.docker.demo1.common.ResponseVo;
import com.potato.docker.demo1.constants.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("code")
public class VerifyCodeController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("get/{phone}")
    public ResponseVo<String> getCode(@PathVariable("phone") String phone) {
        String key = RedisConstants.REDIS_PREFIX + "code:" + phone;
        String code = redisTemplate.opsForValue().get(key);
        if (StringUtils.hasText(code) && ((Long.parseLong(code.split("_")[1]) - System.currentTimeMillis()) < 10 * 60 * 1000)) {
            return ResponseVo.fail("获取验证码过于频繁，稍后再试");
        }
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String uuidWithMills = uuid + "_" + System.currentTimeMillis();
        redisTemplate.opsForValue().set(key, uuidWithMills, 1, TimeUnit.MINUTES);
        return ResponseVo.ok().setData(uuid);
    }

    @GetMapping("verify/{phone}/{code}")
    public ResponseVo<String> verify(@PathVariable("phone") String phone, @PathVariable("code") String code) {
        String key = RedisConstants.REDIS_PREFIX + "code:" + phone;
        String codeWithMills = redisTemplate.opsForValue().get(key);
        if(StringUtils.hasText(codeWithMills) && codeWithMills.split("_")[0].equals(code)){
            redisTemplate.delete(key);
            return ResponseVo.ok();
        }
        return ResponseVo.fail("验证失败");
    }
}
