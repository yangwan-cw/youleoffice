package com.ioomex.youleoffice.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author ioomex
 * @description run application
 */
public class ApplicationRunStarter {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunStarter.class);


    /**
     * 日志输出启动信息
     * @param env 环境变量
     */
    public static void logApplicationStartup(Environment env) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String path = env.getProperty("server.servlet.context-path", "");

            log.info("\n----------------------------------------------------------\n\t"
                + "Application is running! Access URLs:\n\t"
                + "Local: \t\thttp://localhost:{}{}/\n\t"
                + "swagger-ui: \thttp://{}:{}{}/swagger-ui.html\n\t"
                + "Doc: \t\thttp://{}:{}{}/doc.html\n"
                + "----------------------------------------------------------",
              port, path, ip, port, path, ip, port, path, ip, port, path);

            log.info("启动成功 V0.0.1 {}", System.currentTimeMillis());

        } catch (UnknownHostException e) {
            log.error("The host address could not be determined.", e);
        }
    }
}