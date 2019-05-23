package com.thtf.item.controller;

import com.thtf.common.vo.PageResult;
import com.thtf.item.model.Brand;
import com.thtf.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @author: pyy
     * @date: 2019/5/23 8:53
     * @param pageNum
     * @param rows
     * @param sortBy
     * @param desc
     * @param keyword
     * @return: org.springframework.http.ResponseEntity<com.thtf.common.vo.PageResult<com.thtf.item.model.Brand>>
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
    }

    /**
     * 品牌添加
     * @author: pyy
     * @date: 2019/5/23 8:54
     * @param brand
     * @param cids
     * @return: org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
