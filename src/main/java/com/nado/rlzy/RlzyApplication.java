package com.nado.rlzy;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @EnableTransactionManagement 启注解事务管理 等同于xml配置的方式 <tx:annotation-driven />
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 10:06 2019/7/5
 * @Param
 * @return
 **/
@EnableTransactionManagement
@SpringBootApplication
@Slf4j
@EnableWebMvc

@MapperScan(basePackages = "com.nado.rlzy.db.mapper")
public class RlzyApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RlzyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RlzyApplication.class, args);
        log.info("Chapter22启动!");

    }


    /**
     * 解决上传文件大于10M出现连接重置的问题。此异常内容 GlobalException 也捕获不到。
     *
     * @return org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:45 2019/7/1
     * @Param []
     **/
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

}
