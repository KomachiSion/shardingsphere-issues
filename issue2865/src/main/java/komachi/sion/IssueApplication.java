package komachi.sion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import komachi.sion.entity.Order;
import komachi.sion.repository.OrderRepo;

/**
 * Try to reproduce issue, but failed.
 *
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        OrderRepo repo = applicationContext.getBean(OrderRepo.class);
        List<Order> list = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            Order order = new Order();
            order.setId(i);
            order.setOrderId(i);
            order.setStatus("TEST");
            list.add(order);
        }
        repo.saveAll(list);
    }
}
