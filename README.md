# Springboot_Malcolmszx

1、分布式锁

## application.properties配置方式(yaml同理)
### redisson分布式锁配置--单机

        redisson.lock.server.address=127.0.0.1:6379
        redisson.lock.server.type=standalone

### redisson分布式锁配置--哨兵
**redisson.lock.server.address** 格式为: sentinel.conf配置里的sentinel别名,sentinel1节点的服务IP和端口，sentinel2节点的服务IP和端口，sentinel3节点的服务IP和端口
<br/>比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name

        redisson.lock.server.address=my-sentinel-name,127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399
        redisson.lock.server.type=sentinel

### redisson分布式锁配置--集群方式
cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
<br/>地址格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384

        redisson.lock.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
        redisson.lock.server.type=cluster

### redisson分布式锁配置--主从
地址格式为**主节点,子节点,子节点**
<br/>比如:127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
<br/>代表主节点:127.0.0.1:6379，从节点127.0.0.1:6380，127.0.0.1:6381

        redisson.lock.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
        redisson.lock.server.type=masterslave

#################################################################################
