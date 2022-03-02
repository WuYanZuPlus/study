package com.jianghu.winter.tk;

import org.apache.ibatis.session.RowBounds;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 基于通用Mapper的抽象service实现，所有需要基础Mapper的基础方法的Service类皆可继承该类
 *
 * @Author: daniel.hu
 * @Date: 2020/12/30 14:16
 */
@SuppressWarnings({"unused"})
public abstract class AbstractTkService<T> {
    /**
     * 抽象获取mapper方法
     *
     * @return 实体T对应的Mapper
     */
    public abstract TkMapper<T> getMapper();

    /**
     * 根据主键id获取实体
     */
    public T get(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
    public T get(T entity) {
        return getMapper().selectOne(entity);
    }

    /**
     * 根据Example条件进行查询，只能有一个返回值
     */
    public T get(Example example) {
        return getMapper().selectOneByExample(example);
    }

    /**
     * 查询全部结果
     */
    public List<T> find() {
        return getMapper().selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    public List<T> find(T entity) {
        return getMapper().select(entity);
    }

    /**
     * 根据Example条件进行查询
     */
    public List<T> find(Example example) {
        return getMapper().selectByExample(example);
    }

    /**
     * @Name: 通过id集合查询列表
     * @Author: eric.xu
     * @Description:
     * @Date: 2021/6/10 11:17
     */
    public List<T> find(Collection<Long> ids) {
        List<T> ans = new LinkedList<>();
        if (CollectionUtils.isEmpty(ids)) {
            return ans;
        }
        Class<T> clazz = (Class<T>) getParameterizedType();
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return find(example);
    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     */
    public int save(T entity) {
        return getMapper().insert(entity);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    public int saveExcludeNull(T entity) {
        return getMapper().insertSelective(entity);
    }

    /**
     * 批量保存（限制实体包含`id`属性并且必须为自增列）
     */
    public int save(Collection<? extends T> collections) {
        if (CollectionUtils.isEmpty(collections)) {
            return 0;
        }
        return getMapper().insertList(new ArrayList<>(collections));
    }

    /**
     * 批量插入（限制实体包含`id`属性并且必须为自增列），过滤重复记录（主键primary或者UNIQUE约束）
     */
    public int insertIgnoreList(Collection<T> collections) {
        if (CollectionUtils.isEmpty(collections)) {
            return 0;
        }
        return getMapper().insertIgnoreList(new ArrayList<>(collections));
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    public int update(T entity) {
        return getMapper().updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    public Integer updateExcludeNull(T entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据Example条件更新实体`entity`包含的全部属性，null值会被更新
     */
    public int update(T entity, Example example) {
        return getMapper().updateByExample(entity, example);
    }

    /**
     * 根据Example条件更新实体`entity`包含的不是null的属性值
     */
    public int updateExcludeNull(T entity, Example example) {
        return getMapper().updateByExampleSelective(entity, example);
    }

    /**
     * 根据主键id进行删除
     */
    public int delete(Long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
    public int delete(T entity) {
        return getMapper().delete(entity);
    }

    /**
     * 根据Example条件删除数据
     */
    public int delete(Example example) {
        return getMapper().deleteByExample(example);
    }

    /**
     * 批量删除 (根据id集合批量删除数据，限制实体包含`id`属性)
     */
    public Integer delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        Class<T> clazz = (Class<T>) getParameterizedType();
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return delete(example);
    }

    /**
     * 获取泛型
     */
    private Type getParameterizedType() {
        // 获取当前运行类泛型父类类型，即为参数化类型。(Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型)
        Type type = this.getClass().getGenericSuperclass();
        // 泛型数组, 返回第一个泛型
        Type[] parameterizedTypes = ((ParameterizedType) type).getActualTypeArguments();
        return parameterizedTypes[0];
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     */
    public int count(T entity) {
        return getMapper().selectCount(entity);
    }

    /**
     * 根据Example条件查询总数
     */
    public int count(Example example) {
        return getMapper().selectCountByExample(example);
    }

    /**
     * 根据实体属性和RowBounds进行分页查询
     */
    public List<T> selectByRowBounds(T entity, RowBounds rowBounds) {
        return getMapper().selectByRowBounds(entity, rowBounds);
    }

    /**
     * 根据example条件和RowBounds进行分页查询
     */
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return getMapper().selectByExampleAndRowBounds(example, rowBounds);
    }

}