package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 关注 入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 10:46
 * @Version 1.0
 */
@Data
public class AttentionQuery {
    /**
     * 关注者id
     */
    @ApiModelProperty(value = "关注者id")
    private Integer userId;

    /**
     * 被关注者id
     */
    @ApiModelProperty(value = "被关注者id")
    private Integer useredId;

    /**
     * 关注的表id
     */
    @ApiModelProperty(value = "关注的表id")
    private Integer objectId;

    @ApiModelProperty(value = "招聘端 关注 求职表 1, 求职端关注简章表 0")
    private Integer type;

    @ApiModelProperty(value = "关注状态")
    private Integer status;
}