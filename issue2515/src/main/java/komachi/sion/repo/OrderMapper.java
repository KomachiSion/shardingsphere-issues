package komachi.sion.repo;

import komachi.sion.entity.Order;

/**
 *
 *
 * @author yangyi
 */
public interface OrderMapper {
    
    void createTable();
    
    void dropTable();
    
    int insert(Order order);
}
