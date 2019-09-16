package komachi.sion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.shardingsphere.core.merge.dql.DQLMergeEngine;
import org.apache.shardingsphere.core.optimize.sharding.statement.dml.ShardingSelectOptimizedStatement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

/**
 * OrderByItem include the alias information, by the value label come from ResultSet and ResultSetMataData do not include alias.
 * So the order by in ShardingSphere between multiple shard nodes will use the first index of OrderByItem which is same column label.
 *
 * Related Code: {@link DQLMergeEngine#merge()} and {@link ShardingSelectOptimizedStatement#setIndexForItems(Map)}
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
            statement.execute("DROP TABLE IF EXISTS `t_order_item`");
            statement.execute("CREATE TABLE IF NOT EXISTS `t_order` (order_id bigint primary key, user_id int, status varchar(32))");
            statement.execute("CREATE TABLE IF NOT EXISTS `t_order_item` (order_item_id bigint primary key, order_id bigint, user_id int, status varchar(32))");
            statement.execute("TRUNCATE `t_order`");
            statement.execute("TRUNCATE `t_order_item`");
            statement.execute("INSERT INTO `t_order` (order_id, user_id, status) VALUES (1, 1, '1'), (2, 2, '1'), (3, 3, '1'), (4, 4, '3'), (5, 5, '3')");
            statement.execute("INSERT INTO `t_order_item` (order_item_id, order_id, user_id) VALUES (1, 1, 1), (2, 2, 2), (3, 3, 1), (4, 4, 4), (5, 5, 5), (6, 6, 6)");
//            ResultSet resultSet = statement.executeQuery("SELECT i.user_id ,o.user_id FROM t_order o, t_order_item i WHERE o.order_id = i.order_id order by i.user_id");
//            ResultSet resultSet = statement.executeQuery("SELECT i.user_id ,o.user_id FROM t_order o, t_order_item i WHERE o.order_id = i.order_id order by o.user_id");
//            ResultSet resultSet = statement.executeQuery("SELECT i.user_id as uid ,o.user_id FROM t_order o, t_order_item i WHERE o.order_id = i.order_id order by i.user_id");
            ResultSet resultSet = statement.executeQuery("SELECT i.user_id as uid ,o.user_id FROM t_order o, t_order_item i WHERE o.order_id = i.order_id order by uid");
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
