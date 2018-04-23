package mybatis.model;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository(value="helloMapper1")
public interface HelloMapper1 extends Mapper<HelloDO> {
}
