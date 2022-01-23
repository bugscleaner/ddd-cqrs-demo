package com.cqrs.demo.utils;

/**
 * @author : yichen
 * @since : 2022/1/23
 **/
public class StringUtil {
    private StringUtil(){}

    public static String capitalize(String str){
        assert str != null;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String camelCase(String str){
        assert str != null;
        String lower = str.toLowerCase();
        String[] strs = lower.split("_");
        StringBuilder stb = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; i++){
            String nStr = capitalize(strs[i]);
            stb.append(nStr);
        }
        return stb.toString();
    }
}
