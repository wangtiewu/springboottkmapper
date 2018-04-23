package mybatis.jta;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import spring.jta.util.DataSourceContextHolder;

@Aspect
@Component
public class DbAop {
	public DbAop() {
		int a = 0;
		a++;
	}

	@Before("execution(* mybatis.model.HelloMapper1.*(..))")
	public void setDbTest1(JoinPoint joinPoint) {
		DataSourceContextHolder.setDBType("dataSourceTest1");
	}

	@Before("execution(* mybatis.model.HelloMapper2.*(..))")
	public void setDbTest2(JoinPoint joinPoint) {
		DataSourceContextHolder.setDBType("dataSourceTest2");
	}
}
