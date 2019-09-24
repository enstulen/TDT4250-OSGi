package tdt4250.unitconversion.weight;

import org.osgi.service.component.annotations.*;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;

@Component
public class ConverterKgToG implements Converter {
	
	public static final Conversion KG_TO_G = new Conversion("kg", "g");

	public Double convert(Double value) {
		return value*1000;
	}

	@Override
	public Conversion conversion() {
		return KG_TO_G;
	}

	
	
}
