package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     *
     * @param pid
     * @return
     */
    List<Category> queryListByParent(Long pid);

    /**
     *
     * @param bid
     * @return
     */
    List<Category> queryByBrandId(Long bid);

}
