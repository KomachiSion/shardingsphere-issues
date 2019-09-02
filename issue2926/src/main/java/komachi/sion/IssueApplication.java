package komachi.sion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shardingsphere.core.parse.sql.segment.dml.pagination.PaginationValueSegment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import lombok.Getter;
import lombok.Setter;

/**
 * {@link org.apache.shardingsphere.core.optimize.sharding.segment.select.pagination.Pagination#getValue(PaginationValueSegment, List)}
 *
 * Use type Integer as default limit offset.
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
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        init(jdbcTemplate);
        findUsersByPageIntType(jdbcTemplate, 10, 20);
        findUsersByPageLongType(jdbcTemplate, 10L, 20L);
    }
    
    private static void init(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user(id bigint(20) NOT NULL COMMENT '主键ID',\n"
            + "erp_id int(11) NOT NULL COMMENT '分表erpId',\n"
            + "name varchar(30) DEFAULT NULL COMMENT '姓名',\n"
            + "age int(11) DEFAULT NULL COMMENT '年龄',\n"
            + "email varchar(50) DEFAULT NULL COMMENT '邮箱',\n"
            + "PRIMARY KEY (id))");
        jdbcTemplate.execute("TRUNCATE TABLE user");
    }
    
    public static List findUsersByPageIntType(JdbcTemplate jdbcTemplate, Integer begin, Integer end){
        String sql = "select id,name,age,email from user limit ?,? ";
        Object[] params = new Object[] {begin, end};
        return jdbcTemplate.query(sql, params, getUserMapper());
    }
    public static List findUsersByPageLongType(JdbcTemplate jdbcTemplate, Long begin, Long end){
        String sql = "select id,name,age,email from user limit ?,? ";
        Object[] params = new Object[] {begin, end};
        return jdbcTemplate.query(sql, params, getUserMapper());
    }
    
    private static ResultSetExtractor<List> getUserMapper() {
        return new ResultSetExtractor<List>() {
            @Override
            public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List result = new ArrayList();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setAge(resultSet.getInt(3));
                    user.setEmail(resultSet.getString(4));
                    result.add(user);
                }
                return result;
            }
        };
    }
    
    @Setter
    @Getter
    private static class User {
        private Long id;
        
        private String name;
        
        private int age;
        
        private String email;
    }
}
