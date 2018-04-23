package mybatis.jta;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.jta.util.CustomSqlSessionTemplate;
import spring.jta.util.MyMapperScannerConfigurer;

@Configuration
public class MySqlSessionTemplate1 {
	@Bean(name = "mySqlSessionTemplate")
	public SqlSessionTemplate mySqlSessionTemplate(
			@Qualifier("sqlSessionFactoryTest1") SqlSessionFactory sqlSessionFactoryTest1,
			@Qualifier("sqlSessionFactoryTest2") SqlSessionFactory sqlSessionFactoryTest2) {
		
		CustomSqlSessionTemplate mySqlSessionTemplate = new CustomSqlSessionTemplate(sqlSessionFactoryTest1);
		Map<Object, SqlSessionFactory> sqlSessionFactorys = new HashMap<>();
		sqlSessionFactorys.put("dataSourceTest1", sqlSessionFactoryTest1);
		sqlSessionFactorys.put("dataSourceTest2", sqlSessionFactoryTest2);
		mySqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactorys);
		return mySqlSessionTemplate;
	}
		
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MyMapperScannerConfigurer mapperScannerConfigurer = new MyMapperScannerConfigurer();
		mapperScannerConfigurer.setMarkerInterface(tk.mybatis.mapper.common.Mapper.class);
		mapperScannerConfigurer.setBasePackage("/");
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("mySqlSessionTemplate");
		return mapperScannerConfigurer;
	}
}
