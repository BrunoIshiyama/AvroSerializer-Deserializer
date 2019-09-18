package bean.source.test;

public class Job extends BaseSchema{
	private String name;
	private Integer code;
	private Task t;

	public Task getT() {
		return t;
	}

	public void setT(Task t) {
		this.t = t;
	}

	public int getCode() {
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
