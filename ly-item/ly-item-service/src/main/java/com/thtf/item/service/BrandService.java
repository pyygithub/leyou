package com.thtf.item.service;

import com.thtf.common.vo.PageResult;
import com.thtf.item.model.Brand;

import java.util.List;

/**
 * @Description:品牌service接口
 * @Author: panyangyang
 * @Date: 2019/5/20 16:19
 */
public interface BrandService {

    PageResult<Brand> queryBrandByPageAndSort(Integer pageNum, Integer rows, String sortBy, Boolean desc, String keyword);

    void saveBrand(Brand brand, List<Long> cids);
}
