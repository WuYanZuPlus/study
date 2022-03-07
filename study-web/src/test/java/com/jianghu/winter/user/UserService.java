package com.jianghu.winter.user;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daniel.hu
 * @date 2019/9/5 14:15
 */
@Service
public class UserService {

    @Getter
    private UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * getById
     */
    public UserEntity get(Long id) {
        return mapper.get(id);
    }

    /**
     * find all
     */
    public List<UserEntity> find(){
        return mapper.find();
    }

    /**
     * findByCondition
     */
    public List<UserEntity> findByCondition(UserEntity query){
        return mapper.findByCondition(query);
    }

    /**
     * delete
     */
    public void delete(Long id) {
        mapper.delete(id);
    }

    /**
     * update
     */
    public void update(UserEntity user){
        mapper.update(user);
    }

    /**
     * add
     */
    public void insert(UserEntity user){
        mapper.insert(user);
    }

}
