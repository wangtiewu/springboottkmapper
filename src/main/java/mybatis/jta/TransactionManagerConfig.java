package mybatis.jta;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import spring.jta.util.MyMapperScannerConfigurer;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {
	@Bean
	public UserTransaction userTransaction() throws SystemException {
		UserTransaction userTransaction = new UserTransactionImp();
		userTransaction.setTransactionTimeout(300);
		return userTransaction;
	}
	
	@Bean()
	public TransactionManager atomikosTransactionManager() {
		TransactionManager transactionManager = new UserTransactionManager();
		((UserTransactionManager)transactionManager).setForceShutdown(true);
		return transactionManager;
	}
	
	@Bean
	@DependsOn({"userTransaction", "atomikosTransactionManager"})
	public PlatformTransactionManager txManager() throws SystemException {
		PlatformTransactionManager txManager = new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
		return txManager;
	}
}
