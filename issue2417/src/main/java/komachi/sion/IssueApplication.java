package komachi.sion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import komachi.sion.entity.CouponCode;
import komachi.sion.repository.CouponRepo;

/**
 * Use 4.0.0-RC1 reproduce issue.
 * Fixed in 4.0.0-RC2-SNAPSHOT.
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        CouponRepo repo = applicationContext.getBean(CouponRepo.class);
        CouponCode code = new CouponCode();
        code.setCpnId(1);
        code.setId(1L);
        repo.save(code);
    }
}
