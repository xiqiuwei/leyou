package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
//查询品牌
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            //当前第几页,如果前端没传过来请求数据那么默认值给第一页
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            //默认值每页给5条数据
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            //是否排序,不是必须的
            @RequestParam(value = "sortBy",required = false)String sortBy,
            //是否降序排列,否
            @RequestParam(value = "desc",defaultValue = "false")Boolean desc,
            //客户是否必须输入信息,否
            @RequestParam(value = "key",required = false)String key){

        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page,rows,sortBy,desc,key);
        //如果查询出的result为空或者没有信息数为0条的话返回状态值为notFound
        if (result == null || result.getItems().size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param brand 前端传过来的品牌信息
     * @param cids 前端传来的分类的id
     * @return 保存数据,没有返回值
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        this.brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
