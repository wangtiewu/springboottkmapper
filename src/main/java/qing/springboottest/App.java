package qing.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mybatis.model.HelloDO;
import mybatis.service.HelloWorldService;
import spring.jta.util.DataSourceContextHolder;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = ("mybatis/service,mybatis/jta"))
@RestController
@MapperScan(basePackages = "mybatis")
public class App {
	@Autowired
	HelloWorldService helloWorldService;

	@RequestMapping("/")
	public String greeting() {
		return "Hello World!";
	}
	
	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String hello(@RequestParam String name) {
		return "Hello "+name+", I'm from port "+port;
	}

	@Transactional
	@RequestMapping(value = "/create/hello", method = RequestMethod.POST, consumes = { "text/plain", "application/*" })
	public @ResponseBody long createHello(@RequestBody HelloDO helloDO) {
		helloWorldService.addHello(helloDO);
		DataSourceContextHolder.setDBType("dataSourceTest2");
		helloWorldService.addHello(helloDO);
		return helloDO.getId();
	}
	
	@Transactional
	@RequestMapping(value = "/create/hello1", method = RequestMethod.POST, consumes = { "text/plain", "application/*" })
	public @ResponseBody long createHello1(@RequestBody HelloDO helloDO) {
		helloWorldService.addHello1(helloDO);
		return helloDO.getId();
	}
	
	@Transactional
	@RequestMapping(value = "/create/hello2", method = RequestMethod.POST, consumes = { "text/plain", "application/*" })
	public @ResponseBody long createHello2(@RequestBody HelloDO helloDO) {
		helloWorldService.addHello2(helloDO);
		return helloDO.getId();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("Hello World!");
	}
}
