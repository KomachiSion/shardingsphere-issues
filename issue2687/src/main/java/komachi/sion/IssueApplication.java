package komachi.sion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * fixed in 4.0.0-RC2
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS t";
    
//    private static final String CREATE_TABLE = "CREATE TABLE t(`id` INT NOT NULL PRIMARY KEY, `code` VARCHAR(40) NOT NULL) COLLATE='utf8mb4_unicode_ci'";
    private static final String CREATE_TABLE = "CREATE TABLE t(`id` INT NOT NULL PRIMARY KEY, `code` VARCHAR(40) NOT NULL) COLLATE='utf8mb4_bin'";
    
    private static final String INSERT_DATA = "INSERT INTO t (id, code) VALUE (%d, '%s')";
    
    private static final String QUERY_DATA = "select code from t group by code";
    
    private static final String QUERY_DATA_2 = "select max(code) from t group by upper(code)";
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
            initEnvironment(connection);
            query(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void initEnvironment(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(DROP_TABLE);
        statement.execute(CREATE_TABLE);
        statement.execute(String.format(INSERT_DATA, 11, "11035801128253"));
        statement.execute(String.format(INSERT_DATA, 13, "110358011282f5"));
        statement.execute(String.format(INSERT_DATA, 15, "110358011282G1"));
        statement.execute(String.format(INSERT_DATA, 17, "110358011282H5"));
        statement.execute(String.format(INSERT_DATA, 19, "110358011282T4"));
        statement.execute(String.format(INSERT_DATA, 21, "110358011282U5"));
        statement.execute(String.format(INSERT_DATA, 23, "110358011282z1"));
        statement.execute(String.format(INSERT_DATA, 22, "110358011282D4"));
        statement.execute(String.format(INSERT_DATA, 24, "110358011282E9"));
        statement.execute(String.format(INSERT_DATA, 26, "110358011282h8"));
        statement.execute(String.format(INSERT_DATA, 28, "110358011282I0"));
        statement.execute(String.format(INSERT_DATA, 30, "110358011282l1"));
        statement.execute(String.format(INSERT_DATA, 32, "110358011282O7"));
        statement.execute(String.format(INSERT_DATA, 34, "110358011282P0"));
        statement.execute(String.format(INSERT_DATA, 36, "110358011282T4"));
        statement.execute(String.format(INSERT_DATA, 38, "110358011282w6"));
        statement.close();
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
        printData(resultSet, metaData);
    }
    
    private static void printLabel(ResultSetMetaData metaData) throws SQLException {
        StringBuilder lables = new StringBuilder("| ");
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            lables.append(metaData.getColumnLabel(i)).append(" | ");
        }
        System.out.println(lables.toString());
    }
    
    private static void printData(ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {
        StringBuilder lables = new StringBuilder("| ");
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                lables.append(resultSet.getString(i)).append(" | ");
            }
            lables.append("\n");
        }
        System.out.println(lables.toString());
    }
}