package com.thtf.item.controller;

import com.thtf.common.vo.PageResult;
import com.thtf.item.model.Brand;
import com.thtf.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:品牌Controller
 * @Author: panyangyang
 * @Date: 2019/5/20 16:45
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 品牌数据分页模糊查询
     * @Author: PanYangYang
     * @Date: 2019/5/20 16:53
     * @Param: [pageNum, rows, sortBy, desc, keyword]
     * @Return: org.springframework.http.ResponseEntity<com.thtf.common.vo.PageResult<com.thtf.item.model.Brand>>
     */
    @GetMapping("/list")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "keyword", required = false) String keyword ) {
        PageResult<Brand> result = brandService.queryBrandByPageAndSort(pageNum, rows, sortBy, desc, keyword);
        return ResponseEntity.ok(result);
    };
}
