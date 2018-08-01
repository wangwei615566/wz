package com.pos.manage.controller;

import com.czwx.cashloan.core.model.Goods;
import com.github.pagehelper.Page;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.JsonUtil;
import com.rongdu.cashloan.core.common.util.RdPage;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.service.GoodsService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class ManageGoodsController extends ManageBaseController {

    @Resource
    private GoodsService goodsService;

    /**
     * 订单列表
     * @param searchParams 查询参数 json字符串
     * @param current 当前页
     * @param pageSize 每页页数
     * @throws Exception 异常
     */
    @RequestMapping(value = "/manage/goods/search/list.htm")
    public void searchList(@RequestParam(value="searchParams",required=false) String searchParams,
                           @RequestParam(value = "current") int current,
                           @RequestParam(value = "pageSize") int pageSize) throws Exception {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Page<Goods> page = goodsService.pageList(params, current, pageSize);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 查询单个订单
     * @param goodsId 商品id
     * @throws Exception
     */
    @RequestMapping(value = "/manage/goods/search/id.htm")
    public void findById(@RequestParam(value="goodsId") long goodsId) throws Exception {
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        Map<String,Object> result = new HashMap<>();
        result.put(Constant.RESPONSE_DATA, goods);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 删除单个订单
     * @param goodsId 商品id
     * @throws Exception
     */
    @RequestMapping(value = "/manage/goods/search/delete.htm")
    public void deleteById(@RequestParam(value="goodsId") long goodsId) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("id",goodsId);
        params.put("status",0);
        int code = goodsService.updateBySelect(params);
        Map<String,Object> result = saveUpdateCommon(code, "删除失败", "删除成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 保存商品
     * @param searchParams 修改时，必须包含商品id
     * @throws Exception json转换异常
     */
    @RequestMapping(value = "/manage/goods/search/save.htm")
    public void save(@RequestParam(value="searchParams") String searchParams) throws Exception {
        Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
        Map<String,Object> result = new HashMap<>();
        if(params == null){
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "参数错误");
            ServletUtils.writeToResponse(response, result);
            return;
        }
        if(params.get("id") == null) {
            //新增
            int i = goodsService.insertSelective(params);
            result = saveUpdateCommon(i, "新增失败", "保存成功");
        } else {
            //修改
            int code = goodsService.updateBySelect(params);
            result = saveUpdateCommon(code, "修改失败", "保存成功");
        }

        ServletUtils.writeToResponse(response, result);
    }

}
