import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionsReducer extends Reducer<IntWritable, CollisionsWritable, IntWritable, CollisionsWritable> {

	@Override
	public void reduce(IntWritable key, Iterable<CollisionsWritable> values, Context context)
			throws IOException, InterruptedException {

		Text sumText = new Text("average size of station for " + key + " year is: ");

		for (IntWritable val : values) {
			sum += val.get();
			count += 1;
		}
		average = sum / count;
		result.set(average);
		context.write(sumText, result);
	}

}
