/**
 * @author qinzhu
 * @since 2020/7/3
 * 使用静态类做内存数据库，方便简单，暂时就不用mysql了
 * 需要特别注意！因为使用的内存存储数据，所以此服务不能开启多实例，否则会导致数据不一致
 * 如果需要开启多实例请引入redis集群或者数据库集群
 */
package cn.hotpot.springcloud.auth.database;