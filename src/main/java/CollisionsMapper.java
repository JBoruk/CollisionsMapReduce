import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CollisionsMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	
	private static final String BREAK_TEXT = "<BREAK>";
	
	private Text zipCode = new Text();
    private Text streetName = new Text();
    private Text finalKey = new Text();
    private LongWritable number = new LongWritable();
    
    private void fillKey(String victim, String injury) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(victim);
    	sb.append(BREAK_TEXT);
    	sb.append(injury);
    	sb.append(BREAK_TEXT);
    	sb.append(zipCode);
    	sb.append(BREAK_TEXT);
    	sb.append(streetName);
    	
    	finalKey.set(sb.toString());
    }

    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            if (key.get() != 0) {
                String line = value.toString();
                int i = 0;
                
                for (String word : line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) {
                	if (i == 1) {
                		String[] dateArr = word.split("/");
                		String year = dateArr[2];
                    	
                		if (Integer.valueOf(year) < 2012) {
                			continue;
                		}
                    }
                    if (i == 2) {
                    	if (word == null || word.isEmpty()) {
                    		continue;
                    	}
                    	
                    	zipCode.set(word);
                    }
                    if (i == 6) {
                    	streetName.set(word);
                    }
                    if (i == 11) {
                    	number.set(Long.parseLong(word));
                    	fillKey("PEDESTRIAN", "INJURED");
						context.write(finalKey, number);
					}
					if (i == 12) {
						number.set(Long.parseLong(word));
                    	fillKey("PEDESTRIAN", "KILLED");
						context.write(finalKey, number);
					}
					if (i == 13) {
						number.set(Long.parseLong(word));
						fillKey("CYCLIST", "INJURED");
						context.write(finalKey, number);
					}
					if (i == 14) {
						number.set(Long.parseLong(word));
						fillKey("CYCLIST", "KILLED");
						context.write(finalKey, number);
					}
					if (i == 15) {
						number.set(Long.parseLong(word));
						fillKey("DRIVER", "INJURED");
						context.write(finalKey, number);
					}
					if (i == 16) {
						number.set(Long.parseLong(word));
						fillKey("DRIVER", "KILLED");
						context.write(finalKey, number);
					}
					
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
