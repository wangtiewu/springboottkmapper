package mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "HELLO")
public class HelloDO {
	@Id
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
