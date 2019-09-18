package main;

import java.util.ArrayList;
import java.util.List;

import bean.source.test.Dummy;
import bean.source.test.Job;
import bean.source.test.Task;
import manager.DummyAvro;

public class Test {
	public static void main(String[] args) throws Exception {
		Dummy d = new Dummy();
		d.setId(123);
		d.setUserName("Ginni");
		d.setMeu_long(97631293L);
		d.setMeu_byte(1.3f);
		d.setMeu_double(2.1);
		List<String> docs = new ArrayList<String>();
		docs.add("doc 1");
		ArrayList<Job> j = new ArrayList<Job>();
		Job jj = new Job();
		jj.setCode(1);
		jj.setName("java");
		Task t = new Task();
		t.setCode(9);
		t.setName("develop");
		t.setDocuments(docs);
		jj.setT(t);
		j.add(jj);
		d.setLista(j);
		d.setJ(jj);
		DummyAvro avro = new DummyAvro();
		byte[] bytes = avro.serialize(d);
		System.out.println(bytes);
		Dummy k = (Dummy) avro.deserialize(bytes,Dummy.class);
		System.out.println(k);
	}
}
