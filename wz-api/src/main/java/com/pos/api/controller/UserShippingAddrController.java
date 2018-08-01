package com.pos.api.controller;

import com.czwx.cashloan.core.model.UserShippingAddr;
import com.rongdu.cashloan.core.common.context.Constant;
import com.rongdu.cashloan.core.common.util.ServletUtils;
import com.rongdu.cashloan.core.common.web.controller.BaseController;
import com.rongdu.cashloan.core.service.UserShippingAddrService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
public class UserShippingAddrController extends BaseController{
    @Resource
    private UserShippingAddrService userShippingAddrService;

    /**
     * 收货地址列表
     * @param userId
     */
    @RequestMapping("api/addr/list.htm")
    public void list(@RequestParam(value = "userId")Long userId){
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        List<UserShippingAddr> data = userShippingAddrService.listSelective(param);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 新增或修改收货地址
     * @param userId
     * @param id
     * @param province
     * @param city
     * @param area
     * @param detailAddr
     * @param state
     */
    @RequestMapping("api/addr/saveOrUpdate.htm")
    public void saveOrUpdate(@RequestParam(value = "userId")Long userId,
                               @RequestParam(value = "id",required = false)Long id,
                               @RequestParam(value = "province")String province,
                               @RequestParam(value = "city")String city,
                               @RequestParam(value = "area")String area,
                               @RequestParam(value = "detailAddr")String detailAddr,
                               @RequestParam(value = "state")int state
                               ){
        try {
            province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
            city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
            area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
            detailAddr=new String(detailAddr.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserShippingAddr userShippingAddr = new UserShippingAddr();
        if (id!=null) userShippingAddr.setId(id);
        userShippingAddr.setUserId(userId);
        userShippingAddr.setProvince(province);
        userShippingAddr.setCity(city);
        userShippingAddr.setArea(area);
        userShippingAddr.setDetailAddr(detailAddr);
        userShippingAddr.setState((byte)state);
        int i = userShippingAddrService.saveOrUpdate(userShippingAddr);
        Map<String, Object> result = new HashMap<String, Object>();
        if (i>0){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
        }else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
        }
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 删除收货地址
     * @param id
     */
    @RequestMapping("api/addr/delete.htm")
    public void delete(@RequestParam(value = "id")Long id){
        int i = userShippingAddrService.deleteByPrimaryKey(id);
        Map<String, Object> result = new HashMap<String, Object>();
        if (i>0){
            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "操作成功");
        }else {
            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_DATA, "");
            result.put(Constant.RESPONSE_CODE_MSG, "操作失败");
        }
        ServletUtils.writeToResponse(response, result);
    }
}
