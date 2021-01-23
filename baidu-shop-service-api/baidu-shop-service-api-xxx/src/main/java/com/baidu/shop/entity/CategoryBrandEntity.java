package com.baidu.shop.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @ClassName CategoryBrandEntity
 * @Description: TODO
 * @Author shenao
 * @Date 2021/1/21
 * @Version V1.0
 **/
@Data
@Table(name="tb_category_brand")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBrandEntity {

    private Integer categoryId;

    private Integer brandId;
}
