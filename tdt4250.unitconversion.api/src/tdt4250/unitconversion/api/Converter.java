package tdt4250.unitconversion.api;

public interface Converter {
	Conversion conversion();
	Double convert(Double value);
}
