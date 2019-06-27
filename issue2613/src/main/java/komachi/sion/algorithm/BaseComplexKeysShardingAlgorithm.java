package komachi.sion.algorithm;

import java.util.Collection;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author yangyi
 */
@Slf4j(topic = "sharding")
public class BaseComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        log.info("availableTargetNames: {}\nshardingValue: {}", availableTargetNames, shardingValue);
        return availableTargetNames;
    }
    
}