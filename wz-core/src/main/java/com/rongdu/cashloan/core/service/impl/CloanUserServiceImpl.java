package com.rongdu.cashloan.core.service.impl;


import com.czwx.cashloan.core.mapper.UserMapper;
import com.czwx.cashloan.core.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.core.model.CloanUserModel;
import com.rongdu.cashloan.core.service.CloanUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("cloanUserService")
public class CloanUserServiceImpl extends BaseServiceImpl<User, Long> implements CloanUserService {

	@Resource
	private UserMapper userMapper;
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CloanUserServiceImpl.class);


	@Override
	public Page<User> listUser(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<User> users = userMapper.listSelective(params);
		return (Page<User>) users;
	}

	@Override
	public CloanUserModel getModelById(Long id) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findAllDic() {
		return null;
	}

	@Override
	public boolean updateByUuid(Map<String, Object> paramMap) {
		return false;
	}

	@Override
	public User findByPhone(String phone) {
		return null;
	}

	@Override
	public long todayCount() {
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return 0;
	}

	@Override
	public int updateById(User record) {
		return 0;
	}

	@Override
	public BaseMapper<User, Long> getMapper() {
		return null;
	}

	@Override
	public User getById(Long aLong) {
		return null;
	}
}