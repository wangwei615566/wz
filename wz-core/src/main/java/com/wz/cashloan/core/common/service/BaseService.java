package com.wz.cashloan.core.common.service;

import java.io.Serializable;


public interface BaseService<T, ID extends Serializable> {

	int insert(T record);


	int updateById(T record);
	
	
	T getById(ID id);
	

}
