package com.nado.rlzy.utils;

import com.nado.rlzy.platform.exception.AssertException;

public class AssertUtil {

    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new AssertException(msg);
        }
    }

}



