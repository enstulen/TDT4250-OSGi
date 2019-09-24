package tdt4250.unitconversion.currency;

import org.osgi.service.component.annotations.*;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;

@Component
public class DollarToEuro implements Converter {

	public static final Conversion DOLLAR_TO_EURO = new Conversion("$", "€");

	public Double convert(Double value) {
		return value*0.91;
	}

	@Override
	public Conversion conversion() {
		return DOLLAR_TO_EURO;
	}
}
