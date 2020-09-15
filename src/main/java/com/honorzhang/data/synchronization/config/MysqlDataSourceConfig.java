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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 14:29
 **/
@Configuration
@MapperScan(basePackages = "com.honorzhang.data.synchronization.mapper.mysql", sqlSessionFactoryRef = "mySqlSessionFactory")
public class MysqlDataSourceConfig {
    @Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
    @Bean("mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getDb1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("mySqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db1/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("mySqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
