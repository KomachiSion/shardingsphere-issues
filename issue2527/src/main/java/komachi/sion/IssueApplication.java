package komachi.sion;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

/**
 * Fixed in 4.0.0-RC2.
 * ResultSetReturnedDatabaseMetaData has NULL shardingRule when use MasterSlaveDataSource,
 * so some method like getTable will throw NPE.
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
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, "user_role", new String[]{"TABLE"});
        while (resultSet.next()) {
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
    }
}
