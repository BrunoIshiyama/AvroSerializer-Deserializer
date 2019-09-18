
package parse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;

import bean.source.test.BaseSchema;

public class GenericRecordToObject {

	@SuppressWarnings("rawtypes")
	public Object parse(GenericRecord genericRecord, Class<?> type)
			throws ClassCastException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {

		BaseSchema target = (BaseSchema) type.newInstance();

		for (Field f : target.getSchema().getFields()) {
			String s = "set" + f.name().substring(0, 1).toUpperCase() + f.name().substring(1, f.name().length());
			Object o = genericRecord.get(f.name());
			Class classInstance = findClassInstance(f.schema());
			Method m = target.getClass().getMethod(s, classInstance);

			if (!(o instanceof List)) {
				if (!(o instanceof GenericData.Record)) {
					if (o instanceof Utf8) {
						m.invoke(target, o.toString());
					} else {
						m.invoke(target, classInstance.cast(o));
					}
				} else {
					GenericRecordToObject g = new GenericRecordToObject();
					m.invoke(target, classInstance.cast(g.parse((GenericRecord) o,classInstance)));
				}
			} else {
				System.out.println("lista");
				GenericListToList genericListToList = new GenericListToList();
				Schema listItemSchema = f.schema().getElementType();
				m.invoke(target, genericListToList.parse((List) o,listItemSchema));
			}

		}
		return target;
	}
	@SuppressWarnings("rawtypes")
	public Class findClassInstance(Schema schema) throws ClassNotFoundException {
		String className = schema.getFullName();
		switch (className) {
			case "string":
				className = "java.lang.String";
				break;
			case "int":
				className = "java.lang.Integer";
				break;
			case "long":
				className = "java.lang.Long";
				break;
			case "double":
				className = "java.lang.Double";
				break;
			case  "float":
				className = "java.lang.Float";
				break;
			default:
				if(schema.getProp("java-class") != null)
					className = schema.getProp("java-class");
				if(schema.getProp("namespace") != null && schema.getProp("name") != null)
					className = schema.getProp("namespace")+"."+schema.getProp("name");
		}
		return Class.forName(className);
	}
}
