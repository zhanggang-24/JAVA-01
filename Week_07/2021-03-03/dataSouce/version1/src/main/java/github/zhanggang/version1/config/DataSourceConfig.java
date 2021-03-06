package github.zhanggang.version1.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slaveDataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slaveDataSource2() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource targetDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                       @Qualifier("slaveDataSource1") DataSource slaveDataSource1,
                                       @Qualifier("slaveDataSource2") DataSource slaveDataSource2) {

        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE1, slaveDataSource1);
        targetDataSource.put(DBTypeEnum.SLAVE2, slaveDataSource2);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        //绑定所有数据源
        routingDataSource.setTargetDataSources(targetDataSource);
        //设置默认数据源
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

}
