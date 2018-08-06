package com.wz.manage.service;

import com.github.pagehelper.Page;
import com.wz.cashloan.core.model.User;

import java.util.Map;

public interface UserService {

    Page<User> pageList(Map<String, Object> params, int current, int pageSize);

    int updateUser(Map<String, Object> params);
}
