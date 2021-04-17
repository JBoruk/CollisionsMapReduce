import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CollisionsMapper extends Mapper<LongWritable, Text, KeyWritable, LongWritable> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
			if (key.get() == 0)
				return;
			else {
				String line = value.toString();
				
				int i = 0;
				String streetName = null;
				String zipString = null;
				KeyWritable keyWritable = new KeyWritable();
				for (String word : line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) {
					if (i == 1) {
						String year = word.split("/")[2];
						
						if (Integer.parseInt(year) < 2012) {
							break;
						}
					}
					if (i == 2) {
						streetName = word;
					}
					if (i == 6) {
						zipString = word;
						
						if (zipString == null || zipString.isEmpty()) {
							break;
						}
					}
					if (i == 11) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("PEDESTRIAN"), new Text("INJURED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					if (i == 12) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("PEDESTRIAN"), new Text("KILLED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					if (i == 13) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("CYCLIST"), new Text("INJURED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					if (i == 14) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("CYCLIST"), new Text("KILLED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					if (i == 15) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("DRIVER"), new Text("INJURED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					if (i == 16) {
						keyWritable = new KeyWritable(new Text(streetName), new Text(zipString), new Text("DRIVER"), new Text("KILLED"));
						context.write(keyWritable, new LongWritable(Long.valueOf(word)));
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
