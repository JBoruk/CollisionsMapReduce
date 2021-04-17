import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Collisions extends Configured implements Tool {
	
	private static String INPUT_PATH = "C:\\Users\\borat\\Downloads\\mapreduce\\input\\datasource1";
	private static String OUTPUT_PATH = "C:\\Users\\borat\\Downloads\\mapreduce\\output";

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Collisions(), args);
		System.exit(res);
	}

	public int run(String[] args) throws Exception {
		Job job = Job.getInstance(getConf(), "wordcount");
		job.setJarByClass(this.getClass());
		// Use TextInputFormat, the default unless job.setInputFormatClass is used
		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		job.setMapperClass(CollisionsMapper.class);
		job.setCombinerClass(cls);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static class Reduce extends Reducer<LongWritable, CollisionsWritable, Text, IntWritable> {
		@Override
		public void reduce(LongWritable word, Iterable<CollisionsWritable> counts, Context context)
				throws IOException, InterruptedException {
			
			System.out.println("Jestem ");
		}
	}
}