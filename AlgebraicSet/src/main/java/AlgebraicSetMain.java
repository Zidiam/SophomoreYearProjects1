import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * AlgebraicSetMain.java -- Runs an input based test that a user can enter to find the union, intersect or difference in two sets
 * Jason Melnik
 * 11/17/2019
 */
public class AlgebraicSetMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AlgebraicSet<Character> A = new AlgebraicSet<Character>();
		Set<Character> B = new HashSet<Character>();
		
		
		boolean run = true;
		
		while(run == true) {
			
			System.out.println("Enter characters you want to put into set A (Example: ABC)");
			String inputA = scan.nextLine();
			for(int x = 0; x < inputA.length(); x++) {
				A.add(inputA.charAt(x));
			}
			
			System.out.println("Enter characters you want to put into set B(Example: ABC)");
			String inputB = scan.nextLine();
			for(int x = 0; x < inputB.length(); x++) {
				B.add(inputB.charAt(x));
			}
			
			System.out.println("Your Sets:");
			System.out.println("A: " + A);
			System.out.println("B: " + B);
			
			
			System.out.println("A U B" + A.union(B));
			System.out.println("A - B" + A.difference(B));
			System.out.println("A" + " \u2229 " + "B" + A.intersect(B));
			
			System.out.println("Do you want to continue? (Y or N)");
			String input = scan.nextLine();
			
			if(!input.equalsIgnoreCase("y")) {
				run = false;
			}
		}
	}
}
