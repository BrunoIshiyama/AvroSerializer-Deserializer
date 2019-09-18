package parse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.GenericRecord;

import bean.source.test.BaseSchema;

public class ListToGenericList {
	public List<Object> parse(List<Object> list) throws ClassCastException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, InstantiationException  {
		List<Object> returnList = new ArrayList<>();
		for(Object o : list) {
			if(o instanceof BaseSchema) {
				ObjectToGenericRecord genericObjectToGenericRecord = new ObjectToGenericRecord();
				GenericRecord record = genericObjectToGenericRecord.parse(o);
				returnList.add(record);
			}else {
				return list;
			}
			
		}
		return returnList;
	}
}
