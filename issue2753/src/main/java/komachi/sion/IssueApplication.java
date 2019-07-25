package komachi.sion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Fixed in 4.0.0-RC2
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS user_integral_details";
    
    private static final String CREATE_TABLE = "CREATE TABLE user_integral_details("
        + "`id` BIGINT NOT NULL PRIMARY KEY, "
        + "`user_id` INT NOT NULL, "
        + "`type` INT, "
        + "`reason` VARCHAR(50), "
        + "`create_date` DATETIME default current_timestamp, "
        + "`modify_date` DATETIME default current_timestamp on update current_timestamp "
        + ")";
    
    private static final String INSERT_DATA = "INSERT INTO user_integral_details (create_date, modify_date, reason, type, user_id) VALUES (?, ?, ?, ?, ?)";
    
    private static final String QUERY_DATA = "select * from user_integral_details";
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
            initEnvironment(connection);
            insertData(connection);
            query(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initEnvironment(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(DROP_TABLE);
        statement.execute(CREATE_TABLE);
        statement.close();
    }
    
    private static void insertData(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_DATA)) {
            statement.setObject(1, new Date());
            statement.setObject(2, null);
            statement.setObject(3, "test");
            statement.setObject(4, 1);
            statement.setObject(5, 22136);
            statement.executeUpdate();
        }
    }
    
    private static void query(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_DATA);
        printRs(resultSet);
        resultSet.close();
        statement.close();
    }
    
    private static void printRs(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        printLabel(metaData);
        printCaseSensitive(metaData);
        printData(resultSet, metaData);
    }
    
    private static void printLabel(ResultSetMetaData metaData) throws SQLException {
        StringBuilder lables = new StringBuilder("| ");
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            lables.append(metaData.getColumnLabel(i)).append(" | ");
        }
        System.out.println(lables.toString());
    }
    
    private static void printCaseSensitive(ResultSetMetaData metaData) throws SQLException {
        StringBuilder lables = new StringBuilder("| ");
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            lables.append(metaData.isCaseSensitive(i)).append(" | ");
        }
        System.out.println(lables.toString());
    }
    
    private static void printData(ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {
        StringBuilder lables = new StringBuilder("| ");
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                lables.append(resultSet.getObject(i)).append(" | ");
            }
            lables.append("\n");
        }
        System.out.println(lables.toString());
    }
}