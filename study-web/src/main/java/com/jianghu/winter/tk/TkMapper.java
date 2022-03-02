package com.jianghu.winter.tk;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: daniel.hu
 * @Date: 2020/12/30 14:57
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T>, InsertIgnoreListMapper<T> {
}
