package komachi.sion.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sharding_test")
public class ShardingTest extends Model<ShardingTest> {

    private static final long serialVersionUID = 1L;

    /** 编号 **/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 创建时间 **/
    @TableField("create_time")
    private Date createTime;

    /** 更新时间 **/
    @TableField("update_time")
    private Date updateTime;

    /** 标题 **/
    private String title;

    /** 内容 **/
    private String content;

    /** 排序 **/
    private Integer sort;

    /** 有效标识：1、有效；0、无效 **/
    @TableField("alive_flag")
    private Integer aliveFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
