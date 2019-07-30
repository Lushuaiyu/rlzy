package com.nado.rlzy.utils;

import java.math.BigDecimal;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/15 13:36
 * @Version 1.0
 */
@FunctionalInterface
public interface ToBigDecimalFunction<T> {
    BigDecimal applyAsBigDecimal(T value);

}