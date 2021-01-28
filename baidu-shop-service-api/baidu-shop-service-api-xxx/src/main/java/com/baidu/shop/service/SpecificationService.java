package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.validate.group.BaiduOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格接口")
public interface SpecificationService {

    @ApiModelProperty(value = "通过条件查询规格组")
    @GetMapping(value = "specgroup/getSpecGroupInfo")
    Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO);

    @ApiModelProperty(value = "新增规格组")
    @PostMapping(value = "specgroup/save")
    Result<JSONObject> addGroupInfo(@Validated({BaiduOperation.Add.class}) @RequestBody SpecGroupDTO specGroupDTO);

    @ApiModelProperty(value = "修改规格组")
    @PutMapping(value = "specgroup/save")
    Result<JSONObject> editGroupInfo(@Validated({BaiduOperation.Update.class}) @RequestBody SpecGroupDTO specGroupDTO);

    @ApiModelProperty(value = "删除规格组")
    @DeleteMapping(value = "specgroup/delete{id}")
    Result<JSONObject> deleteSpecGroupById(@PathVariable Integer id);

    @ApiModelProperty(value = "查询规格参数")
    @GetMapping(value = "specparam/getSpecParamInfo")
    Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO);

    @ApiModelProperty(value = "新增规格参数")
    @PostMapping(value = "specparam/save")
    Result<JSONObject> addParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiModelProperty(value = "修改规格参数")
    @PutMapping(value = "specparam/save")
    Result<JSONObject> editParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiModelProperty(value = "删除规格参数")
    @DeleteMapping(value = "specparam/delete")
    Result<JSONObject> deleteParam(Integer id);
}
