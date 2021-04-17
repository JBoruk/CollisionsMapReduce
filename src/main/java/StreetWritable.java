import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class StreetWritable implements Writable {
	
	private Text streetName;
	private Text zipCode;
	
	public StreetWritable() {
		super();
		this.streetName = new Text();
		this.zipCode = new Text();
	}

	public StreetWritable(Text streetName, Text zipCode) {
		super();
		this.streetName = streetName;
		this.zipCode = zipCode;
	}

	public Text getStreetName() {
		return streetName;
	}

	public void setStreetName(Text streetName) {
		this.streetName = streetName;
	}

	public Text getZipCode() {
		return zipCode;
	}

	public void setZipCode(Text zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.streetName.write(out);
		this.zipCode.write(out);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.streetName.readFields(in);
		this.zipCode.readFields(in);
	}
}
