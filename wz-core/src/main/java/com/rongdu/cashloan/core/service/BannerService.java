package com.rongdu.cashloan.core.service;

import com.czwx.cashloan.core.model.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    List<Banner> listSelect(Map<String,Object> map);
}
