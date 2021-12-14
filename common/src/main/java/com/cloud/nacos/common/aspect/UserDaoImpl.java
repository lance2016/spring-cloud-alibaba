package com.cloud.nacos.common.aspect;

import org.springframework.stereotype.Service;

/**
 * @program: springcloud  UserDaoImpl
 * @description:
 * @author: flchen
 * @create: 2021-05-06 16:09
 **/

@Service
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser() {
        // TODO Auto-generated method stub
        System.out.println("添加用户");
    }

    @Override
    public void delUser() {
        // TODO Auto-generated method stub
        System.out.println("删除用户");
    }
}