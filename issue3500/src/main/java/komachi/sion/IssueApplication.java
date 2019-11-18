package komachi.sion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.shardingsphere.core.merge.dql.DQLMergeEngine;
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
            
            statement.execute("DROP TABLE IF EXISTS `t_check_task`");
            statement.execute("CREATE TABLE IF NOT EXISTS `t_check_task` (id bigint primary key, executeTime datetime)");
            statement.execute("TRUNCATE `t_check_task`");
            insertData(connection);
            selectDatas(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void insertData(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `t_check_task` (executeTime) VALUES (?)")) {
            preparedStatement.setObject(1, new Date());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void selectDatas(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select count(1) from (SELECT id, executeTime FROM t_check_task WHERE executeTime between ? and ?)")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            preparedStatement.setObject(1, calendar.getTime());
            calendar.add(Calendar.MONTH, 2);
            preparedStatement.setObject(2, calendar.getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            printColumnName(2, resultSet.getMetaData());
            printColumnValue(2, resultSet);
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
