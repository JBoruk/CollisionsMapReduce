import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionCombiner extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	private LongWritable finalSum = new LongWritable();
	Long sum;
	
	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		sum = 0L;

        for (LongWritable val : values) {
            sum += val.get();
        }
        
        finalSum.set(sum);
        context.write(key, finalSum);
	}

}
