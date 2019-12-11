package sion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

/**
 * In router, for subQuery will check the sharding column status.
 * But the check will cast to the {@link org.apache.shardingsphere.core.strategy.route.value.ListRouteValue},
 * If the sharding value is range, the problem will happen.
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(final String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            
            statement.execute("DROP TABLE IF EXISTS `t_order`");
            statement.execute("CREATE TABLE IF NOT EXISTS `t_order` (order_id bigint primary key, user_id int, status varchar(50))");
            statement.execute("TRUNCATE `t_order`");
            insertData(connection);
            selectDatas(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void insertData(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `t_order` (order_id, user_id, status) VALUES (?, ?, ?)")) {
            preparedStatement.setObject(1, 100);
            preparedStatement.setObject(2, 99);
            preparedStatement.setObject(3, "xxxx");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void selectDatas(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from `t_order` where order_id=? and user_id=?")) {
            preparedStatement.setObject(1, 100);
            preparedStatement.setObject(2, 99);
            ResultSet resultSet = preparedStatement.executeQuery();
            printColumnName(3, resultSet.getMetaData());
            printColumnValue(3, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @SneakyThrows
    private static void printColumnName(int columnCount, ResultSetMetaData resultSetMetaData) {
        StringBuilder builder = new StringBuilder("|");
        for (int i = 1; i <= columnCount; i++) {
            builder.append(resultSetMetaData.getColumnLabel(i)).append("|");
        }
        System.out.println(builder.toString());
    }
    
    @SneakyThrows
    private static void printColumnValue(int columnCount, ResultSet resultSet) {
        while (resultSet.next()) {
            StringBuilder builder = new StringBuilder("|");
            for (int i = 1; i <= columnCount; i++) {
                builder.append(resultSet.getString(i)).append("|");
            }
            System.out.println(builder.toString());
        }
    }
    
    @SneakyThrows
    private static void printColumnValueByLabel(ResultSetMetaData resultSetMetaData, ResultSet resultSet) {
        while (resultSet.next()) {
            StringBuilder builder = new StringBuilder("|");
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                builder.append(resultSet.getString(resultSetMetaData.getColumnLabel(i))).append("|");
            }
            System.out.println(builder.toString());
        }
    }
}
