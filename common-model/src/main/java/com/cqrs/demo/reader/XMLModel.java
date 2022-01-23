package com.cqrs.demo.reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author : yichen
 * @since : 2022/1/23
 **/
public abstract class XMLModel {
    public Element checkXmlDefThenGetRoot(File file){
        SAXReader reader = new SAXReader();
        if (file.exists()) {
            Document document;
            try {
                document = reader.read(file);
            } catch (DocumentException e) {
                throw new RuntimeException("document 异常");
            }

            if (document != null) {
                Element root = document.getRootElement();
                if (root == null) {
                    throw new RuntimeException("repository xml 异常");
                }

                String rootName = root.getName();
                if ("repository".equalsIgnoreCase(rootName)) {
                    String clzRef = root.attribute("ref").getValue();
                    if (clzRef == null || clzRef.isEmpty()) {
                        throw new RuntimeException("ref元素异常");
                    }

                    Element fields = root.element("fields");
                    if (fields == null) {
                        throw new RuntimeException("fields标签未定义");
                    }

                    return root;
                } else {
                    throw new RuntimeException("未找到root定义");
                }
            }
        }

        throw new RuntimeException("未找到文件: " + file.getPath());
    }
}
