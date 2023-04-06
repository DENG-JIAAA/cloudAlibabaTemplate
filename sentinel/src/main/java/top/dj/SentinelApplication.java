package top.dj;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

    @RestController
    public class SentinelController {
        @Autowired
        private SentinelService sentinelService;

        @GetMapping("/sentinel")
        public void caller() {
            sentinelService.sentinelResource();
        }

    }

    @Service
    public class SentinelService {
        @SentinelResource
        public void sentinelResource() {
            System.out.println("hello sentinel");
        }
    }

}
