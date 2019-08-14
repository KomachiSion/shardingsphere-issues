package komachi.sion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_order", indexes = {
    @Index(name = "idx_order_id", columnList = "order_id")
})
@Getter
@Setter
public class Order {
    
    @Id
    private long id;
    
    @Column(name = "order_id")
    private int orderId;
    
    @Column
    private String status;
}
