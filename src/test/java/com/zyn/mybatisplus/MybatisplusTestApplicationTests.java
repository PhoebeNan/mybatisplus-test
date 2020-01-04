package com.zyn.mybatisplus;

import com.zyn.mybatisplus.entity.User;
import com.zyn.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //添加用户操作
    @Test
    public void addUser(){

        User user = new User();
        user.setName("zyn");
        user.setAge(25);
        user.setEmail("@163.com");
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    //查询所有用户
    @Test
    public void getAllUsers() {

        List<User> users = userMapper.selectList(null);

        for (User user : users) {
            System.out.println(user);
        }
    }

}
