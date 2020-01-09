package komachi.sion.algotirhm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author yangyi
 */
@Slf4j
public class OrderTableShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {
    
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String tableNode=null;
        Date createDate = DateUtil.parseDateFromStrYyyyMMdd2(preciseShardingValue.getValue());
        Calendar c = Calendar.getInstance();
        c.setTime(createDate);
        int year = c.get(Calendar.YEAR);
        int season = DateUtil.getSeason(createDate);
        log.debug("按照季度进行数据分片，精准查询，当前为第{}年，第{}季度：",year,season);
        for(Object obj:collection){
            String oneNode=obj+"";
            if(oneNode.endsWith(year+""+season)){
                tableNode = oneNode;
                break;
            }
        }
        
        return tableNode;
    }
    
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        Collection<String> collect = new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        String dateUpperStr=rangeShardingValue.getValueRange().upperEndpoint();
        String dateLowerStr=rangeShardingValue.getValueRange().lowerEndpoint();
        Date dateUpper=DateUtil.parseDateFromStrYyyyMMdd2(dateUpperStr);
        Date dateLower=DateUtil.parseDateFromStrYyyyMMdd2(dateLowerStr);
        
        calendar.setTime(dateUpper);
        int yearUpper=calendar.get(Calendar.YEAR);
        int seasonUpper = DateUtil.getSeason(dateUpper);
        
        calendar.setTime(dateLower);
        int yearLower=calendar.get(Calendar.YEAR);
        int seasonLower = DateUtil.getSeason(dateLower);
        boolean add = false;
        for(String obj:collection){
            String tableNoe=obj+"";
            if(!add){
                if(tableNoe.endsWith(yearLower+""+seasonLower)){
                    add = true;
                }
            }
            if(tableNoe.endsWith(yearUpper+""+seasonUpper)){
                collect.add(tableNoe);
                break;
            }
            if(add){
                collect.add(tableNoe);
            }
        }
        log.debug("按照季度进行数据分片，范围查询，当前范围：{}", Arrays.toString(collection.toArray()));
        return collect;
    }
}
