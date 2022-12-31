package com.cloud.nacos.consumer;

import com.cloud.nacos.common.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ServerConsumerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        MyAlgorithmParameters myAlgorithmParameters = new MyAlgorithmParameters();
        FieldMatchParam fieldMatchParam = new FieldMatchParam();
        ColumnType c1 = new ColumnType("float64",0);
        ColumnType c2 = new ColumnType("float64",1);
        Map<String, ColumnType> map = new HashMap<>();
        map.put("x_0", c1);
        map.put("x_2", c2);
        fieldMatchParam.setColumn_types(map);
        myAlgorithmParameters.setFieldMatch_0(fieldMatchParam);
        System.out.println(JsonUtil.toJson(myAlgorithmParameters));


    }

    @Data
    class MyAlgorithmParameters{
        FieldMatchParam FieldMatch_0 = new FieldMatchParam();
    }


    @Data
    class FieldMatchParam{
        private Map<String,ColumnType> column_types = new HashMap<>();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ColumnType{
        private String datatype;
        private Integer op;
    }

//    @Resource
//    private PersonRepository personRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void test2(){
        System.out.println(elasticsearchRestTemplate);
    }
}
