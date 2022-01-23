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

    /**
     * 当前持久层数据
     */
    private List<T> repoList;

    /**
     * 构造器- 加载数据
     * @param url repository xml 文件路径
     */
    public XMLRepository(String url){
        this.repoUrl = url;
        repoList = XmlReader.getInstance().makeToRepository(url);
        assert repoList != null;
    }

    @Override
    public void save(T entity) {
        assert entity != null;
        int maxId = repoList.stream().mapToInt(i -> {
            Class<?> clz = i.getClass();
            try {
                Method method = clz.getMethod("getId");
                long val = (long) method.invoke(i);
                return (int) val;
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        }).max().getAsInt();

        try {
            Method method = entity.getClass().getMethod("setId", Long.class);
            method.invoke(entity, (long) (maxId + 1));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        repoList.add(entity);
    }

    @Override
    public void deleteById(Long id) {
        for (T t : repoList){
            Class<?> clz = t.getClass();
            try {
                Method method = clz.getMethod("getId");
                Object val = method.invoke(t);
                if (id.equals(val)){
                    repoList.remove(t);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T findById(Long id) {
        for (T t : repoList){
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
        return repoList;
    }

    @Override
    public void flush() {
        XmlWriter xmlWriter = XmlWriter.getInstance();
        xmlWriter.writer(repoList, repoUrl);
    }
}
