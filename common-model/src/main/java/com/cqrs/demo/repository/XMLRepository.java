package com.cqrs.demo.repository;

import com.cqrs.demo.reader.XmlReader;
import com.cqrs.demo.reader.XmlWriter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 简易 - XML持久层
 * @author : kenny
 * @since : 2022/1/23
 **/
public abstract class XMLRepository<T> implements IRepository<T>{
    private String repoUrl;
    private List<T> list;

    /**
     * 构造器- 加载数据
     * @param url repository xml 文件路径
     */
    public XMLRepository(String url){
        this.repoUrl = url;
        list = XmlReader.getInstance().makeToRepository(url);
        assert list != null;
    }

    @Override
    public void save(T entity) {
        assert entity != null;
        list.add(entity);
    }

    @Override
    public void deleteById(Long id) {
        for (T t : list){
            Class<?> clz = t.getClass();
            try {
                Method method = clz.getMethod("getId");
                Object val = method.invoke(t);
                if (id.equals(val)){
                    list.remove(t);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T findById(Long id) {
        for (T t : list){
            Class<?> clz = t.getClass();
            try {
                Method method = clz.getMethod("getId");
                Object val = method.invoke(t);
                if (id.equals(val)){
                    return t;
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        return list;
    }

    @Override
    public void flush() {
        XmlWriter xmlWriter = XmlWriter.getInstance();
        xmlWriter.writer(list, repoUrl);
    }
}
