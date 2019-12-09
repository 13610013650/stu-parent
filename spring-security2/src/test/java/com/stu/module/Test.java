package com.stu.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.context.Lifecycle;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: stu-parent
 * @description: ${}
 * @author: Mr.ZhangDESCRIPTION
 * @create: 2019-11-11 01:44
 **/
@Data
@AllArgsConstructor
public class Test implements Lifecycle{

    private String name;


    public static void main(String[] args) {
        List<Test> ids = Lists.newArrayList();
        ids.add(new Test("a"));
        ids.add(new Test("D"));
        ids.add(new Test("E"));
        List<Test> ids2 = Lists.newArrayList();
        ids2.add(new Test("a"));
        ids2.add(new Test("b"));
        ids2.add(new Test("c"));
//
//        List test = ids.stream()
//                .map(id -> ids2.stream()
//                        .filter(map2 -> !id.getName().equals(map2.getName()))
//                        .findFirst()
//                        .map(id2 -> {
//                            id.setName(id2.getName());
//                            return id;
//                        })
//                ).collect(Collectors.toList());

//        System.out.println(test);

        System.out.println("---------------------------");

//        List<Test> collect = ids.stream()
////                .map(id -> {
////                    if (id.getName().equals("D")){
////                        id.setName("A");
////                    }
////                    return id;
////                }).collect(Collectors.toList());
////
////        System.out.println("collect:"+collect);

        List<String> data = new ArrayList<String>();
        for (int i = 0;i<=1000;i++) {
            String uuid = UUID.randomUUID().toString().replace("-","");
            data.add(uuid);
        }
//        long currenttime = System.currentTimeMillis();
//            first(data); //55
//        long endtime = System.currentTimeMillis()-currenttime;
//        System.out.println("first:"+endtime);
        long start = System.currentTimeMillis();
        secend(data);
        long end = System.currentTimeMillis()-start;
        System.out.println("end:"+end); //16



//        ids.stream().filter(id -> id =="c")
//                .forEach(id ->{
//                    System.out.println(id);
//                });
    }

    private static void secend(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }

    public static void first(List<String> data){
        data.stream()
                .forEach(value -> {
                    System.out.println(value);
                });
    }

    @Override
    public void start() {
        System.out.println("start...");
    }

    @Override
    public void stop() {
        System.out.println("stop...");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    public static void test(){
        System.out.println("do something...");
    }
}
