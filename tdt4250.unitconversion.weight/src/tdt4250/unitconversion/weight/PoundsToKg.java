package tdt4250.unitconversion.weight;

import org.osgi.service.component.annotations.Component;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;

@Component
public class PoundsToKg implements Converter {
	public static final Conversion LBS_TO_KG = new Conversion("lbs", "kg");

	public Double convert(Double value) {
		return value*0.453592;
	}

	@Override
	public Conversion conversion() {
		return LBS_TO_KG;
	}
}
