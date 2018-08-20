package com.ys.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

public class JedisTest {

    private Jedis jedis = null;

    @Before
    public void connection() {
        //连接本地的Redis服务
        jedis = new Jedis("192.168.61.129", 6379);
    }

    //jedis测试连通性
    @Test
    public void testConnection() {
        //连接本地的Redis服务
        Jedis jedis = new Jedis("192.168.61.129", 6379);
        //查看服务是否运行，打出pong表示ok
        System.out.println("connection is OK ===========>"
                + jedis.ping());
    }

    //jedis-API: key
    //key
    @Test
    public void testKey() {
        Set<String> keys = jedis.keys("*");
        for (Iterator iterator = keys.iterator(); ((Iterator) iterator).hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        System.out.println("jedis.exists====>" + jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));
    }

    //jedis-API:String
    @Test
    public void testString() {
        System.out.println(jedis.get("k1"));
        jedis.set("k4", "k4_Redis");
        System.out.println("--------------------------------");
        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
        System.out.println(jedis.mget("str1", "str2", "str3"));
    }

    //jedis-API:List
    @Test
    public void testList() {
        List<String> list = jedis.lrange("mylist", 0, -1);
        for (String element : list) {
            System.out.println(element);
        }
    }

    //jedis-API: set
    @Test
    public void testSet(){
        jedis.sadd("orders","jd001");
        jedis.sadd("orders","jd002");
        jedis.sadd("orders","jd003");
        Set<String> set = jedis.smembers("orders");
        for (Iterator iterator = set.iterator();iterator.hasNext();){
            String string = (String) iterator.next();
            System.out.println(string);
        }
        jedis.srem("orders","jd002");
    }

    //jedis-API:hash
    @Test
    public void testHash(){
        jedis.hset("hash1","userName","lisi");
        System.out.println(jedis.hget("hash1","userName"));
        Map<String ,String> map = new HashMap<String, String>();
        map.put("telphone","13810169999");
        map.put("address","atguigu");
        map.put("email","abc@163.com");
        jedis.hmset("hash2",map);
        List<String> result = jedis.hmget("hash2","telphone","email");
        for (String element:
             result) {
            System.out.println(element);
        }
    }

    //jedis-API: zset
    @Test
    public void testZset(){
        jedis.zadd("zset01",60d,"v1");
        jedis.zadd("zset01",70d,"v2");
        jedis.zadd("zset01",80d,"v3");
        jedis.zadd("zset01",90d,"v4");
        Set<String> s1 = jedis.zrange("zset01",0,-1);
        for (Iterator iterator = s1.iterator();iterator.hasNext();){
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }

    @After
    public void close() {
        jedis.close();
    }
}
