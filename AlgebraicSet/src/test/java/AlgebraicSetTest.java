import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * AlgebraicSetTest.java -- This tests the intersection, union, and difference of two Sets
 * Jason Melnik
 * 11/16/2019
 */
class AlgebraicSetTest {

	private AlgebraicSet<Character> mathSet;
	private Set<Character> testSet;
	
	@BeforeEach
	void initEach() {
		testSet = new HashSet<Character>();
		testSet.add('A');
		testSet.add('B');
		testSet.add('C');
		testSet.add('D');
		testSet.add('E');
		
		mathSet = new AlgebraicSet<Character>();
		mathSet.add('A');
		mathSet.add('B');
		mathSet.add('C');
		mathSet.add('D');
		mathSet.add('E');
	}
	
	@Nested
	class testIntersect{
		
		@Test
		void noChange() {
			assertEquals(testSet, mathSet.intersect(testSet));
		}
		
		@Test
		void adding1Letter() {
			mathSet.add('F');
			assertEquals(testSet, mathSet.intersect(testSet));
		}
		
		@Test
		void adding2Letter() {
			mathSet.add('F');
			mathSet.add('G');
			assertEquals(testSet, mathSet.intersect(testSet));
		}
		
		@Test
		void removing1Letter() {
			mathSet.remove('E');
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.remove('E');
			assertEquals(tempTest, mathSet.intersect(testSet));
		}
		
		@Test
		void removing2Letters() {
			mathSet.remove('E');
			mathSet.remove('D');
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.remove('E');
			tempTest.remove('D');
			assertEquals(tempTest, mathSet.intersect(testSet));
		}
	}
	
	@Nested
	class testUnion{
		
		@Test
		void noChange() {
			assertEquals(testSet, mathSet.union(testSet));
		}
		
		@Test
		void adding1Letter() {
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.add('F');
			mathSet.add('F');
			assertEquals(tempTest, mathSet.union(testSet));
		}
		
		@Test
		void adding2Letters() {
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.add('F');
			tempTest.add('G');
			mathSet.add('F');
			mathSet.add('G');
			assertEquals(tempTest, mathSet.union(testSet));
		}
		
		@Test
		void removing1Letter() {
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.remove('F');
			mathSet.remove('F');
			assertEquals(tempTest, mathSet.union(testSet));
		}
		
		@Test
		void removing2Letters() {
			Set<Character> tempTest = new HashSet<Character>(testSet);
			tempTest.remove('F');
			tempTest.remove('G');
			mathSet.remove('F');
			mathSet.remove('G');
			assertEquals(tempTest, mathSet.union(testSet));
		}
	}
	
	@Nested
	class testDifference{
		
		@Test
		void noChange() {
			Set<Character> tempTest = new HashSet<Character>();
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void adding1LetterToA() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.add('F');
			tempTest.add('F');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void adding2LettersToA() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.add('F');
			mathSet.add('G');
			tempTest.add('F');
			tempTest.add('G');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void adding1LetterToB() {
			Set<Character> tempTest = new HashSet<Character>();
			testSet.add('F');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void adding2LettersToB() {
			Set<Character> tempTest = new HashSet<Character>();
			testSet.add('F');
			testSet.add('G');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void add1ToARemove1ToB() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.add('F');
			testSet.remove('E');
			tempTest.add('E');
			tempTest.add('F');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void add2ToARemove2ToB() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.add('F');
			mathSet.add('H');
			testSet.remove('E');
			testSet.remove('D');
			tempTest.add('F');
			tempTest.add('H');
			tempTest.add('E');
			tempTest.add('D');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void add1ToBRemove1ToA() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.remove('E');
			testSet.add('G');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
		@Test
		void add2ToBRemove2ToA() {
			Set<Character> tempTest = new HashSet<Character>();
			mathSet.remove('E');
			mathSet.remove('D');
			testSet.add('G');
			testSet.add('I');
			assertEquals(tempTest, mathSet.difference(testSet));
		}
		
	}

}
