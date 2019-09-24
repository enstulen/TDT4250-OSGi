# TDT4250 - OSGI

## Structure

The project is divided into modules. Currently these are `jaxservlet`, `gogo` and `api` as well as two specific implementations - `weight` and `currency`.

## Usage

Run the servlet `jaxservlet` in Eclipse using the `bnd.bnd` file and clicking `Run OSGi` under the `run` tab. This will run all the necessary bundles.

### Servlet

The servlet will run on port `8080`.
It takes three query params:

`from` : Unit to convert from

`to`: Unit to convert to

`value`: Value to be converted

Example usage:
http://localhost:8080/convert?from=kg&to=g&value=10

### Command Line (GoGo)

#### list

This will list all the active Converter-services.

#### convert

This takes in an argument on the form similar to that of the Servlet. For example `convert 'from=kg&to=g&value=50'`. (Remember the quotations!) This will convert 50kg to g.

#### add

This will add a new conversion. For example `add 'F=C*1.8+32'`. (Remember the quotations!). This will add the conversion between Fahrenheit and Celcius.
