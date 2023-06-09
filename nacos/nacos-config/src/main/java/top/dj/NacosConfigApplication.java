package top.dj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NacosConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        /*
            外部化配置文件的发生变动时，
            动态刷新，变动也会更新到 environment 中，
            在这里每隔 5s 打印一次配置输出。
         */
        int i = 0;
        while (i < 20) {
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String userAddr = applicationContext.getEnvironment().getProperty("user.addr");

            // 获取到 环境配置文件 中的配置内容
            System.out.println("user.name: " + userName + ", user.addr: " + userAddr + ", user.age: " + userAge);
            TimeUnit.SECONDS.sleep(7);
            i++;
        }
    }
}
