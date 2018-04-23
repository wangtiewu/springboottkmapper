package mybatis.model;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository(value="helloMapper")
public interface HelloMapper extends Mapper<HelloDO> {
}
