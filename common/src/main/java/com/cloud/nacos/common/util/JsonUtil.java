package com.cloud.nacos.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {

    /**
     * 将对象转化成json
     *
     * @param t
     * @return
     * @throws JsonProcessingException
     */
    public static <T> String toJson(T t) {
        try {
            return OBJECT_MAPPER.get().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("[json processing]异常：" + e.getMessage(), e);
            throw new RuntimeException(String.format("json processing error : %s", t.toString()));
        }
    }

    /**
     * 将json转化成bean
     *
     * @param json
     * @param valueType
     * @return
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     * @throws IOException
     */
    public static <T> T toEntity(String json, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.get().readValue(json, valueType);
        } catch (IOException e) {
            log.error("[JsonUtil]异常：" + e.getMessage(), e);
            throw new RuntimeException(
                    String.format("JsonUtil Error! json:%s, valueType: %s", json, valueType.toString()));
        }
    }

    /**
     * 将object转化为另一个对象
     *
     * @param sourceObject 源对象
     * @param targetType 目标类型
     * @return
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     * @throws IOException
     */
    public static <T> T toEntityByObject(Object sourceObject, Class<T> targetType) {
        String json = toJson(sourceObject);
        try {
            return OBJECT_MAPPER.get().readValue(json, targetType);
        } catch (IOException e) {
            log.error("[JsonUtil]异常：" + e.getMessage(), e);
            throw new RuntimeException(
                    String.format("JsonUtil Error! json:%s, valueType: %s", json, targetType.toString()));
        }
    }

    /**
     * 将json转化成List
     *
     * @param json
     * @param collectionClass
     * @param elementClass
     * @return
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> toList(
            String json, Class<? extends List> collectionClass, Class<T> elementClass) {
        try {
            JavaType javaType =
                    OBJECT_MAPPER
                            .get()
                            .getTypeFactory()
                            .constructCollectionType(collectionClass, elementClass);
            return OBJECT_MAPPER.get().readValue(json, javaType);
        } catch (IOException e) {
            log.error("[JsonUtil toList]异常：" + e.getMessage(), e);
            throw new RuntimeException(
                    String.format(
                            "toList error, String: %s, collectionClass: %s, elementClass: %s",
                            json, collectionClass, elementClass));
        }
    }

    /**
     * 将json转化成Map
     *
     * @param json
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @return
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static <K, V> Map<K, V> toMap(
            String json, Class<? extends Map> mapClass, Class<K> keyClass, Class<V> valueClass)
            throws IOException {
        JavaType javaType =
                OBJECT_MAPPER.get().getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
        return OBJECT_MAPPER.get().readValue(json, javaType);
    }

    // ################################################################################################################

    /**
     * 禁止调用无参构造
     *
     * @throws IllegalAccessException
     */
    private JsonUtil() throws AssertionError {
        throw new AssertionError("Can't create an instance!");
    }

    /**
     * 使用ThreadLocal创建对象，防止出现线程安全问题
     */
    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER =
            new ThreadLocal<ObjectMapper>() {
                @Override
                protected ObjectMapper initialValue() {
                    ObjectMapper objectMapper = new ObjectMapper();
                    // 添加对LocalDateTime的序列化
                    JavaTimeModule module = new JavaTimeModule();
                    module.addDeserializer(
                            LocalDateTime.class,
                            new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    module.addSerializer(
                            LocalDateTime.class,
                            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    objectMapper.registerModule(module);

                    // 忽略不存在的字段
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    return objectMapper;
                }
            };
}
