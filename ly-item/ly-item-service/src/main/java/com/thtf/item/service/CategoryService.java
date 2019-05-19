package com.thtf.item.service;

import com.thtf.item.model.Category;

import java.util.List;

/**
 * 商品分类service实现类
 * @author pyy
 * @date 2019年5月19日 23:01:312
 */
public interface CategoryService {

    List<Category> queryCategoryListByPid(Long pid);
}
