package com.leyou.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPageAndSort(
            Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //开始分页,分页助手
        PageHelper.startPage(page,rows);
        //example实例Brand,和过滤
        Example example = new Example(Brand.class);
        //isnNotBlank判断非空和去空
        if (StringUtils.isNotBlank(key)){
            //拼接sql语句以客户输入的内容模糊查询
            example.createCriteria().andLike("name","%"+key+"%")
                    //字母都转成大写的
                    .orEqualTo("letter",key.toUpperCase());
        }
        if (StringUtils.isNotBlank(key)){
            //排序,三元判断true or false 降序 升序
            String orderByClause = sortBy+ (desc?" DESC":" ASC");
            example.setOrderByClause(orderByClause);
        }
        //根据实例查询当前页和当前页信息
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        //返回结果,当前页和当前页信息
        return new PageResult<Brand>(pageInfo.getTotal(),pageInfo);
    }

    /**
     * 不仅新增品牌还要维护品牌和商品分类中间表
     * @param brand
     * @param cids
     */
    @Transactional
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌信息
        this.brandMapper.insertSelective(brand);
        //新增品牌和分类中间表
       for (Long cid:cids){
           this.brandMapper.insertCategoryBrand(cid,brand.getId());
       }
    }


}
