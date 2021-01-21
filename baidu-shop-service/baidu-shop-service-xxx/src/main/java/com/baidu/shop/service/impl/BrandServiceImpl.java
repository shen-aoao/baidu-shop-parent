package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BrandServiceImpl
 * @Description: TODO
 * @Author shenao
 * @Date 2021/1/20
 * @Version V1.0
 **/
@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;


    @Override
    public Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO) {
        PageHelper.startPage(brandDTO.getPage(),brandDTO.getRows());
        if(!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrderBy());

        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO,BrandEntity.class);

        Example example = new Example(BrandEntity.class);
        example.createCriteria().andLike("name","%" + brandEntity.getName() + "%");

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);
        return this.setResultSuccess(pageInfo);
    }

    @Transactional
    @Override
    public Result<JSONObject> addBrandInfo(BrandDTO brandDTO) {
        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO,BrandEntity.class);
        brandMapper.insertSelective(brandEntity);

        String categories = brandDTO.getCategories();
        if(StringUtils.isEmpty(brandDTO.getCategories())) return this.setResultError("");
        List<CategoryBrandEntity> categoryBrandEntities = new ArrayList<>();

        if(categories.contains(",")){
            String[] categoryArr = categories.split(",");

            for(String s : categoryArr){
                CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
                categoryBrandEntity.setBrandId(brandEntity.getId());
                categoryBrandEntity.setCategoryId(Integer.valueOf(s));
                categoryBrandEntities.add(categoryBrandEntity);
            }
            categoryBrandMapper.insertList(categoryBrandEntities);
        }else{
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(brandEntity.getId());
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));

            categoryBrandMapper.insert(categoryBrandEntity);
        }
        return this.setResultSuccess();
    }
}
