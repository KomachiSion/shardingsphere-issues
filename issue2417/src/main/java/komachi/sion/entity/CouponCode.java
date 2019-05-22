package komachi.sion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cpn_code", indexes = {
    @Index(name = "idx_cpnId", columnList = "cpnId")
})
@Getter
@Setter
public class CouponCode {
    
    @Id
    private long id;
    
    @Column
    private int cpnId;
}
