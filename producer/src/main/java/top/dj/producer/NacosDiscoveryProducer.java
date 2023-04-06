package top.dj.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DengJia
 * @Date: 2023/4/1 14:37
 * @Description: 生产者服务
 */

@RestController
@RefreshScope
public class NacosDiscoveryProducer {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${showFlag:false}")
    private boolean showFlag;

    @GetMapping("/producer")
    public String getName() {
        return "nacos discovery producer.[" + active + "]";
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
