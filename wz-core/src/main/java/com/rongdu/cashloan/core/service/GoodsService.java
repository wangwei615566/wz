package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.Goods;
import com.czwx.cashloan.core.model.GoodsDetail;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<Goods> listMember(Long userId);

    List<Goods> listSelect(Map<String,Object> param);

    Page<Goods> pageList(Map<String,Object> param, int current, int pageSize);

    Goods selectByPrimaryKey(Long goodsId);

    int updateBySelect(Map<String,Object> param);

    int insertSelective(Map<String,Object> param);

    List<Goods> listGoods(String type,Long userId);

    List<GoodsDetail> listGoodsDetail(Map<String,Object> param);
}
