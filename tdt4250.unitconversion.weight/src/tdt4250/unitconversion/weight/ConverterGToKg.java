package tdt4250.unitconversion.weight;

import org.osgi.service.component.annotations.Component;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;

@Component
public class ConverterGToKg implements Converter {

	public static final Conversion G_TO_KG = new Conversion("g", "kg");

	public Double convert(Double value) {
		return value*0.001;
	}

	@Override
	public Conversion conversion() {
		return G_TO_KG;
	}
}
