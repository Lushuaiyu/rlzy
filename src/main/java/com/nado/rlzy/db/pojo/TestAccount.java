package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lushuaiyu
 */
@Data
public class TestAccount {
    private Integer id;

    private String name;

    private BigDecimal money;


}