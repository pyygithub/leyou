package com.thtf.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 */
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 序列化对象为JSON字符串
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为List集合
     * @param json
     * @param eClass 元素类型
     * @param <E>
     * @return
     */
    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 将JSON字符串反序列化为Map集合
     * @param json
     * @param kClass
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 将JSON字符串转换为指定泛型对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

    public static void main(String[] args) {
        String json = "[{\"name\":\"Jack\", \"age\":\"22\"},{\"name\":\"Rose\", \"age\":\"20\"}]";

        List<Map<String, String>> maps = nativeRead(json, new TypeReference<List<Map<String, String>>>() {});

        for (Map<String, String> map : maps) {
            System.out.println("map=" + map);
        }
    }
}
