package komachi.sion.shard;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author yangyi
 */
@Slf4j
public class Range implements RangeShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Date start = shardingValue.getValueRange().lowerEndpoint();
        Date end = shardingValue.getValueRange().upperEndpoint();
        int cr = DateUtil.getMonthsBetween(start, end);
        List<String> das = Lists.newArrayList();
        for (int i = 0; i < cr; i++) {
            Date date = DateUtil.addMonths(start, i + 1);
            das.add(DateUtil.formatDate(date, "yyyyMM"));
        }
        // 获取数据
        Set<String> tables = Sets.newTreeSet();
        //符合条件的表
        for (String da : das) {
            for (String target : availableTargetNames) {
                if (target.contains(da)) {
                    tables.add(target);
                }
            }
        }
        log.info("目标表:" + JSON.toJSONString(tables));
        return tables;
    }
}
