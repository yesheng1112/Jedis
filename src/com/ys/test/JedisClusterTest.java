package com.ys.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest {

    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("192.168.61.129",6391));
        JedisCluster jedisCluster = new JedisCluster(set);
        jedisCluster.set("k2000","v2000");
        System.out.println(jedisCluster.get("k2000"));
    }

}
