package io.javabrains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MathUtilsTest {
	
	private MathUtils mathUtils;
	
	
	@BeforeEach
	void initEach() {
		mathUtils = new MathUtils();
		}

	@Nested
	class AddTest {
		@Test
		void testAddingTwoPositives() {
			assertEquals(2, mathUtils.add(1, 1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		void testAddingTwoNegatives() {
			assertEquals(-2, mathUtils.add(-1, -1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		void testAddingAPositiveAndANegative() {
			assertEquals(0, mathUtils.add(-1, 1), 
					"Add method should return the sum of two numbers");
		}
	}
	
	@Test 
	void testMultiply() {
		assertAll(
				() -> assertEquals(0, mathUtils.multiply(1, 0)),
				() -> assertEquals(1, mathUtils.multiply(1, 1)),
				() -> assertEquals(6, mathUtils.multiply(2, 3))
				);
	}
	
	@Test 
	void computeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), 
				"Should return right circle area");
	}
	
	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), 
				"Divide should throw ArithmeticException when denominator is zero");
	}
	
	@Nested
	class RootTest {
		@Test
		void testGettingNoRoots() {
			List<Double> confirm = new ArrayList<Double>();
			assertEquals(confirm, mathUtils.quadraticRoots(1, -3, 4), 
					"Roots method should return the roots of a quadratic");
			
			assertEquals(confirm, mathUtils.quadraticRoots(-1, 4, -7), 
					"Roots method should return the roots of a quadratic");
			
			assertEquals(confirm, mathUtils.quadraticRoots(2, -7, 12), 
					"Roots method should return the roots of a quadratic");
		}
		@Test
		void testGettingOneRoot() {
			List<Double> confirm = new ArrayList<Double>();
			confirm.add(1.5);
			assertEquals(confirm, mathUtils.quadraticRoots(-4, 12, -9), 
					"Roots method should return the roots of a quadratic");
			
			confirm = new ArrayList<Double>();
			confirm.add(3.0);
			assertEquals(confirm, mathUtils.quadraticRoots(1, -6, 9), 
					"Roots method should return the roots of a quadratic");
			
			confirm = new ArrayList<Double>();
			confirm.add(-1.0);
			assertEquals(confirm, mathUtils.quadraticRoots(1, 2, 1), 
					"Roots method should return the roots of a quadratic");
		}
		@Test
		void testGettingTwoRoots() {
			List<Double> confirm = new ArrayList<Double>();
			confirm.add(0.5);
			confirm.add(5.0);
			assertEquals(confirm, mathUtils.quadraticRoots(2, -11, 5), 
					"Roots method should return the roots of a quadratic");
			
			confirm.clear();
			confirm.add(1.0);
			confirm.add(1.5);
			assertEquals(confirm, mathUtils.quadraticRoots(2, -5, 3), 
					"Roots method should return the roots of a quadratic");
			
			confirm.clear();
			confirm.add(-5.0);
			confirm.add(2.0);
			assertEquals(confirm, mathUtils.quadraticRoots(1, 3, -10), 
					"Roots method should return the roots of a quadratic");
		}
	}
	
	
}