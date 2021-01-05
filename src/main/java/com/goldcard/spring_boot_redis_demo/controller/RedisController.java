package com.goldcard.spring_boot_redis_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis事务
     *
     * @return
     */
    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> testMulti() {
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //设置监控key1
                operations.watch("key1");
                //开启事务，在exec命令执行前，全部都只是进入队列
                operations.multi();
                operations.opsForValue().set("key2", "value2");
                //此处会报异常，Redis事务跟数据库不一样，对于Redis事务是先让命令进入队列，所以一开始它并没有检测这个加一命令是否能够成功，
                //只有在exec命令执行的时候，才能发现错误，对于出错的命令Redis只是报出错误，错误后面的命令依旧被执行
                operations.opsForValue().increment("key1", 1);
                //获取值将为null，因为reids只是将命令放入队列，未执行
                Object value2 = operations.opsForValue().get("key2");
                System.out.println("命令在队列，所以value为null【" + value2 + "】");
                operations.opsForValue().set("key3", "value3");
                Object value3 = operations.opsForValue().get("key3");
                System.out.println("命令在队列，所以value为null【" + value3 + "】");
                //执行exec命令，将先判断key1是否在监控后被修改，如果是，则不执行事务，队列的命令将不执行，否则就执行事务，执行队列里的命令
                return operations.exec();
            }
        });
        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * redis 批处理--流水线 pipeline
     * 与redis事务一样，使用pipeline过程中，所有命令也只是进入队列而没有执行
     */
    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String, Object> testPipeline() {
        Long start_time = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= 100000; i++) {
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 100000) {
                        System.out.println("命令只是进入队列，所以值为空【" + value + "】");
                    }
                }
                return null;
            }
        });
        Long end_time = System.currentTimeMillis();
        System.out.println("耗时：" + (end_time - start_time) + " 毫秒");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua")
    @ResponseBody
    public Map<String, Object> testLua() {
        Map<String, Object> map = new HashMap<>();
        DefaultRedisScript<String> rs = new DefaultRedisScript<>();
        //设置脚本
        rs.setScriptText("return ‘Hello Redis’");
        //定义返回类型。注意：如果没有这个定义，Spring不会返回结果
        rs.setResultType(String.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        //执行lua脚本
        String str = (String) redisTemplate.execute(rs, redisSerializer, redisSerializer, null);
        map.put("str", str);
        return map;
    }
}
