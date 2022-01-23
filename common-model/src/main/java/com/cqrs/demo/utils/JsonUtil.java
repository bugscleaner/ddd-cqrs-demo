package com.cqrs.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

/**
 * @author : yichen
 * @since : 2022/1/23
 **/
public class JsonUtil {
    private JsonUtil() {
    }

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static String parseToJson(Object obj) throws RuntimeException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseToList(String jsonStr, Class<T> clz) throws RuntimeException {
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clz);
        List<T> tList;
        try {
            tList = mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tList;
    }

    public static <T> T parseToObject(String json, Class<T> clz) throws RuntimeException {
        ObjectReader or = mapper.readerFor(clz);
        try {
            T obj = or.readValue(json);
            return obj;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isBooleanType(Class cls) {
        return Boolean.TYPE.equals(cls) || Boolean.class.equals(cls);
    }

    private static boolean isNumericType(Class cls) {
        return Short.TYPE.equals(cls) || Short.class.equals(cls)
                || Integer.TYPE.equals(cls) || Integer.class.equals(cls)
                || Long.TYPE.equals(cls) || Long.class.equals(cls)
                || Float.TYPE.equals(cls) || Float.class.equals(cls)
                || Double.TYPE.equals(cls) || Double.class.equals(cls);
    }
}
