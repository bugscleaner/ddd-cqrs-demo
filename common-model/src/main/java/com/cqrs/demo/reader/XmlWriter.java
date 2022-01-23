package com.cqrs.demo.reader;

import com.cqrs.demo.utils.StringUtil;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author : kenny
 * @since : 2022/1/23
 **/
public class XmlWriter extends XMLModel{
    private Logger logger = Logger.getLogger(XmlWriter.class.getName());

    private XmlWriter(){}

    public <T> void writer(List<T> list, String fileUrl){
        Element root = super.checkXmlDefThenGetRoot(new File(XmlWriter.class.getResource("/").getPath() + fileUrl));
        Element rows = root.element("rows");
        if (rows == null) {
            throw new RuntimeException("rows标签未定义");
        }

        Element fields = root.element("fields");
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

            fieldNameList.add(fieldName);
        }

        for (T t : list){
            Element newRow = rows.addElement("row");
            for (String fieldName : fieldNameList){
                Element value = newRow.addElement("value");
                Class<?> clz = t.getClass();
                String getMethodName = "get" + StringUtil.capitalize(fieldName);
                try {
                    Method method = clz.getMethod(getMethodName);
                    Object val = method.invoke(t);
                    if (val == null){
                        value.setText("");
                    }else {
                        value.setText(String.valueOf(val));
                    }

                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        FileOutputStream fis;
        try {
            fis = new FileOutputStream(fileUrl);
            OutputFormat out = OutputFormat.createPrettyPrint();
            out.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(fis,out);
            writer.write(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class InnerHolder{
        private static final XmlWriter INSTANCE = new XmlWriter();
    }

    public static XmlWriter getInstance(){
        return XmlWriter.InnerHolder.INSTANCE;
    }
}
