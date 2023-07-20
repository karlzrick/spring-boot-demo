package com.xkcoding.desensitized.controller;

import cn.hutool.core.util.RandomUtil;
import com.xkcoding.desensitized.domain.vo.DesensitizedInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuyuan
 * @date 2023-07-20 14:44
 */
@Slf4j
@RestController
public class DesensitizedController {

    @RequestMapping("/desensitized")
    public DesensitizedInfoVO desensitized(String param) {
        log.info("com.xkcoding.desensitized.controller.DesensitizedController.desensitized request:{}", param);

        DesensitizedInfoVO desensitizedInfoVO = new DesensitizedInfoVO();
        desensitizedInfoVO.setCustom("自定义脱敏");
        desensitizedInfoVO.setUserId(RandomUtil.randomString(16));
        desensitizedInfoVO.setChineseName("张三");
        desensitizedInfoVO.setIdCard("510181198712306421");
        desensitizedInfoVO.setFixedPhone("028-87298451");
        desensitizedInfoVO.setMobilePhone("13884605210");
        desensitizedInfoVO.setAddress("XX省XX市XX区天XXX开发园");
        desensitizedInfoVO.setEmail("test@desensitized.com");
        desensitizedInfoVO.setPassword("desensitized");
        desensitizedInfoVO.setCarLicense("川T12345");
        desensitizedInfoVO.setBankCard("6230241548410011");

        return desensitizedInfoVO;
    }
}
