package komachi.sion;

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
 * The reason is Standard routing engine do not get sharding value from database only method rather get values from logic table method.
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String CREATE_TABLE = "CREATE TABLE sample_table (id int primary key AUTO_INCREMENT, client varchar(10), source varchar(10), ticket_id int)";
    
    public static void main(final String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
            initEnvironment(connection);
            insertData(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initEnvironment(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `sample_table`");
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void insertData(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("insert into sample_table (client, source, ticket_id) values (?, ?, ?)")) {
            statement.setObject(1, "a");
            statement.setObject(2, "a");
            statement.setObject(3, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void selectDatas(Connection connection) {
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
