package tdt4250.unitconversion.jaxservlet;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.osgi.service.component.annotations.*;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

import tdt4250.unitconversion.api.Conversion;
import tdt4250.unitconversion.api.Converter;


@Component(service=UnitConverterResource.class)
@JaxrsResource
public class UnitConverterResource {
	
	private Map<Conversion, Converter> unitConverters = new HashMap<Conversion, Converter>();

	@Reference(
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			bind = "addUnitConverter",
			unbind = "removeUnitConverter"
	)
	
	public void addUnitConverter(Converter converter) {
		System.out.println("added converter: " + converter.conversion().getFrom() + "->" + converter.conversion().getTo());
		unitConverters.put(converter.conversion(), converter);
	}
	public void removeUnitConverter(Converter converter) {
		System.out.println("removed converter: " + converter.conversion().getFrom() + "->" + converter.conversion().getTo());
		unitConverters.remove(converter.conversion());
	}


	@GET
	@Path("/convert")
	public String convert(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("value") Double value) throws ScriptException {
		Conversion conversion = new Conversion(from, to);
		Converter converter = unitConverters.get(conversion);
		
		if (converter == null) {
			return "This conversion doesn't exist yet. Please add it.";
		}
		
		return String.valueOf(converter.convert(value));
	}



}