package com.nado.rlzy.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 参数校验
 *  例子:
 *  * CheckParametersUtil.getInstance()
 *  *          .put(loginUserId, "loginUserId")
 *  *          .put(branId, "branId")
 *  *          .put(shelfNo, "shelfNo")
 *  *          .put(newShelfNo, "newShelfNo")
 *  *          .checkParameter();
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 15:54
 * @Version 1.0
 */
public class CheckParametersUtil {
    Map<String, Object> map = new HashMap<>(16);
    /**
     * 添加需要校验的参数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:56 2019/7/1
     * @Param object 实参
     * @param parameterName 参数名称
     * @return com.nado.rlzy.utils.CheckParametersUtil
     **/
    public CheckParametersUtil put(Object object, String parameterName) {
        map.put(parameterName, object);
        return this;
    }

    /**
     * 获取CheckParametersUtil实例
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:57 2019/7/1
     * @Param []
     * @return com.nado.rlzy.utils.CheckParametersUtil
     **/
    public static CheckParametersUtil getInstance(){
        return new CheckParametersUtil();
    }

    /**
     * 校验
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:58 2019/7/1
     * @Param []
     * @return void
     **/
    public void checkParameter() throws Exception {
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            if(isEmpty(entry.getValue())){
                throw new Exception("参数【" + entry.getKey() + "】为空" );
            }
        }
    }

    public String toString(Object object) {
        return object == null ? "" : object.toString();
    }

    public boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public boolean isEmpty(String string) {
        return toString(string).isEmpty();
    }

    public boolean isEmptyTrim(String string) {
        return toString(string).trim().isEmpty();
    }

    public boolean isEmpty(Object object) {
        return toString(object).isEmpty();
    }

    public boolean isEmptyTrim(Object object) {
        return toString(object).trim().isEmpty();
    }

    public <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

}