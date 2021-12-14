package com.cloud.nacos.common.thread;

/**
 * @program: springcloud  ThreadDemo
 * @description:
 * @author: flchen
 * @create: 2021-04-26 19:36
 **/


public class ThreadDemo {
    public static void main(String[] args) {
        ThreadDemo t = new ThreadDemo();

            int b = t.getNumber();
            System.out.println(b);


    }

    public int getNumber () {

        try {
            System.out.println(1/0);
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println("有问题");
        throw new RuntimeException();
    }
}
