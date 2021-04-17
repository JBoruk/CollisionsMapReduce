import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class KeyWritable implements Writable {
	
	private Text streetName;
	private Text zipCode;
	private Text typeOfVictim;
	private Text natureOfInjury;
	
	
	public KeyWritable() {
		super();
		this.streetName = new Text();
		this.zipCode = new Text();
		this.typeOfVictim = new Text();
		this.natureOfInjury = new Text();
	}

	public KeyWritable(Text streetName, Text zipCode, Text typeOfVictim, Text natureOfInjury) {
		super();
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.typeOfVictim = zipCode;
		this.natureOfInjury = zipCode;
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
	
	public Text getTypeOfVictim() {
		return typeOfVictim;
	}

	public void setTypeOfVictim(Text typeOfVictim) {
		this.typeOfVictim = typeOfVictim;
	}

	public Text getNatureOfInjury() {
		return natureOfInjury;
	}

	public void setNatureOfInjury(Text natureOfInjury) {
		this.natureOfInjury = natureOfInjury;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.streetName.write(out);
		this.zipCode.write(out);
		this.typeOfVictim.write(out);
		this.natureOfInjury.write(out);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.streetName.readFields(in);
		this.zipCode.readFields(in);
		this.typeOfVictim.readFields(in);
		this.natureOfInjury.readFields(in);
	}
}
