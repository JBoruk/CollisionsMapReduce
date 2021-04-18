import java.io.IOException;
import java.util.logging.Logger;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CollisionsReducer extends Reducer<KeyWritable, LongWritable, Text, LongWritable> {
	
	private static final Logger logger = Logger.getLogger(CollisionsReducer.class.getName());
	  
	@Override
	public void reduce(KeyWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

		Text text = new Text(String.format("ZipCode: %s, StreetName: %s, Victim: %s, Injury: %s", 
				key.getZipCode(), key.getStreetName(), key.getTypeOfVictim(), key.getNatureOfInjury()));

		Long sum = 0L;
		
		for (LongWritable val : values) {
			sum += val.get();
		}
		
		logger.info("REDUCER. Summed: " + sum + ". Key: " + key.getTypeOfVictim() + "-" + key.getNatureOfInjury());
		
		context.write(text, new LongWritable(sum));
	}

}
