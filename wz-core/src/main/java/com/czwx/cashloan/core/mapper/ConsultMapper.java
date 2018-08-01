package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.Banner;
import com.czwx.cashloan.core.model.Consult;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface ConsultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Consult record);

    int insertSelective(Consult record);

    Consult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Consult record);

    int updateByPrimaryKey(Consult record);

    List<Banner> listSelect(Map<String,Object> map);
}