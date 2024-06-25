package com.ioomex.youleoffice;

import com.ioomex.youleoffice.starter.ApplicationRunStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ServletComponentScan
public class YouleofficeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(YouleofficeApplication.class, args);
        Environment env = run.getEnvironment();
        ApplicationRunStarter.logApplicationStartup(env);
    }

}
