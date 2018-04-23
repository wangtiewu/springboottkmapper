package mybatis.jta;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration

public class DbConfigTest2 {
	
	@Bean(name="dataSourceTest2")
	public DataSource dataSourceTest2(DbProperties2 dbProperties) throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(dbProperties.getUrl());
        mysqlXADataSource.setUser(dbProperties.getUsername());
        mysqlXADataSource.setPassword(dbProperties.getPassword());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("dataSourceTest2");
        xaDataSource.setMinPoolSize(dbProperties.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbProperties.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbProperties.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbProperties.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbProperties.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbProperties.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbProperties.getMaxIdleTime());
        xaDataSource.setTestQuery(dbProperties.getTestQuery());
        return xaDataSource;
	}
	
	@Bean(name="sqlSessionFactoryTest2")
	public SqlSessionFactory sqlSessionFactoryTest2(@Qualifier("dataSourceTest2") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/model/*.xml"));
        bean.setTypeAliasesPackage("com.isea533.mybatis.model");
        return bean.getObject();
	}
	
	
}
