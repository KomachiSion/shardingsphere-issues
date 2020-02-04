package komachi.sion;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author yangyi
 */
@Slf4j
public class IssueStarter {

    public static void main(String[] args) throws IOException, SQLException {
        readFromYaml();
    }

    private static void readFromYaml() throws IOException, SQLException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(getFile("/META-INF/sharding-databases.yaml"));
    }

    private static File getFile(final String fileName) {
        return new File(Thread.currentThread().getClass().getResource(fileName).getFile());
    }
}
