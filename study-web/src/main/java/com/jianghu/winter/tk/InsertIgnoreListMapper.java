package com.jianghu.winter.tk;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 通用Mapper接口,批量插入
 *
 * @Author: daniel.hu
 * @Date: 2021/1/29 10:19
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface InsertIgnoreListMapper<T> {
    /**
     * 批量插入，过滤重复记录（主键primary或者UNIQUE约束）
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true)
    @InsertProvider(type = TkInsertIgnoreListProvider.class, method = "dynamicSQL")
    int insertIgnoreList(List<? extends T> recordList);
}
