package temperature;
// a little interface which makes it easier to deal with Fahrenheit vs Celsius values 

interface ValueStrategy {
	
	public void set(double d);
	public double get();
	
}