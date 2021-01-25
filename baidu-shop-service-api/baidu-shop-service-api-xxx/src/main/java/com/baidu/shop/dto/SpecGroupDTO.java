package com.baidu.shop.dto;

import com.baidu.shop.validate.group.BaiduOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SpecGroupDTO
 * @Description: TODO
 * @Author shenao
 * @Date 2021/1/25
 * @Version V1.0
 **/
@ApiModel(value = "规格组数据传输DTO")
@Data
public class SpecGroupDTO {

    @ApiModelProperty(value = "主键",example = "1")
    @NotNull(message = "主键不能为空",groups = {BaiduOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value = "类型id",example = "1")
    @NotNull(message = "类型id不能为空",groups = {BaiduOperation.Add.class})
    private Integer cid;

    @ApiModelProperty(value = "规格组名称")
    @NotEmpty(message = "规格组名称不能为空",groups = {BaiduOperation.Add.class})
    private String name;
}