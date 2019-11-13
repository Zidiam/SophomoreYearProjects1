package temperature;

// this Strategy works with a TemperatureModel's Celsius values

class CelsiusStrategy implements ValueStrategy {

	private TemperatureModel model;
	
	public CelsiusStrategy(TemperatureModel m) {
		model = m;
	}

	public void set(double d) {
		model.setC(d);
	}

	public double get() {
		return model.getC();
	}

}
