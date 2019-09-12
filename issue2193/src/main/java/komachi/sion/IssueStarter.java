package komachi.sion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;


/**
 * route way bad
 *
 * @author yangyi
 */
public class IssueStarter {
    
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(IssueStarter.class.getResourceAsStream("/sharding-config-example-1.yaml"), "utf-8"));
        StringBuffer sb = new StringBuffer();
        CharBuffer charBuffer = CharBuffer.allocate(32);
        for (int count = reader.read(charBuffer); count > 0; count = reader.read(charBuffer)) {
            sb.append(charBuffer.flip());
        }
    
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(sb.toString().getBytes("utf-8"));
        Connection connection = dataSource.getConnection();
        Statement st = connection.createStatement();
//        st.executeUpdate("CREATE TABLE IF NOT EXISTS `ts_order` (`id` BIGINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (`id`));");
//        ResultSet rs = st.executeQuery("select * from ts_order where id = 3");
        ResultSet rs = st.executeQuery("select * from ts_order where id in (1, 3)");
    }
}
