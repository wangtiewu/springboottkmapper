package mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybatis.model.HelloDO;
import mybatis.model.HelloMapper;
import mybatis.model.HelloMapper1;
import mybatis.model.HelloMapper2;

@Service("helloWorldService")
public class HelloWorldService {
	@Autowired
	private HelloMapper helloMapper;
	
	@Autowired
	private HelloMapper1 helloMapper1;
	
	@Autowired
	private HelloMapper2 helloMapper2;
	
	public void addHello(HelloDO helloDO) {
		// TODO Auto-generated method stub
		helloMapper.insert(helloDO);
	}

	public void addHello2(HelloDO helloDO) {
		// TODO Auto-generated method stub
		helloMapper2.insert(helloDO);
	}

	public void addHello1(HelloDO helloDO) {
		// TODO Auto-generated method stub
		helloMapper1.insert(helloDO);
	}
}
