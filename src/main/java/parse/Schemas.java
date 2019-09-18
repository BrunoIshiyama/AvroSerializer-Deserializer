package parse;

import java.io.IOException;

import org.apache.avro.Schema;

import bean.source.test.BaseSchema;

public class Schemas {
	public static Schema getSchema(Object o) throws InstantiationException, IllegalAccessException, IOException {
		if(o instanceof BaseSchema) {
			return ((BaseSchema) o).getSchema();
		}
		throw new ClassCastException("Class " + o.getClass().getSimpleName() + " is not a type of BaseSchema");
	}
}
