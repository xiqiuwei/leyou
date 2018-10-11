package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec/")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 查询get请求
     * 请求路径spec/groups/{cid}
     * @param cid 分类id
     * @return 页面把List<SpecGroup> 结果集赋值给groups
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroup(@PathVariable("cid")Long cid){
        List<SpecGroup> list = this.specificationService.querySpecGroup(cid);
        //判断
        if (list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
     }

    /**
     * 查询get请求
     * 请求路径spec/params
     * @param gid 分组id
     * @return 该分组下规格参数集合 List<SpecParam>
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParam(@RequestParam(value = "gid" ,required = false)Long gid){
        List<SpecParam> list = specificationService.querySpecParam(gid);
        if (list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
