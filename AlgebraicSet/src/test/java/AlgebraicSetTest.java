import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgebraicSetTest {

	private Set<Character> mathSet;
	
	
	@BeforeEach
	void initEach() {
		//mathSet = new AlgebraicSet();
	}
	
	@Test
	void test() {
		Set<Character> testSet = new HashSet<Character>();
		testSet.add('A');
		testSet.add('B');
		testSet.add('C');
		testSet.add('D');
		testSet.add('E');
		mathSet = testSet;
		assertEquals(testSet, testSet);
	}

}
