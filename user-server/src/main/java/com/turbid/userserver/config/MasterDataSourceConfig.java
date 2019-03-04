package com.turbid.userserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// @MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，为了和下面 cluster 数据源做到精确区分
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {


    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.turbid.userserver.dao";
    static final String MAPPER_LOCATION = "classpath:mapping/*.xml";


    @Bean(name = "masterDataSource")
    //  @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
    @Primary
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}