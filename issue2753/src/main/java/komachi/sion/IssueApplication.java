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
 * refer to #2770
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS user_task_record";
    
    private static final String CREATE_TABLE = "CREATE TABLE user_task_record(id bigint(20) NOT NULL AUTO_INCREMENT,create_date datetime NOT NULL,modify_date datetime DEFAULT NULL,order_list bigint(20) DEFAULT NULL,clazz_id bigint(20) DEFAULT NULL,course_id bigint(20) DEFAULT NULL,date date DEFAULT NULL,organization_id bigint(20) DEFAULT NULL,project_id bigint(20) DEFAULT NULL,rewards float NOT NULL,task_id bigint(20) NOT NULL,task_name varchar(255) DEFAULT NULL,user_id bigint(20) NOT NULL, PRIMARY KEY (id) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4";
    
    private static final String INSERT_DATA = "insert into user_task_record (create_date, modify_date, order_list, clazz_id, course_id, date, organization_id, project_id, rewards, task_id, task_name, user_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String QUERY_DATA = "select * from user_task_record";
    
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
            statement.setObject(3, 1564113456471L);
            statement.setObject(4, null);
            statement.setObject(5, null);
            statement.setObject(6, new Date());
            statement.setObject(7, 120);
            statement.setObject(8, 589);
            statement.setObject(9, 3.0);
            statement.setObject(10, 345673456L);
            statement.setObject(11, "发帖");
            statement.setObject(12, 33);
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