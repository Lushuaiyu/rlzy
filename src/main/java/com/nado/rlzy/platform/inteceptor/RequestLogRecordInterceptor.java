package com.nado.rlzy.platform.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright 2017 济中节能 All rights reserved.
 * Created by LiLei on 2017/9/29 16:35.
 */
@Component
public class RequestLogRecordInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogRecordInterceptor.class);

    // 前处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    // 后处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    // 页面响应成功
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HttpSession session = request.getSession();
//        TbSysUserEntity tbSysUserEntity = (TbSysUserEntity) session.getAttribute(Constants.SESSION_USER.name());
//        String ip = request.getRemoteAddr();//客户端IP
//        if ("0:0:0:0:0:0:0:1".equals(ip)) {
//            InetAddress addr = InetAddress.getLocalHost();
//            ip = addr.getHostAddress();//获得本机IP
//        }
//        String requestUrl = request.getRequestURI();
//        if (tbSysUserEntity != null) {
//            String username = tbSysUserEntity.getUsername();
//            if (handler instanceof ParameterizableViewController) {
//                ParameterizableViewController viewController = (ParameterizableViewController) handler;
//                String viewName = viewController.getViewName();
//                TbSysLogEntity tbSysLogEntity = new TbSysLogEntity(username, ip,
//                        viewName, requestUrl, "访问网页",
//                        null, "成功", null);
//                sysLogRepository.save(tbSysLogEntity);
//                logger.info("用户：{}，ip地址：{}，请求访问地址：{}，当前视图名称：{}", username, ip, requestUrl, viewName);
//            }
//        } else {
//            logger.info("未知用户，ip地址：{}，请求访问地址：{}", ip, requestUrl);
//        }
    }
}
