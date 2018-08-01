package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.Banner;
import com.czwx.cashloan.core.model.Consult;
import com.rongdu.cashloan.core.common.service.BaseService;

import java.util.List;
import java.util.Map;

public interface ConsultService extends BaseService<Consult,Long> {
    List<Banner> listSelect(Map<String,Object> map);
}
