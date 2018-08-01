package com.rongdu.cashloan.core.service.impl;

import com.czwx.cashloan.core.mapper.BannerMapper;
import com.czwx.cashloan.core.model.Banner;
import com.rongdu.cashloan.core.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> listSelect(Map<String, Object> map) {
        return bannerMapper.listSelect(map);
    }
}
