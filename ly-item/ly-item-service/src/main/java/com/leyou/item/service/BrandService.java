package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;

import java.util.List;

public interface BrandService {
    PageResult<Brand> queryBrandByPageAndSort(
            Integer page,
            Integer rows,
            String sortBy,
            Boolean desc,
            String key);

    /**
     *
     * @param brand
     * @param cids
     */
    void saveBrand(Brand brand, List<Long> cids);


}
