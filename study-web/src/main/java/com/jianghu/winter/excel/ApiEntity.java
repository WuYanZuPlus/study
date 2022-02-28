package com.jianghu.winter.excel;


import lombok.Getter;
import lombok.Setter;

/**
 * @author daniel.hu
 */
@Getter
@Setter
public class ApiEntity {

    public static final String TABLE = "t_sys_api";

    private Long id;

    private String project;

    private String apiCode;

    private String apiName;

    private Platform apiPlatform;

    private String apiUrl;

    private String memo;

    private Boolean valid;
}