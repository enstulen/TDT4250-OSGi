package tdt4250.unitconversion.api;

import java.util.Objects;

public class Conversion {
	private String from;
	private String to;
	
	public Conversion(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	
	
	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversion other = (Conversion) obj;
		return Objects.equals(from, other.from) && Objects.equals(to, other.to);
	}
	
	
}
