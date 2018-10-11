package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 查找全部商品分类
     * @param cid 根据cid查询商品分类
     * @return 返回商品分类集
     */
    @Override
    public List<SpecGroup> querySpecGroup(Long cid) {
        SpecGroup t = new SpecGroup();
        t.setCid(cid);
        return this.specGroupMapper.select(t);
    }

    /**
     * 查找全部规格参数
     * @param gid 分组id
     * @return 返回规格参数集
     */
    @Override
    public List<SpecParam> querySpecParam(Long gid) {
        SpecParam t = new SpecParam();
        t.setGroupId(gid);
        return this.specParamMapper.select(t);
    }
}
