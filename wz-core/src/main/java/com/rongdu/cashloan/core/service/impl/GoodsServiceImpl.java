package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.GoodsDetailMapper;
import com.czwx.cashloan.core.mapper.GoodsMapper;
import com.czwx.cashloan.core.mapper.UserMapper;
import com.czwx.cashloan.core.model.Goods;
import com.czwx.cashloan.core.model.GoodsDetail;
import com.czwx.cashloan.core.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rongdu.cashloan.core.service.GoodsService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private GoodsDetailMapper goodsDetailMapper;

    @Override
    public List<Goods> listMember(Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("type",2);
        List<Goods> goods = goodsMapper.listSelect(param);
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getLevelId() == 2) {
            goods.get(0).setFlag(false);
            goods.get(1).setFlag(true);
        } else if (user.getLevelId() == 3) {
            goods.get(0).setFlag(false);
            goods.get(1).setFlag(false);
        }else {
            goods.get(0).setFlag(true);
            goods.get(1).setFlag(true);
        }
        return goods;
    }

    @Override
    public List<Goods> listSelect(Map<String, Object> param) {
        return goodsMapper.listSelect(param);
    }

    @Override
    public Page<Goods> pageList(Map<String, Object> param, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<Goods> goodsList = goodsMapper.listSelect(param);
        return (Page<Goods>) goodsList;
    }

    @Override
    public Goods selectByPrimaryKey(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public int updateBySelect(Map<String, Object> param) {
        return goodsMapper.updateBySelect(param);
    }

    @Override
    public int insertSelective(Map<String, Object> param) {
        return goodsMapper.insertSelective(param);
    }

    @Override
    public List<Goods> listGoods(String type,Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("type",type);
        List<Goods> goods = goodsMapper.listSelect(param);
        Map<String, Object> levels = userMapper.findLevelToUserId(userId);
        BigDecimal rate = (BigDecimal) levels.get("rate");
        for (Goods g:goods
             ) {
            g.setProfitAmount(g.getProfitAmount().multiply(rate));
        }
        return goods;
    }

    @Override
    public List<GoodsDetail> listGoodsDetail(Map<String,Object> param) {
        return goodsDetailMapper.listSelect(param);
    }
}
