package com.thtf.item.service.impl;

import com.thtf.common.enums.ExceptionEnums;
import com.thtf.common.exception.CommonException;
import com.thtf.item.mapper.CategoryMapper;
import com.thtf.item.model.Category;
import com.thtf.item.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品分类service实现类
 * @author pyy
 * @date 2019年5月19日 23:01:21
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        if (pid == null || pid < 0) {
            logger.error("请求参数错误");
            throw new CommonException(ExceptionEnums.BAD_REQUEST_PARAMETER);
        }
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.error("商品分类数据查询失败");
            throw new CommonException(ExceptionEnums.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }
}
