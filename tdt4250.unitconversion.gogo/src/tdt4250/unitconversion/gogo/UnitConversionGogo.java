package tdt4250.unitconversion.gogo;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.*;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;
import tdt4250.unitconversion.api.ConverterImpl;

@Component(
		service = UnitConversionGogo.class,
		property = {
				"osgi.command.scope=unitconversion",
				"osgi.command.function=add",
				"osgi.command.function=convert",
				"osgi.command.function=list",
		}
		)public class UnitConversionGogo {

	public void add(String conversionString) {

		String[] split = conversionString.split("=");
		String to = split[0];
		String from = split[1].substring(0, 1);

		String expression = split[1].substring(1, split[1].length());

		Conversion conversion = new Conversion(from, to);
		ConverterImpl converter = new ConverterImpl(conversion, expression);

		boolean existed = Activator.getActivator().addConverter(converter);
		System.out.println("\"" + conversion.getFrom() + conversion.getTo() + "\" converter " + (existed ? "replaced" : "added"));
	}

	public void convert(String conversionString) {
		try {
			String[] split = conversionString.split("&");
			String from = split[0].split("=")[1];
			String to = split[1].split("=")[1];
			String value = split[2].split("=")[1];
			Double valueDouble = Double.parseDouble(value);

			BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
			for (ServiceReference<Converter> serviceReference : bc.getServiceReferences(Converter.class, null)) {
				Converter converter = bc.getService(serviceReference);
				if (converter != null) {
					if (converter.conversion().getFrom().equals(from) && converter.conversion().getTo().equals(to)) {
						System.out.println(converter.convert(valueDouble));
					}
				}
			}
		} catch (Exception ex)  {
			System.out.println("Something went wrong. Maybe you haven't added the conversion, or your syntax might be wrong.");
		}

	}

	public void list() {
		System.out.println("conversions: ");
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		try {
			for (ServiceReference<Converter> serviceReference : bc.getServiceReferences(Converter.class, null)) {
				Converter converter = bc.getService(serviceReference);
				try {
					if (converter != null) {
						System.out.println("From: " + converter.conversion().getFrom() + " To: "+ converter.conversion().getTo());
					}
				} finally {
					bc.ungetService(serviceReference);
				}
				System.out.print(" ");
			}
		} catch (InvalidSyntaxException e) {
		}
		System.out.println();
	}

}
