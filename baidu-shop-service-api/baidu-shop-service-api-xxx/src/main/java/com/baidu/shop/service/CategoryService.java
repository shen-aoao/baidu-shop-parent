package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.validate.group.BaiduOperation;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品分类接口")
public interface CategoryService {

    @ApiOperation(value = "通过查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> getCategoryByid(Integer pid);

    @ApiOperation(value = "通过id删除商品分类")
    @DeleteMapping(value = "category/del")
    Result<JsonObject> deleteCategory(Integer id);

    @ApiOperation(value = "修改分类")
    @PutMapping(value = "category/edit")
    Result<JsonObject> editCategory(@Validated({BaiduOperation.Update.class})@RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "新增分类")
    @PostMapping(value = "category/add")
    Result<JsonObject> addCategory(@Validated({BaiduOperation.Add.class})@RequestBody CategoryEntity categoryEntity);
}
