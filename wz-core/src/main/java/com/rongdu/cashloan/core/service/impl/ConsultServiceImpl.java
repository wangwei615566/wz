package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.ConsultMapper;
import com.czwx.cashloan.core.model.Banner;
import com.czwx.cashloan.core.model.Consult;
import com.rongdu.cashloan.core.common.mapper.BaseMapper;
import com.rongdu.cashloan.core.common.service.impl.BaseServiceImpl;
import com.rongdu.cashloan.core.service.ConsultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("consultService")
public class ConsultServiceImpl extends BaseServiceImpl<Consult,Long> implements ConsultService {
    @Resource
    private ConsultMapper consultMapper;
    @Override
    public BaseMapper<Consult, Long> getMapper() {
        return null;
    }

    @Override
    public List<Banner> listSelect(Map<String, Object> map) {
        return consultMapper.listSelect(map);
    }
}
