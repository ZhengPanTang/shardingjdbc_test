package com.tzp.test.shardingjdbc_test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tzp.test.shardingjdbc_test.dao.sharding.algorithm.TzpTestPreciseShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.druid.url}")
    private String dataSourceUrl;
    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.druid.username}")
    private String userName;
    @Value("${spring.datasource.druid.password}")
    private String password;
    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;
    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.druid.max-wait}")
    private long maxWait;
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private long minEvictableIdleTimeMillis;

    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceUrl);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return druidDataSource;
    }

    @Bean("shardingDataSource")
    @Primary
    public DataSource ShardingDataSource() throws SQLException {
        /** tzp_test表分片规则配置 逻辑表名+实际数据节点+表分片策略+库分片策略 **/
        TableRuleConfiguration tzpTestRuleConfiguration = new TableRuleConfiguration("tzp_test","ds.tzp_test_${0..1}");
        // 表分片策略配置 分片建+分片算法
        ShardingStrategyConfiguration tzpTestTableShardingConfiguration =
                new StandardShardingStrategyConfiguration("phone",new TzpTestPreciseShardingAlgorithm());
        tzpTestRuleConfiguration.setTableShardingStrategyConfig(tzpTestTableShardingConfiguration);

        /** 分片配置入口 **/
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tzpTestRuleConfiguration);

        /** 数据源配置MAP **/
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds", druidDataSource());

        /** 全局配置 **/
        Properties globalProperties = new Properties();
        globalProperties.put("sql.show",true);
        return ShardingDataSourceFactory.createDataSource(dataSourceMap,shardingRuleConfiguration,globalProperties);

    }
}
