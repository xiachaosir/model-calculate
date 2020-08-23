package com.model.calculate.service;

import com.model.calculate.domain.Result;
import com.model.calculate.domain.User;

public interface LoginService {

    Result login(User user);

    boolean isLogin(String key);
}
