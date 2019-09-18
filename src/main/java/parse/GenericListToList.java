package parse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

public class GenericListToList {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List parse(List list,Schema listItemSchema) throws ClassCastException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, InstantiationException, ClassNotFoundException  {
		List returnList = new ArrayList<>();
		for(Object o : list) {
			if(o instanceof GenericData.Record) {
				GenericRecordToObject genericRecordToObject = new GenericRecordToObject();
				Class listItemClass = genericRecordToObject.findClassInstance(listItemSchema);
				Object record = genericRecordToObject.parse((GenericRecord) o,listItemClass);
				returnList.add(record);
			}else {
				return list;
			}
			
		}
		return returnList;
	}
}
