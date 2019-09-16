package com.nado.rlzy.db.pojo.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-11 11:56
 * @Version 1.0
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5837689663954337199L;
    private String name;

    private Integer age;

    private Integer sex;
}
