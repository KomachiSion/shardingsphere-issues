package komachi.sion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import komachi.sion.entity.CouponCode;

public interface CouponRepo extends JpaRepository<CouponCode, Long> {
}
