package bean.source.test;

import java.util.List;

import org.apache.avro.util.Utf8;

public class Dummy extends BaseSchema {

	private Integer id;
	private String userName;
	private Long meu_long;
	private Float meu_byte;
	private Double meu_double;
	private Job j;
	private List<Job> lista;

	public List<Job> getLista() {
		return lista;
	}

	public void setLista(List<Job> lista) {
		this.lista = lista;
	}
//
	public Job getJ() {
		return j;
	}

	public void setJ(Job j) {
		this.j = j;
	}

	public Double getMeu_double() {
		return meu_double;
	}

	public void setMeu_double(Double meu_double) {
		this.meu_double = meu_double;
	}

	public Float getMeu_byte() {
		return meu_byte;
	}

	public void setMeu_byte(Float meu_byte) {
		this.meu_byte = meu_byte;
	}

	public int getId() {
		return id;
	}

	public long getMeu_long() {
		return meu_long;
	}

	public void setMeu_long(Long meu_long) {
		this.meu_long = meu_long;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public void setUserName(Utf8 username) {
		this.userName = username.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id + ",");
		sb.append(userName + ",");
		sb.append(meu_long);
		sb.append(",");
		sb.append(meu_byte);
		sb.append(",");
		sb.append(meu_double);
		sb.append(",");
		getLista().forEach(l -> sb.append(l.getName() + " " + l.getCode() +" " + l.getT().getName() +"\n"));
		sb.append(getJ().getCode());
		sb.append(",");
		sb.append(getJ().getName());
		return sb.toString();
	}

}
