package komachi.sion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import komachi.sion.entity.Order;
import komachi.sion.repo.OrderMapper;

/**
 *
 *
 * @author yangyi
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    public void createEnvironment() {
        orderMapper.createTable();
    }
    
    public void cleanEnvironment() {
        orderMapper.dropTable();
    }
    
    public void insertOneData() {
        Order order = new Order();
        order.setRecepit("insert test");
        orderMapper.insert(order);
        System.out.println(order.getOrderId());
    }
}
