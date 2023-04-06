package top.dj.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.dj.lb.LoadBalancer;

import java.util.List;

/**
 * @Author: DengJia
 * @Date: 2023/4/1 14:27
 * @Description: 消费者服务
 */

@RestController
@RefreshScope // 用来刷新配置信息，如果缺少，修改Nacos配置中心后，客户端已经引用到配置中的数据不会被刷新。
public class NacosDiscoveryConsumer {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.profiles.active}")
    private String active;

    /*
     * 设置属性默认值：false，目的在于增加一层容错机制，
     * 即使无法从 nacos 获取配置，应用程序也可以使用默认值完成启动加载。
     */
    @Value("${showFlag:false}")
    private boolean showFlag;

    /**
     * 消费者提供服务
     *
     * @return ret
     */
    @GetMapping("/consumer")
    public String getName() {

        List<ServiceInstance> instances = discoveryClient.getInstances("producer");
        ServiceInstance instance = loadBalancer.instances(instances);
        String url = String.format("http://%s:%s/producer", instance.getHost(), instance.getPort());
        return "服务调用链：" + appName + "[" + active + "] --> " + restTemplate.getForObject(url, String.class);
    }

    /**
     * 动态属性推送场景
     *
     * @return str
     */
    @GetMapping("/dynamic")
    public String dyna() {
        if (showFlag) {
            return "开启，判是。[" + active + "]";
        }
        return "关闭，判否。[" + active + "]";
    }
}
