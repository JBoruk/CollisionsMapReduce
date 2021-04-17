import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionCombiner extends Reducer<StreetWritable, Text, StreetWritable, Text> {

	@Override
	public void reduce(StreetWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		
		for (Text val : values) {
			sum += val.get();
			count += 1;
		}
		average = sum / count;
		result.set(average);
		context.write(sumText, result);
	}

}
