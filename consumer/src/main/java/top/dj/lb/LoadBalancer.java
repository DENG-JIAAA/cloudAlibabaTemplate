package top.dj.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: DengJia
 * @Date: 2023/4/1 14:19
 * @Description: 自定义负载均衡器
 */

public interface LoadBalancer {
    /**
     * 获取一个服务实例
     *
     * @param serviceInstances 服务实例列表
     * @return ins
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
