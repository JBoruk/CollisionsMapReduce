import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class CollisionsWritable implements Writable {
	
	private Text streetName;
	private IntWritable pedestriansInjured;
	private IntWritable pedestriansKilled;
	private IntWritable driversInjured;
	private IntWritable driversKilled;
	private IntWritable cyclistsInjured;
	private IntWritable cyclistsKilled;
	
	public CollisionsWritable() {
		super();
		this.streetName = new Text();
		this.pedestriansInjured = new IntWritable(0);
		this.pedestriansKilled = new IntWritable(0);
		this.driversInjured = new IntWritable(0);
		this.driversKilled = new IntWritable(0);
		this.cyclistsInjured = new IntWritable(0);
		this.cyclistsKilled = new IntWritable(0);
	}

	public CollisionsWritable(Text streetName, IntWritable pedestriansInjured, IntWritable pedestriansKilled,
			IntWritable driversInjured, IntWritable driversKilled, IntWritable cyclistsInjured,
			IntWritable cyclistsKilled) {
		super();
		this.streetName = streetName;
		this.pedestriansInjured = pedestriansInjured;
		this.pedestriansKilled = pedestriansKilled;
		this.driversInjured = driversInjured;
		this.driversKilled = driversKilled;
		this.cyclistsInjured = cyclistsInjured;
		this.cyclistsKilled = cyclistsKilled;
	}

	public Text getStreetName() {
		return streetName;
	}

	public void setStreetName(Text streetName) {
		this.streetName = streetName;
	}

	public IntWritable getPedestriansInjured() {
		return pedestriansInjured;
	}

	public void setPedestriansInjured(IntWritable pedestriansInjured) {
		this.pedestriansInjured = pedestriansInjured;
	}

	public IntWritable getPedestriansKilled() {
		return pedestriansKilled;
	}

	public void setPedestriansKilled(IntWritable pedestriansKilled) {
		this.pedestriansKilled = pedestriansKilled;
	}

	public IntWritable getDriversInjured() {
		return driversInjured;
	}

	public void setDriversInjured(IntWritable driversInjured) {
		this.driversInjured = driversInjured;
	}

	public IntWritable getDriversKilled() {
		return driversKilled;
	}

	public void setDriversKilled(IntWritable driversKilled) {
		this.driversKilled = driversKilled;
	}

	public IntWritable getCyclistsInjured() {
		return cyclistsInjured;
	}

	public void setCyclistsInjured(IntWritable cyclistsInjured) {
		this.cyclistsInjured = cyclistsInjured;
	}

	public IntWritable getCyclistsKilled() {
		return cyclistsKilled;
	}

	public void setCyclistsKilled(IntWritable cyclistsKilled) {
		this.cyclistsKilled = cyclistsKilled;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.streetName.write(out);
		this.pedestriansInjured.write(out);
        this.pedestriansKilled.write(out);
        this.driversInjured.write(out);
        this.driversKilled.write(out);
        this.cyclistsInjured.write(out);
        this.cyclistsKilled.write(out);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.streetName.readFields(in);
		this.pedestriansInjured.readFields(in);
        this.pedestriansKilled.readFields(in);
        this.driversInjured.readFields(in);
        this.driversKilled.readFields(in);
        this.cyclistsInjured.readFields(in);
        this.cyclistsKilled.readFields(in);
	}
	
	@Override
    public String toString() {
        return this.streetName.toString() + "\t" + this.pedestriansInjured.toString() + "\t" + this.pedestriansKilled.toString()
        	+ "\t" + this.driversInjured.toString() + "\t" + this.driversKilled.toString()
        	+ "\t" + this.cyclistsInjured.toString() + "\t" + this.cyclistsKilled.toString();
    }

}
