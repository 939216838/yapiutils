package com.yunshi.yapiutils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YapiutilsApplicationTests {

    @Test
    public void contextLoads() {
        Map<String, Object> map = new
                HashMap<>();
        map.put("properties", 123);
        boolean properties = map.containsKey("properties");
        System.out.println(properties);


    }

}
