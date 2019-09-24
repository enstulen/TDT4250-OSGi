package tdt4250.unitconversion.api;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ConverterImpl implements Converter{
	
	Conversion conversion;
	String expression;	

	public ConverterImpl(Conversion conversion, String expression) {
		super();
		this.conversion = conversion;
		this.expression = expression;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public Conversion conversion() {
		return conversion;
	}

	@Override
	public Double convert(Double value) {
		ScriptEngineManager mgr = new ScriptEngineManager();    
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");  
	    try {
			return (Double) engine.eval(String.valueOf(value) + expression);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}

}
