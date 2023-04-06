package top.dj.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: DengJia
 * @Date: 2023/4/1 14:20
 * @Description:
 */

@Component
public class LoadBalancerImpl implements LoadBalancer {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    /**
     * 采用轮询的负载均衡方式
     *
     * @return 服务实例下标
     */
    public final int getAndIncrement() {
        int current, next;
        do {
            current = this.atomicInteger.get();
            next = current == Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("第 " + next + " 次访问");
        return next;
    }
}
