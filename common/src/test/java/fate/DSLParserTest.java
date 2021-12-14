package fate;

import com.cloud.nacos.common.bean.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;


class DSLParserTest {

    String str = "{\n" +
            "  \"components\": {\n" +
            "    \"dataio_0\": {\n" +
            "      \"module\": \"DataIO\",\n" +
            "      \"input\": {\n" +
            "        \"data\": {\n" +
            "          \"data\": [\n" +
            "            \"args.train_data\"\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      \"output\": {\n" +
            "        \"data\": [\n" +
            "          \"train\"\n" +
            "        ],\n" +
            "        \"model\": [\n" +
            "          \"dataio\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"CodePath\": \"/Users/lance/Documents/springcloud/common/src/main/java/fate/Dict.java\"\n" +
            "    },\n" +
            "    \"intersection_0\": {\n" +
            "      \"module\": \"Intersection\",\n" +
            "      \"input\": {\n" +
            "        \"data\": {\n" +
            "          \"data\": [\n" +
            "            \"dataio_0.train\"\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      \"output\": {\n" +
            "        \"data\": [\n" +
            "          \"train\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"CodePath\": \"/Users/lance/Documents/springcloud/common/src/main/java/fate/Dict.java\"\n" +
            "    },\n" +
            "    \"feature_scale_0\": {\n" +
            "      \"module\": \"FeatureScale\",\n" +
            "      \"input\": {\n" +
            "        \"data\": {\n" +
            "          \"data\": [\n" +
            "            \"intersection_0.train\"\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      \"output\": {\n" +
            "        \"data\": [\n" +
            "          \"train\"\n" +
            "        ],\n" +
            "        \"model\": [\n" +
            "          \"feature_scale\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"CodePath\": \"/Users/lance/Documents/springcloud/common/src/main/java/fate/Dict.java\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    void test() {
        DSLParser dslParser = new DSLParser();
        dslParser.parseDagFromDSL(str);
    }

    @Test
    void testSingleton() {
        List<String> str = Collections.singletonList("1");
        System.out.println(str.size());
    }


    @Test
    void testSpring() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean  = (TestBean)ctx.getBean("p1");
        System.out.println(testBean);


//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
//        ctx.refresh();
//
//        Entitlement ent = (Entitlement)ctx.getBean("entitlement");

        /**
         * 构造对象
         */
//        TestBean testBean0 = (TestBean)TestBean.class.newInstance();
//
//        Class clazz = Class.forName("com.cloud.nacos.common.bean.TestBean");
//        TestBean testBean1 = (TestBean)clazz.newInstance();
//
//        Constructor<TestBean> constructor = TestBean.class.getDeclaredConstructor();
//        TestBean testBean2 = constructor.newInstance();
    }
}