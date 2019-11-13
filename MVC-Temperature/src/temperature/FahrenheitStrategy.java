package temperature;
// this Strategy works with a TemperatureModel's Fahrenheit values

class FahrenheitStrategy implements ValueStrategy {

	private TemperatureModel model;
	
	public FahrenheitStrategy(TemperatureModel m) {
		model = m;
	}

	public void set(double d) {
		model.setF(d);
	}

	public double get() {
		return model.getF();
	}

}
