package com.mg.javase;

import java.util.*;

public class UseMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("001", "信雅达");
        map.put("002", "网联");
        map.put("003", "海康威视");
        ergodic(map);
    }

    public static void ergodic(Map<String, String> map){
        if (map == null){
            System.out.println("传入的map为空");
        throw new RuntimeException("传入的map为空");
    }
        System.out.println(map);
        System.out.println("第一种遍历方式(遍历keySet值):");
        for (String key : map.keySet()){
            System.out.println("key=" + key + ",value=" + map.get(key));
        }
        System.out.println("==================================");
        System.out.println("第二种遍历方式(遍历value值):");
        for (String value : map.values()){
            System.out.println("value=" + value);
        }
        System.out.println("===================================");
        System.out.println("第三种遍历方式(遍历EntrySet)");
        for (Map.Entry<String, String> entry : map.entrySet()){
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }
        System.out.println("====================================");
        System.out.println("第四种遍历方式(迭代器遍历EntrySet)");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }
    }

}
