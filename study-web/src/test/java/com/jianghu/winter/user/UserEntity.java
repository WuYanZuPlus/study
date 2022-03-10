package com.jianghu.winter.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author daniel.hu
 * @date 2019/8/27 10:20
 */
@Getter
@Setter
@Entity
@Table(name = "t_user")
public class UserEntity implements Serializable {

    private Long id;
    private String account;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String mobile;
    private String email;
    @Column(name = "nick_name")
    private String nickName;
    private String userLevel;
    private boolean valid;
    private UserType userType;
}
