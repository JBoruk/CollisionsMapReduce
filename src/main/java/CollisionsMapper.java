import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CollisionsMapper extends Mapper<LongWritable, Text, StreetWritable, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
			if (key.get() == 0)
				return;
			else {
				String line = value.toString();
				
				int i = 0;
				String streetName = null;
				String zipString;
				StreetWritable street = new StreetWritable();
				for (String word : line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) {
					if (i == 1) {
						String year = word.split("/")[2];
						
						if (Integer.parseInt(year) < 2012) {
							break;
						}
					}
					if (i == 2) {
						streetName = word;
						
						if (streetName == null || streetName.isEmpty()) {
							break;
						}
					}
					if (i == 6) {
						zipString = word;
						
						if (zipString != null && !zipString.isEmpty()) {
							street = new StreetWritable(new Text(streetName), new Text(zipString));
						}
					}
					if (i == 11) {
						context.write(street, new Text("PEDESTRIAN-INJURED-" + word));
					}
					if (i == 12) {
						context.write(street, new Text("PEDESTRIAN-KILLED-" + word));
					}
					if (i == 13) {
						context.write(street, new Text("CYCLIST-INJURED-" + word));
					}
					if (i == 14) {
						context.write(street, new Text("CYCLIST-KILLED-" + word));
					}
					if (i == 15) {
						context.write(street, new Text("DRIVER-INJURED-" + word));
					}
					if (i == 16) {
						context.write(street, new Text("DRIVER-KILLED-" + word));
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
