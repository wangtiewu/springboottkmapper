package mybatis.model;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository(value="helloMapper2")
public interface HelloMapper2 extends Mapper<HelloDO> {
}
