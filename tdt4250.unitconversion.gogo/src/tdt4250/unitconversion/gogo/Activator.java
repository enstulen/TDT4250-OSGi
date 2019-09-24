package tdt4250.unitconversion.gogo;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;

public class Activator implements BundleActivator {

	private static Activator SINGLETON = null;
	
	static Activator getActivator() {
		return SINGLETON;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("started!");
		SINGLETON = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		SINGLETON = null;
	}
	
	private Map<Conversion, ServiceRegistration<Converter>> serviceRegistrations = new HashMap<Conversion, ServiceRegistration<Converter>>();

	
	public boolean addConverter(Converter converter) {
		boolean existed = removeConverter(converter.conversion());
		BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceRegistration<Converter> serviceRegistration = bc.registerService(Converter.class, converter, null);
		getServiceRegistrations().put(converter.conversion(), serviceRegistration);
		return existed;
	}
	
	public boolean removeConverter(Conversion conversion) {
		if (getServiceRegistrations().containsKey(conversion)) {
			getServiceRegistrations().get(conversion).unregister();
			getServiceRegistrations().remove(conversion);
			return true;
		}
		return false;
	}

	public Map<Conversion, ServiceRegistration<Converter>> getServiceRegistrations() {
		return serviceRegistrations;
	}
	
}

