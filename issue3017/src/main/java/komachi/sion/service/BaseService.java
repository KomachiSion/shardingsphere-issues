package komachi.sion.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>    {
}
