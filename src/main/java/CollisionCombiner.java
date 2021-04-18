import java.io.IOException;
import java.util.logging.Logger;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionCombiner extends Reducer<KeyWritable, LongWritable, KeyWritable, LongWritable> {
	
	private static final Logger logger = Logger.getLogger(CollisionCombiner.class.getName());
	
	@Override
	public void reduce(KeyWritable key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		
		Long sum = 0L;
		
		for (LongWritable val : values) {
			sum += val.get();
		}
		
		logger.info("COMBINER. Summed: " + sum + ". Key: " + key.getTypeOfVictim() + "-" + key.getNatureOfInjury());
		
		context.write(key, new LongWritable(sum));
	}

}
