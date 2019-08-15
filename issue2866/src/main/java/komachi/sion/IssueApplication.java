package komachi.sion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Configuration mistake.
 *
 */
@SpringBootApplication
public class IssueApplication {
    
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(IssueApplication.class, args)) {
            process(applicationContext);
        }
    }
    
    private static void process(ConfigurableApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into party (party_nickname, party_nickname_spelling, sex, birthday) values (?, ?, ?, ?)")) {
            statement.execute("CREATE TABLE IF NOT EXISTS party (party_id bigint primary key, party_nickname varchar(50), party_nickname_spelling varchar(50), sex int, birthday date)");
            preparedStatement.setObject(1, "test");
            preparedStatement.setObject(2, "test");
            preparedStatement.setObject(3, 1);
            preparedStatement.setObject(4, new Date());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
