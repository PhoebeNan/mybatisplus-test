package com.zyn.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyn.mybatisplus.entity.User;
import com.zyn.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用mybatis-plus插件实现sql语句的各种操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //mp实现逻辑删除
    @Test
    public void deleteByIdLogic() {
        int row = userMapper.deleteById(1214064392636829698L);
        System.out.println(row);
    }

    //Mp实现分页的操作  使用不同的方法
    @Test
    public void testPageLMap() {

        //创建page对象，传递参数为当前页和每页记录数
        Page<User> page = new Page<>(2, 3);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
//        System.out.println(userIPage.getTotal());
        List<Map<String, Object>> records = mapIPage.getRecords();//每页数据
        records.forEach(System.out::println);

        long current = mapIPage.getCurrent();//当前页
        long size = mapIPage.getSize();//每页记录数
        long total = mapIPage.getTotal();//总记录数
        long pages = mapIPage.getPages();//总页数

        System.out.println(records);
        System.out.println("======");
        System.out.println(current);
        System.out.println("======");
        System.out.println(size);
        System.out.println("======");
        System.out.println(total);
        System.out.println("======");
        System.out.println(pages);
    }

    //Mp实现分页的操作
    @Test
    public void testPageList() {

        //创建page对象，传递参数为当前页和每页记录数
        Page<User> page = new Page<>(1, 3);
        IPage<User> userIPage = userMapper.selectPage(page, null);
//        System.out.println(userIPage.getTotal());
        List<User> records = page.getRecords(); //每页数据
        long current = page.getCurrent();//当前页
        long size = page.getSize();//每页记录数
        long total = page.getTotal();//总记录数
        long pages = page.getPages();//总页数
        boolean previous = page.hasPrevious();//是否有前一页
        boolean next = page.hasNext();//是否有后一页

        System.out.println(records);
        System.out.println("======");
        System.out.println(current);
        System.out.println("======");
        System.out.println(size);
        System.out.println("======");
        System.out.println(total);
        System.out.println("======");
        System.out.println(pages);
        System.out.println(previous);
        System.out.println(next);
//        System.out.println(userIPage);
    }

    //简单的条件查询
    @Test
    public void testConditionSelectUser() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //批量查询用户
    @Test
    public void selectInUsers() {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(2, 3, 4));
        for (User user : users) {
            System.out.println(user);
        }
    }

    //测试乐观锁的版本号修改用户操作
    @Test
    public void testOptimisticLockerUser() {

        User user = userMapper.selectById(1214046656217083906L);
        user.setName("段誉");
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //修改用户操作
    @Test
    public void updateUser() {

        User user = new User();
        user.setId(1214026060502491138L);
        user.setName("刘备");
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //添加用户操作
    @Test
    public void addUser() {

        User user = new User();
        user.setName("特朗普");
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
    }//查询所有用户

    //查询年龄大于21的所有用户数据
    @Test
    public void selectAgeConditionUser() {

        //构建条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 22).select("id","name");
//        wrapper.gt("age", 21);
        userMapper.selectList(wrapper);
    }

}
