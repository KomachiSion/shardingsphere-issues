package komachi.sion.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import komachi.sion.sharding.DataRecord;

/**
 * @author : wangzhe
 * @date : Created in 2019-09-11 21:26
 * @description :
 */
@Mapper
public interface DataRecordMapper {

    DataRecord selectByPrimaryKey(@Param("id") Long uuid);

    void insert(DataRecord record);

    void update(DataRecord record);

    void delete(@Param("id") Long id);
}
