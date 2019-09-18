package bean.source.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

public class BaseSchema {
	public Schema getSchema() throws IOException, InstantiationException, IllegalAccessException {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		String[] names = this.getClass().getName().toLowerCase().split("\\.");
		File f = new File(projectPath + "/src/main/avro/" + names[names.length - 1] + ".avsc");
		PrintStream defaultPrintStream = System.out;
		if (!f.exists()) {
			System.out.println("Creating avro...");
			try {
				f.createNewFile();
				System.setOut(new PrintStream(f));
				System.out.println(ReflectData.get().getSchema(this.getClass()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.setOut(defaultPrintStream);
			}

		} else {
			Schema schema  = new Schema.Parser().parse(f);
			if(!schema.equals(ReflectData.get().getSchema(this.getClass()))){
				updateSchema(f, this.getClass().newInstance());
			}
			System.out.println("Avro Already created...");
		}
		return ReflectData.get().getSchema(this.getClass());
	}
	private void updateSchema(File f,Object o) throws IOException {
		System.out.println("updating schema for "+o.getClass().getSimpleName());
		f.delete();
		f.createNewFile();
		FileWriter fw = new FileWriter(f);
		fw.append(ReflectData.get().getSchema(o.getClass()).toString());
		fw.flush();
		fw.close();
		
	}
}
