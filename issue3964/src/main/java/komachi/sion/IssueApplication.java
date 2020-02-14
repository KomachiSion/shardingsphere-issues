package komachi.sion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

/**
 * The reason is Standard routing engine do not get sharding value from database only method rather get values from logic table method.
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String CREATE_TABLE = "";
    
    public static void main(final String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
//            initEnvironment(connection);
//            insertData(connection);
            selectDatas(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initEnvironment(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `tunnel_order`");
            statement.execute(CREATE_TABLE);
            statement.execute("TRUNCATE `tunnel_order`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void insertData(Connection connection) {
        HintManager.getInstance().setDatabaseShardingValue("a");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO t_order (order_id, user_id, status) values (1, 1, 'a')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void selectDatas(Connection connection) {
        HintManager.getInstance().setDatabaseShardingValue("a");
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from t_order")) {
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
