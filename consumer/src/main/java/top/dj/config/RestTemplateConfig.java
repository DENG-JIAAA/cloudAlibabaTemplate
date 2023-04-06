package top.dj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: DengJia
 * @Date: 2023/4/2 16:58
 * @Description:
 */

@Component
public class RestTemplateConfig {
    @Bean
    @Lazy
    RestTemplate restTemplate() {
        // @Lazy 注解避免循环依赖
        return new RestTemplate();
    }
}
