package com.czwx.cashloan.core.mapper;

import com.czwx.cashloan.core.model.Goods;
import com.rongdu.cashloan.core.common.mapper.RDBatisDao;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;
@RDBatisDao
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Map<String,Object> map);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods>listSelect(Map<String,Object> param);

    int updateBySelect(Map<String,Object> param);
}