package com.honorzhang.data.synchronization.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 14:29
 **/
@Configuration
@MapperScan(basePackages = "com.honorzhang.data.synchronization.mapper.oracle", sqlSessionFactoryRef = "oracleSessionFactory")
public class OracleDataSourceConfig {
    @Bean("oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getOracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("oracleSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db1/*.xml"));
        return bean.getObject();
    }

    @Bean("oracleSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(@Qualifier("oracleSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
