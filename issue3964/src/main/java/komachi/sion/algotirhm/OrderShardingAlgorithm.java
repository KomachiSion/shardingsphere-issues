package komachi.sion.algotirhm;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

/**
 *
 *
 * @author yangyi
 */
public class OrderShardingAlgorithm implements HintShardingAlgorithm<String> {
    
    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final HintShardingValue<String> shardingValue) {
        Collection<String> values = shardingValue.getValues();
        Collection<String> result = new LinkedList<>();
        for (String each : values) {
            for (String eachTarget : availableTargetNames) {
                if (eachTarget.endsWith(each)) {
                    result.add(eachTarget);
                }
            }
        }
        return result;
    }
}
