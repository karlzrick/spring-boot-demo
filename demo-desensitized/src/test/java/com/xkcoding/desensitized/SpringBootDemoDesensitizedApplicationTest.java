package com.xkcoding.desensitized;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 测试类
 * </p>
 *
 * @author xuyuan
 * @date 2023-07-20 14:44
 */
@RunWith(JUnit4.class)
@SpringBootTest
public abstract class SpringBootDemoDesensitizedApplicationTest {

    @Before
    public void before(){

    }

    @Test
    abstract public void test();

    @After
    public void after(){

    }
}
