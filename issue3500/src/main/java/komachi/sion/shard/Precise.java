package komachi.sion.shard;

import java.util.Collection;
import java.util.Date;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/**
 *
 *
 * @author yangyi
 */
public class Precise implements PreciseShardingAlgorithm<Date> {
    
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        int year = preciseShardingValue.getValue().getYear() + 1900;
        int month = preciseShardingValue.getValue().getMonth() + 1;
        String tableSuffix = "" + year + month;
        for (String each : collection) {
            if (each.endsWith(tableSuffix)) {
                return each;
            }
        }
        return null;
    }
}
