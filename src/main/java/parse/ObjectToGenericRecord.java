package parse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import bean.source.test.BaseSchema;

public class ObjectToGenericRecord {

	@SuppressWarnings("unchecked")
	public GenericRecord parse(Object target)
			throws ClassCastException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {

		
		Schema schema = Schemas.getSchema(target);
		GenericRecord record = new GenericData.Record(schema);

		for (Field f : target.getClass().getDeclaredFields()) {
			String s = "get" + f.getName().substring(0, 1).toUpperCase()
					+ f.getName().substring(1, f.getName().length());
			Method m = target.getClass().getMethod(s);
			Object o = m.invoke(target);

			if (!(o instanceof List)) {
				if (!(o instanceof BaseSchema)) {
					record.put(f.getName(), o);
				} else {
					ObjectToGenericRecord g = new ObjectToGenericRecord();
					record.put(f.getName(), g.parse(o));
				}
			} else {
				System.out.println("lista");
				ListToGenericList listToGenericList = new ListToGenericList();
				record.put(f.getName(), listToGenericList.parse((List<Object>) o));
			}

		}
		return record;
	}
}
