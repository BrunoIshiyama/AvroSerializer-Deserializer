package bean.source.test;

import java.util.List;

public class Task extends BaseSchema{
	private String name;
	private Integer code;
	private List<String> documents;

	public List<String> getDocuments() {
		return documents;
	}



	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}



	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
}
