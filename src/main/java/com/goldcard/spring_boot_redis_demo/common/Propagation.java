package com.goldcard.spring_boot_redis_demo.common;

import org.springframework.transaction.TransactionDefinition;

/**
 * 事务传播行为枚举
 */
public enum Propagation {
    /**
     * 需要事务，它是默认传播行为，如果当前存在事务，就沿用当前事务，
     * 否则新建一个事务运行子方法
     * 常用
     */
    REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),

    /**
     * 支持事务，如果当前存在事务，就沿用当前事务，
     * 如果不存在，则继续采用无事务的方式运行子事务
     */
    SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS),

    /**
     *  必须使用事务，如果当前没有事务，则抛出异常
     *  如果当前存在事务，就沿用当前事务
     */
    MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),

    /**
     * 无论当前是否存在事务，都会创建新事务运行子方法
     * 这样新事务就可以拥有新的锁和新的隔离级别等特性，与当前事务相互独立
     * 常用
     */
    REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),

    /**
     * 不支持事务，当前存在事务时，将事务挂起，运行方法
     */
    NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),

    /**
     * 不支持事务，如果当前存在事务时抛出异常，否则继续使用无事务运行机制
     */
    NEVER(TransactionDefinition.PROPAGATION_NEVER),

    /**
     * 在当前方法调用子方法时，如果子方法发生异常，
     * 只回滚子方法执行过的SQL，而不回滚当前方法的事务
     * 常用
     */
    NESTED(TransactionDefinition.PROPAGATION_NESTED);


    private final int value;

    Propagation(int value) {
        this.value = value;
    }
}
