package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecificationService {
    /**
     * 查找全部商品分类
     * @param cid 根据cid查询商品分类
     * @return 返回商品分类集
     */
    List<SpecGroup> querySpecGroup(Long cid);

    /**
     * 查找全部规格参数
     * @param gid 分组id
     * @return 返回规格参数集
     */
    List<SpecParam> querySpecParam(Long gid);

}
