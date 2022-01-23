package com.cqrs.demo.reader;

import com.cqrs.demo.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * 读取resources下面repository下的数据
 * @author : kenny
 * @since : 2022/1/19
 **/
public class XmlReader extends XMLModel {
    private Logger logger = Logger.getLogger(XmlReader.class.getName());

    private XmlReader(){}

    public <T> List<T> makeToRepository(String fileUrl) {
        Element root = super.checkXmlDefThenGetRoot(new File(XmlReader.class.getResource("/").getPath() + fileUrl));
        Element fields = root.element("fields");

        String clzRef = root.attribute("ref").getValue();

        Class<?> clz;
        try {
            clz = Class.forName(clzRef);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("未定位到Class");
        }

        List<String> fieldNameList = new ArrayList<>();
        Iterator<Element> iter1 = fields.elementIterator();
        while (iter1.hasNext()) {
            Element fieldTag = iter1.next();
            Attribute id = fieldTag.attribute("id");
            String fieldName;

            if (id.getValue().contains("_")) {
                fieldName = StringUtil.camelCase((id.getValue()));
            } else {
                fieldName = id.getValue();
            }

            if (fieldNameList.contains(fieldName)) {
                throw new RuntimeException("field id 名称重复定义");
            }
            fieldNameList.add(fieldName);
        }

        Element rows = root.element("rows");
        if (rows == null) {
            throw new RuntimeException("rows标签未定义");
        }

        Iterator<Element> iter2 = rows.elementIterator();
        List<T> result = new ArrayList<>();
        while (iter2.hasNext()) {
            Element row = iter2.next();
            if (row == null) {
                return null;
            }

            try {
                Object obj = clz.newInstance();
                Iterator<Element> subIter = row.elementIterator();
                int idx = 0;
                while (subIter.hasNext()) {
                    Element subRow = subIter.next();
                    Field field = clz.getDeclaredField(fieldNameList.get(idx));
                    String setMethodName = "set" + StringUtil.capitalize(fieldNameList.get(idx));
                    Method method = clz.getDeclaredMethod(setMethodName, field.getType());
                    this.setFieldValue(obj, field, method, subRow);
                    idx++;
                }

                result.add((T) obj);
            } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据字段名称设置对象属性
     * @param obj
     * @param field
     * @param method
     * @param fromValue
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void setFieldValue(Object obj, Field field, Method method, Element fromValue) throws InvocationTargetException, IllegalAccessException {
        Object value = this.cast(fromValue.getStringValue(), field.getType());
        method.invoke(obj, value);
    }


    public Object cast(String fromValue, Class<?> toType) {
        if (toType == String.class) {
            return fromValue;
        }

        if (toType == Long.class) {
            return Long.valueOf(fromValue);
        }

        if (toType == Integer.class) {
            return Integer.valueOf(fromValue);
        }

        if (toType == Short.class) {
            return Short.valueOf(fromValue);
        }

        if (toType == Double.class) {
            return Double.valueOf(fromValue);
        }

        if (toType == Float.class) {
            return Float.valueOf(fromValue);
        }

        if (toType == Byte.class) {
            return Byte.valueOf(fromValue);
        }

        if (toType == Character.class) {
            return Character.valueOf(fromValue.charAt(0));
        }

        if (toType == long.class) {
            return Long.valueOf(fromValue).longValue();
        }

        if (toType == char.class) {
            return fromValue.charAt(0);
        }

        if (toType == int.class) {
            return Integer.valueOf(fromValue);
        }

        if (toType == short.class) {
            return Short.valueOf(fromValue).shortValue();
        }

        if (toType == boolean.class) {
            return Boolean.valueOf(fromValue).booleanValue();
        }

        if (toType == float.class) {
            return Float.valueOf(fromValue).floatValue();
        }

        if (toType == byte.class) {
            return Byte.valueOf(fromValue).byteValue();
        }

        if (toType == BigDecimal.class) {
            return new BigDecimal(fromValue);
        }

        throw new IllegalArgumentException("Can't cast [" + fromValue + "] to " + toType);
    }

    public static class InnerHolder{
        private static final XmlReader INSTANCE = new XmlReader();
    }

    public static XmlReader getInstance(){
        return InnerHolder.INSTANCE;
    }
}
