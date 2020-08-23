package com.model.calculate.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.model.calculate.domain.Result;
import com.model.calculate.domain.User;
import com.model.calculate.service.LoginService;
import com.model.calculate.util.LoginCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {

    private static Cache<String, User> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .maximumSize(100)
            .build();

    @Override
    public Result login(User user) {
        if (!"admin".equals(user.getUsername())) {
            return Result.fail("用户名错误,请重新输入");
        }
        if (!"admin".equals(user.getPassword())) {
            return Result.fail("密码错误,请重新输入");
        }
        cache.put(user.getUsername(), user);
        return Result.success();
    }

    @Override
    public boolean isLogin(String key) {
        if (cache == null) return false;
        return !(cache.getIfPresent(key) == null);
    }
}
