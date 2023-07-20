package com.xkcoding.desensitized.domain.vo;

import com.xkcoding.desensitized.annotation.Desensitization;
import com.xkcoding.desensitized.enums.DesensitizationTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体信息类
 *
 * @author xuyuan
 * @date 2023-07-20 14:50
 */
@Data
public class DesensitizedInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自定义脱敏
     */
    @Desensitization(type = DesensitizationTypeEnum.CUSTOMER, startInclude = 1, endExclude = 3)
    private String custom;

    /**
     * 用户id
     */
    @Desensitization(type = DesensitizationTypeEnum.USER_ID)
    private String userId;

    /**
     * 中文名
     */
    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String chineseName;

    /**
     * 身份证号
     */
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    /**
     * 固定电话
     */
    @Desensitization(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String fixedPhone;

    /**
     * 手机号
     */
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String mobilePhone;

    /**
     * 地址
     */
    @Desensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    /**
     * 电子邮件
     */
    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    /**
     * 密码
     */
    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    /**
     * 中国大陆车牌，包含普通车辆、新能源车辆
     */
    @Desensitization(type = DesensitizationTypeEnum.CAR_LICENSE)
    private String carLicense;

    /**
     * 银行卡
     */
    @Desensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;
}
