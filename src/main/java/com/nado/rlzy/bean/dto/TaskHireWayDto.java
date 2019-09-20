package com.nado.rlzy.bean.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/9/20 15:02
 * @Version 1.0
 */
@Data
public class TaskHireWayDto {
    private LocalDateTime registerTime;

    private LocalDateTime interviewTime;
    private Integer hsdId;
}