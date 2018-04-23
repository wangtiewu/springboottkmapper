package mybatis.jta;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration

public class DbConfigTest1 {
	@Primary
	@Bean(name="dataSourceTest1")
	public DataSource dataSourceTest1(DbProperties1 dbProperties1) throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(dbProperties1.getUrl());
        mysqlXADataSource.setUser(dbProperties1.getUsername());
        mysqlXADataSource.setPassword(dbProperties1.getPassword());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("dataSourceTest1");
        xaDataSource.setMinPoolSize(dbProperties1.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbProperties1.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbProperties1.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbProperties1.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbProperties1.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbProperties1.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbProperties1.getMaxIdleTime());
        xaDataSource.setTestQuery(dbProperties1.getTestQuery());
        return xaDataSource;
	}
	
	@Primary
	@Bean(name="sqlSessionFactoryTest1")
	public SqlSessionFactory sqlSessionFactoryTest1(@Qualifier("dataSourceTest1") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/model/*.xml"));
        bean.setTypeAliasesPackage("com.isea533.mybatis.model");
        return bean.getObject();
	}
	
	
}
