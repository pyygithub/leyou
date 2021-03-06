package com.thtf.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thtf.common.enums.ExceptionEnums;
import com.thtf.common.exception.CommonException;
import com.thtf.common.vo.PageResult;
import com.thtf.item.mapper.BrandMapper;
import com.thtf.item.model.Brand;
import com.thtf.item.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:品牌service接口实现类
 * @Author: panyangyang
 * @Date: 2019/5/20 16:20
 */
@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌列表分页模糊查询加排序
     * @author: pyy
     * @date: 2019/5/23 8:55
     * @param pageNum
     * @param rows
     * @param sortBy
     * @param desc
     * @param keyword
     * @return: com.thtf.common.vo.PageResult<com.thtf.item.model.Brand>
     */
    public PageResult<Brand> queryBrandByPageAndSort(Integer pageNum, Integer rows, String sortBy, Boolean desc, String keyword) {
        // 分页
        Page page = PageHelper.startPage(pageNum, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(keyword)) {
            example.createCriteria().andLike("name", "%" + keyword + "%")
                    .orEqualTo("letter", keyword);
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> brandList = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brandList)) {
            log.info("品牌列表查询不到数据");
            throw new CommonException(ExceptionEnums.BRAND_NOT_FOUND);
        }

        return new PageResult(page.getTotal(), (long)page.getPages(), brandList);
    }

    /**
     * 品牌新增
     * @author: pyy
     * @date: 2019/5/23 8:55
     * @param brand
     * @param cids
     * @return: void
     */
    @Transactional
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1) {
            log.error("###新增品牌失败###");
            throw new CommonException(ExceptionEnums.BRAND_SAVE_ERROR);
        }
        // 新增中间表 品牌和分类关系表
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count != 1) {
                log.error("###新增品牌分类中间表失败###");
                throw new CommonException(ExceptionEnums.BRAND_SAVE_ERROR);
            }
        }

    }

}
