package mybatis.jta;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import spring.jta.util.DbProperties;

@Configuration
@ConfigurationProperties(prefix="spring.datasource.test2")
public class DbProperties2 extends DbProperties{

}
