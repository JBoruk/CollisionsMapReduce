import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import com.google.common.collect.ComparisonChain;

public class KeyWritable implements WritableComparable<KeyWritable> {
	
	private Text streetName = new Text();
	private Text zipCode = new Text();
	private Text typeOfVictim = new Text();
	private Text natureOfInjury = new Text();
	
	
	public KeyWritable() {
		super();
	}

	public KeyWritable(Text streetName, Text zipCode, Text typeOfVictim, Text natureOfInjury) {
		super();
		this.streetName.set(streetName);
		this.zipCode.set(zipCode);
		this.typeOfVictim.set(typeOfVictim);
		this.natureOfInjury.set(natureOfInjury);
	}

	public Text getStreetName() {
		return streetName;
	}

	public void setStreetName(Text streetName) {
		this.streetName.set(streetName);
	}

	public Text getZipCode() {
		return zipCode;
	}

	public void setZipCode(Text zipCode) {
		this.zipCode.set(zipCode);
	}
	
	public Text getTypeOfVictim() {
		return typeOfVictim;
	}

	public void setTypeOfVictim(Text typeOfVictim) {
		this.typeOfVictim.set(typeOfVictim);
	}

	public Text getNatureOfInjury() {
		return natureOfInjury;
	}

	public void setNatureOfInjury(Text natureOfInjury) {
		this.natureOfInjury.set(natureOfInjury);
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

	@Override
	public String toString() {
		return "KeyWritable [streetName=" + streetName + ", zipCode=" + zipCode + ", typeOfVictim=" + typeOfVictim
				+ ", natureOfInjury=" + natureOfInjury + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((natureOfInjury == null) ? 0 : natureOfInjury.hashCode());
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((typeOfVictim == null) ? 0 : typeOfVictim.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyWritable other = (KeyWritable) obj;
		if (natureOfInjury == null) {
			if (other.natureOfInjury != null)
				return false;
		} else if (!natureOfInjury.equals(other.natureOfInjury))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (typeOfVictim == null) {
			if (other.typeOfVictim != null)
				return false;
		} else if (!typeOfVictim.equals(other.typeOfVictim))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public int compareTo(KeyWritable o) {
		return ComparisonChain.start().compare(streetName, o.streetName)
				.compare(zipCode, zipCode)
				.compare(typeOfVictim, typeOfVictim)
				.compare(natureOfInjury, natureOfInjury)
				.result();
	}
}
