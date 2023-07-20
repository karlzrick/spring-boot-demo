package com.xkcoding.desensitized.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 实体信息类
 *
 * @author xuyuan
 * @date 2023-07-20 14:50
 */
@Data
public class DesensitizedInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义脱敏
     */
    private String custom;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 中文名
     */
    private String chineseName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 固定电话
     */
    private String fixedPhone;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 中国大陆车牌，包含普通车辆、新能源车辆
     */
    private String carLicense;

    /**
     * 银行卡
     */
    private String bankCard;
}
