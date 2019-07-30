package com.nado.rlzy.platform.exception;

import com.nado.rlzy.bean.model.ResponseBean;
import com.nado.rlzy.platform.emums.UnicomResponseEnums;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截404的错误
 *
 * @ClassName
 * @Description TODO
 * @Author ar
 * @Data 2019/5/20 19:48
 * @Version 1.0
 */
@RestController
public class MyBasicErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    public static final Integer STSTUS_404 = 404;

    @RequestMapping("/error")
    public ResponseBean<Object> handleError(HttpServletRequest request) {

        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (STSTUS_404.equals(statusCode)) {
            return new ResponseBean<>(false, null, UnicomResponseEnums.NOT_FOUND);
        } else {
            return new ResponseBean<>(false, null, UnicomResponseEnums.METHOD_NOT_ALLOWED);
        }

    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}