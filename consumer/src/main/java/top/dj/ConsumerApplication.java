package top.dj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    @RestController
    public class NacosDiscoveryConsumer {
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Value("${spring.application.name}")
        private String appName;

        @GetMapping("/consumer")
        public String getName() {
            // restTemplate + loadBalancerClient 的方式来访问服务提供者
            ServiceInstance instance = loadBalancerClient.choose("producer");
            String url = String.format("http://%s:%s/producer/%s", instance.getHost(), instance.getPort(), appName);
            return "consumer --> " + restTemplate.getForObject(url, String.class);
        }

        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
