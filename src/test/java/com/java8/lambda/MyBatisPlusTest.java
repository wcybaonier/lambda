package com.java8.lambda;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.java8.lambda.dao.UserMapper;
import com.java8.lambda.pojo.User;
import com.java8.lambda.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    /**
     * 查询列表测试
     */
    @Test
    public void queryList(){
        String s = userService.selectList();
        log.info("查询列表得到: "+s);

        userMapper.selectList(null)
                .forEach(System.out::println);
    }

    /**
     * 插入一条数据
     */
    @Test
    public void insert(){
        User user = new User();
        user.setId(1094592041087729667l);
        user.setAge(20);
        user.setName("宝妮儿");
        user.setEmail("baonier@qq.com");
        user.setManagerId("1094592041087729666");
        user.setCreateTime(LocalDateTime.now());
        int insert = userMapper.insert(user);
        log.info("添加结果: "+insert);
    }

    /**
     * 修改一条数据
     */
    @Test
    public void update(){
        User user = new User();
        user.setId(1094592041087729667l);
        user.setAge(18);
        user.setName("宝妮儿");
        user.setEmail("baonier@qq.com");
        user.setManagerId("1094592041087729666");
        user.setCreateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * 查询名字中包含'宝'并且年龄小于40
     * where name like '%宝%' and age < 40
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","宝").lt("age",40);
        userMapper.selectList(queryWrapper)
                .forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级姓名为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2021-03-23")
                .inSql("manager_id","select id from user where name like '王%'");
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    /**
     * 名字为王姓，（年龄小于40或者邮箱不为空）
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name","王")
                    .and(wq-> wq.lt("age",40)
                            .or()
                            .isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓，（年龄小于40，并且年龄大于20，并且邮箱不为空）
     */
    @Test
    public void selectWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> and = queryWrapper.likeRight("name", "王").and(s -> s.between("age", 20, 40)).and(w -> w.isNotNull("email"));
        userMapper.selectList(and).forEach(System.out::println);
    }

    /**
     * （年龄小于40或者邮箱不为空）并且名字为王姓
     * （age<40 or email is not null）and name like '王%'
     */
    @Test
    public void selectWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("email")).likeRight("name","王");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30,31,35,34的员工
     */
    @Test
    public void selectWrapper6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("age", Stream.of(30,31,34,35).collect(Collectors.toList()));

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的一条语句即可
     * limit 1
     */
    @Test
    public void selectWrapper7(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("age", Stream.of(30,31,34,35).collect(Collectors.toList())).last("limit 1");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查找为王姓的员工的姓名和年龄
     */
    @Test
    public void selectWrapper8(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age").likeRight("name","王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查询所有员工信息除了创建时间和员工ID列
     */
    @Test
    public void selectWrapper9(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class,info->!info.getColumn().equals("create_time")
                &&!info.getColumn().equals("manager_id"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectWrapper10(){
        User user = new User();
        user.setName("宝妮儿");
        user.setAge(18);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查询名字中包含‘宝’并且年龄小于40的员工信息
     */
    @Test
    public void lambdaSelect(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.like(User::getName,"宝").lt(User::getAge,40);
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 查询名字中包含“宝”字的，并且年龄大于20的员工信息
     */
    @Test
    public void lambdaSelect1(){
        List<User> userList = new LambdaQueryChainWrapper<>(userMapper).like(User::getName, "宝").lt(User::getAge, 40).list();
        userList.forEach(System.out::println);
    }

    /**
     * 自定义查询
     */
    @Test
    public void selectAll(){
        userMapper.selectAll().forEach(System.out::println);
    }

    @Test
    public void selectPage(){

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(User::getAge,10);

        //设置当前页和页容量
        Page<User> page = new Page<>(1, 2);
        //是否查询总记录数和总页数
        //page.setSearchCount(false);

        IPage<User> userIPage = userMapper.selectPage(page, lambdaQueryWrapper);

        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
    }


    /**
     * 1. 通过userMapper提供的方法更新用户信息
     */
    @Test
    public void updateTest1(){
        User user = new User();
        user.setId(1088250446457389058L);
        user.setEmail("update@email");
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    /**
     * 使用UpdateWrapper更新数据(相当于使用联合主键)
     */
    @Test
    public void updateTest2(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","宝妮儿").eq("age",26);
        User user = new User();
        user.setEmail("update2@email");
        int rows = userMapper.update(user, updateWrapper);
        System.out.println(rows);
    }

    /**
     * 当我们更新少量用户信息的时候，可以不用创建对象，直接通过调用set方法更新属性即可。
     */
    @Test
    public void updateTest3(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","宝妮儿").eq("age",18).set("email","baonierlovewcy@email.com");
        userMapper.update(null,updateWrapper);
    }

    /**
     * 使用lambda更新数据
     */
    @Test
    public void updateByLambda(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getName,"宝妮儿").eq(User::getAge,18).set(User::getAge,20);
        userMapper.update(null,lambdaUpdateWrapper);
    }

    /**
     * 删除
     * 删除方式和update极其类似。
     */

    /**
     * AR模式（Active Record）
     * 直接通过实体类完成对数据的增删改查。
     */
    @Test
    public void test(){
        User user = new User();
        user.selectAll().forEach(System.out::println);
    }

    /**
     * 通过id逻辑删除
     */
    @Test
    public void deleteById(){
        userMapper.deleteById(4566L);
    }

    /**
     * 查询中排除删除标识字段及注意事项
     * 逻辑删除字段只是为了标识数据是否被逻辑删除，在查询的时候，并不想也将该字段查询出来。
     * 我们只需要在delete字段上增加@TableField(select = false)mybatisplus在查询的时候就会自动忽略该字段。
     */
    @Test
    public void selectIgnoreDeleteTest(){
        userMapper.selectById(3456L);
    }

    @Test
    public void testLock(){
        User user = new User();
        user.setEmail("wtf@163.com");
        user.setAge(34);
        user.setId(2345L);
        user.setManagerId("1234");
        user.setVersion(1);
        userMapper.updateById(user);

    }
}
