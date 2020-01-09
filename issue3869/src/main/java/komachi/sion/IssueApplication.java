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
 * The reason is `ifnull` method covered `sum` method. Merge Engine found that the `amount` is the result
 * of `ifnull` which is not a aggregation, so it replace the result instead of sum results.
 *
 * The solution if use `sum` method to covered `ifnull` method.
 *
 * @author yangyi
 */
@SpringBootApplication
public class IssueApplication {
    
    private static final String CREATE_TABLE = "CREATE TABLE tunnel_order (\n"
        + "  id bigint(20) NOT NULL primary key AUTO_INCREMENT,\n"
        + "  line_order_id varchar(50) DEFAULT NULL,\n"
        + "  line_no int(11) DEFAULT NULL,\n"
        + "  plate_no varchar(10) DEFAULT NULL,\n"
        + "  plate_color varchar(2) DEFAULT NULL,\n"
        + "  pay_type char(1) DEFAULT NULL,\n"
        + "  carry_cnt int(11) DEFAULT NULL,\n"
        + "  vehicle_type char(1) DEFAULT NULL,\n"
        + "  pay_amount int(11) DEFAULT NULL,\n"
        + "  act_amount int(11) DEFAULT NULL,\n"
        + "  coupon_amount int(11) DEFAULT NULL,\n"
        + "  order_status char(1) DEFAULT NULL,\n"
        + "  pay_time datetime DEFAULT NULL,\n"
        + "  pay_success_time datetime DEFAULT NULL,\n"
        + "  pass_time datetime DEFAULT NULL,\n"
        + "  shift char(1) DEFAULT NULL,\n"
        + "  shift_date varchar(10) DEFAULT NULL,\n"
        + "  order_type char(1) DEFAULT NULL,\n"
        + "  charger_id varchar(32) DEFAULT NULL,\n"
        + "  charger_name varchar(20) DEFAULT NULL,\n"
        + "  vehicle_id varchar(32) DEFAULT NULL,\n"
        + "  user_id varchar(32) DEFAULT NULL,\n"
        + "  user_name varchar(50) DEFAULT NULL,\n"
        + "  pay_order_no varchar(32) DEFAULT NULL,\n"
        + "  offline_record_id varchar(50) DEFAULT NULL,\n"
        + "  is_lease char(1) DEFAULT 'N',\n"
        + "  img_url varchar(50) DEFAULT NULL,\n"
        + "  create_by varchar(30) DEFAULT NULL,\n"
        + "  create_time datetime DEFAULT NULL,\n"
        + "  update_by varchar(30) DEFAULT NULL,\n"
        + "  update_time datetime DEFAULT NULL,\n"
        + "  logic_delete char(1) DEFAULT '0',\n"
        + "  create_date varchar(10) DEFAULT NULL,\n"
        + "  obu_id varchar(50) DEFAULT NULL,\n"
        + "  fee_seri_no varchar(32) DEFAULT NULL\n"
        + ")\n"
        + "ENGINE = INNODB\n"
        + "CHARACTER SET utf8\n"
        + "COLLATE utf8_general_ci";
    
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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO tunnel_order(id, line_order_id, line_no, plate_no, plate_color, pay_type, carry_cnt, vehicle_type, pay_amount, act_amount, order_status, pay_time, pay_success_time, pass_time, shift, shift_date, order_type, charger_id, charger_name, vehicle_id, user_id, user_name, pay_order_no, offline_record_id, is_lease, img_url, create_by, create_time, update_by, update_time, logic_delete, create_date, obu_id, fee_seri_no) VALUES"
                + "(1212040165892034562, '116201912312357519120', 16, '鲁B11111', '02', '1', 5, '1', 1000, 1000, '1', '2019-12-31 23:57:51', '2019-12-31 23:57:49', '2019-12-31 23:57:51', '1', '2020-01-01', '1', 'b0109', 'xxxx', NULL, 'MTC', 'MTC', '8aaa84ba6e224f71016f5cad73a57e28', NULL, 'N', '116201912312357510', 'b0109', '2019-12-31 23:57:49', NULL, '2019-12-31 23:57:50', '0', '2019-12-31', NULL, NULL)");
            statement.executeUpdate("INSERT INTO tunnel_order(id, line_order_id, line_no, plate_no, plate_color, pay_type, carry_cnt, vehicle_type, pay_amount, act_amount, order_status, pay_time, pay_success_time, pass_time, shift, shift_date, order_type, charger_id, charger_name, vehicle_id, user_id, user_name, pay_order_no, offline_record_id, is_lease, img_url, create_by, create_time, update_by, update_time, logic_delete, create_date, obu_id, fee_seri_no) VALUES\n"
                + "(1212040302508904449, '119201912312358244810', 19, '鲁B22222', '02', '0', 5, '1', 1000, 1000, '1', '2019-12-31 23:58:24', '2019-12-31 23:58:22', '2019-12-31 23:58:24', '1', '2020-01-01', '1', 'b0283', 'xxxx', NULL, 'MTC', 'MTC', '8aaa85d56e224f71016f5cadf2e17527', NULL, 'N', '119201912312358240', 'b0283', '2019-12-31 23:58:21', NULL, '2019-12-31 23:58:23', '0', '2019-12-31', NULL, NULL)");
            statement.executeUpdate("INSERT INTO tunnel_order(id, line_order_id, line_no, plate_no, plate_color, pay_type, carry_cnt, vehicle_type, pay_amount, act_amount, order_status, pay_time, pay_success_time, pass_time, shift, shift_date, order_type, charger_id, charger_name, vehicle_id, user_id, user_name, pay_order_no, offline_record_id, is_lease, img_url, create_by, create_time, update_by, update_time, logic_delete, create_date, obu_id, fee_seri_no) VALUES\n"
                + "(1212040708349702145, '116202001010000012300', 16, '京F33333', '02', '0', 5, '1', 1000, 300, '1', '2020-01-01 00:00:01', '2019-12-31 23:59:59', '2020-01-01 00:00:01', '1', '2020-01-01', '1', 'b0109', 'xxxx', NULL, 'MTC', 'MTC', '8aaa84ba6e224f71016f5caf6cd97e2c', NULL, 'N', '116202001010000010', 'b0109', '2019-12-31 23:59:58', NULL, '2020-01-01 00:00:00', '0', '2020-01-01', NULL, NULL)");
            statement.executeUpdate("INSERT INTO tunnel_order(id, line_order_id, line_no, plate_no, plate_color, pay_type, carry_cnt, vehicle_type, pay_amount, act_amount, order_status, pay_time, pay_success_time, pass_time, shift, shift_date, order_type, charger_id, charger_name, vehicle_id, user_id, user_name, pay_order_no, offline_record_id, is_lease, img_url, create_by, create_time, update_by, update_time, logic_delete, create_date, obu_id, fee_seri_no) VALUES\n"
                + "(1212040709268254722, '121202001010000014510', 21, '鲁44444', '02', '1', 5, '1', 1000, 500, '1', '2020-01-01 00:00:01', '2019-12-31 23:59:59', '2020-01-01 00:00:01', '1', '2020-01-01', '1', 'b0242', 'xxxx', NULL, 'MTC', 'MTC', '8aaa84ba6e224f71016f5caf6db57e2d', NULL, 'N', '121202001010000010', 'b0242', '2019-12-31 23:59:58', NULL, '2019-12-31 23:59:59', '0', '2020-01-01', NULL, NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void selectDatas(Connection connection) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT ifnull(sum(act_amount),0) as amount,pay_type FROM tunnel_order WHERE (order_status = ? AND date_format(pay_success_time,'%Y-%m-%d') = ? AND logic_delete = ? AND order_type = ? AND create_date BETWEEN ? AND ?) GROUP BY pay_type")) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT sum(ifnull(act_amount, 0)) as amount,pay_type FROM tunnel_order WHERE (order_status = ? AND date_format(pay_success_time,'%Y-%m-%d') = ? AND logic_delete = ? AND order_type = ? AND create_date BETWEEN ? AND ?) GROUP BY pay_type")) {
            preparedStatement.setObject(1, 1);
            preparedStatement.setObject(2, "2019-12-31");
            preparedStatement.setObject(3, 0);
            preparedStatement.setObject(4, 1);
            preparedStatement.setObject(5, "2019-12-31");
            preparedStatement.setObject(6, "2020-01-01");
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
