package com.xkcoding.desensitized.databind;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.xkcoding.desensitized.annotation.Desensitization;
import com.xkcoding.desensitized.enums.DesensitizationTypeEnum;

import java.io.IOException;
import java.util.Objects;

/**
 * 脱敏序列化处理
 *
 * @author xuyuan
 * @date 2023-07-20 15:23
 */
public class DesensitizationSerialize extends JsonSerializer<String> implements ContextualSerializer {
    /**
     * 脱敏类型
     */
    private DesensitizationTypeEnum desensitizationTypeEnum;

    /**
     * 开始位置（包含）
     */
    private int startInclude;

    /**
     * 结束位置（不包含）
     */
    private int endExclude;

    public DesensitizationSerialize() {
    }

    public DesensitizationSerialize(DesensitizationTypeEnum desensitizationTypeEnum, int startInclude, int endExclude) {
        this.desensitizationTypeEnum = desensitizationTypeEnum;
        this.startInclude = startInclude;
        this.endExclude = endExclude;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        switch (desensitizationTypeEnum){
            case CUSTOMER:
                // 使用*自定义隐藏
                jsonGenerator.writeString(StrUtil.hide(value, startInclude, endExclude));
                break;
            case USER_ID:
                // 【用户id】不对外提供userId
                jsonGenerator.writeString(String.valueOf(DesensitizedUtil.userId()));
                break;
            case CHINESE_NAME:
                // 【中文姓名】只显示第一个汉字，其他隐藏为2个星号，比如：李**
                jsonGenerator.writeString(DesensitizedUtil.chineseName(value));
                break;
            case ID_CARD:
                // 【身份证号】前1位 和后2位
                jsonGenerator.writeString(DesensitizedUtil.idCardNum(value,1,2));
                break;
            case FIXED_PHONE:
                // 固定电话 前四位，后两位
                jsonGenerator.writeString(DesensitizedUtil.fixedPhone(value));
                break;
            case MOBILE_PHONE:
                // 前三位，后4位，其他隐藏，比如135****2210
                jsonGenerator.writeString(DesensitizedUtil.mobilePhone(value));
                break;
            case ADDRESS:
                // 只显示到地区，不显示详细地址，比如：北京市海淀区****
                jsonGenerator.writeString(DesensitizedUtil.address(value,8));
                break;
            case EMAIL:
                // 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com
                jsonGenerator.writeString(DesensitizedUtil.email(value));
                break;
            case PASSWORD:
                // 密码的全部字符都用*代替，比如：******
                jsonGenerator.writeString(DesensitizedUtil.password(value));
                break;
            case CAR_LICENSE:
                // 【中国车牌】车牌中间用*代替
                // eg1：null -》 "" eg1："" -》 "" eg3：苏D40000 -》 苏D4***0 eg4：陕A12345D -》 陕A1****D eg5：京A123 -》 京A123 如果是错误的车牌，不处理
                jsonGenerator.writeString(DesensitizedUtil.carLicense(value));
                break;
            case BANK_CARD:
                // 【银行卡号】前6位，后4位，其他用*代替，比如：622260**********1234
                jsonGenerator.writeString(DesensitizedUtil.bankCard(value));
                break;
            default:
                // 默认不脱敏
                jsonGenerator.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        if (beanProperty != null) {
            // 判断数据类型是否为String类型
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 获取定义的注解
                Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
                // 为null
                if (desensitization == null) {
                    desensitization = beanProperty.getContextAnnotation(Desensitization.class);
                }
                // 不为null
                if (desensitization != null) {
                    // 创建定义的序列化类的实例并且返回，入参为注解定义的type,开始位置，结束位置。
                    return new DesensitizationSerialize(desensitization.type(), desensitization.startInclude(),
                        desensitization.endExclude());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
