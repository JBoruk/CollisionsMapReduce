import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionCombiner extends Reducer<KeyWritable, LongWritable, KeyWritable, LongWritable> {

	@Override
	public void reduce(KeyWritable key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		
		Long sum = 0L;
		
		for (LongWritable val : values) {
			sum += val.get();
		}
		
		context.write(key, new LongWritable(sum));
	}

}
