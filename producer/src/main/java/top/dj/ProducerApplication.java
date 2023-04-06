package top.dj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ProducerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        String appName = context.getEnvironment().getProperty("spring.application.name");
        String active = context.getEnvironment().getProperty("spring.profiles.active");
        System.out.println(appName + " 为您提供服务！当前环境为：" + active + "。");
    }
}
