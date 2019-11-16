package io.javabrains;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a/b;
	}
	
	public double computeCircleArea(double radius) {
		return Math.PI * radius * radius;
	}
	
	public List<Double> quadraticRoots(int a, int b, int c){
		List<Double> roots = new ArrayList<Double>();
		double negativeCheck = Math.pow(b, 2) - 4 * a * c;
		if(negativeCheck < 0) {
			return roots;
		}
		double negative = (-b - Math.sqrt(negativeCheck))/(2 * a);
		double positive = (-b + Math.sqrt(negativeCheck))/(2 * a);
		
		if(negative == positive) {
			roots.add(negative);
		}
		else {
			roots.add(negative);
			roots.add(positive);
		}
		
		return roots;
	}
	
}
