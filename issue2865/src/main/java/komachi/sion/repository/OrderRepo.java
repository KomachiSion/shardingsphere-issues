package komachi.sion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import komachi.sion.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
