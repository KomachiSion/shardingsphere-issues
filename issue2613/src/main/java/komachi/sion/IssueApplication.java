package komachi.sion;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

/**
 * ComplexShardingStrategy default RouteValue as ListRouteValue,
 * but when use `between and`, RouteValue is BetweenRouteValue(4.0.0-RC1) or RangeRouteValue(4.0.0-RC2+)
 * so maybe is a bug and may be fixed in 4.0.0-RC3
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    @SneakyThrows
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("select * from t_order where id between 1 and 10");
        }
    }
}