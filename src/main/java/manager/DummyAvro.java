package manager;

import java.io.ByteArrayOutputStream;

import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;

import bean.source.test.BaseSchema;
import parse.GenericRecordToObject;
import parse.ObjectToGenericRecord;
import parse.Schemas;

public class DummyAvro {

	private ObjectToGenericRecord objectToGenericRecord = new ObjectToGenericRecord();
	private GenericRecordToObject genericRecordToObject = new GenericRecordToObject();

	public byte[] serialize(BaseSchema d) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(d.getSchema());
		GenericRecord record = objectToGenericRecord.parse(d);
		writer.write(record, encoder);
		encoder.flush();
		out.close();
		return out.toByteArray();
	}

	public Object deserialize(byte[] input,Class<?> type) throws Exception {
		BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(input, null);
		System.out.println(type);
		GenericDatumReader<GenericRecord> reader = new GenericDatumReader<>(Schemas.getSchema(type.newInstance()));
		GenericRecord g = reader.read(null, decoder);
		System.out.println(g);
		return genericRecordToObject.parse(g, type);
	}
}
