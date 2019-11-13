/*
 * Algorithms.java -- The 3 algorithms from discrete mathematics in lexigraphic order (finding the next permutation, finding the next bit string, finding the next r combination) 
 * Jason Melnik
 * 9/19/2019
 */

public class Algorithms {
	
	public static int[] nextPerm(int[] perm) {
		int n = perm.length - 1;
		int j = n - 1;
		while(perm[j] > perm[j + 1]) {
			j--;
		}
		int k = n;
		while(perm[j] > perm[k]) {
			k --;
		}
		
		int temp = perm[j];
		perm[j] = perm[k];
		perm[k] = temp;
		
		int r = n;
		int s = j + 1;
		while(r > s) {
			int temps = perm[r];
			perm[r] = perm[s];
			perm[s] = temps;
			r --;
			s ++;
		}
		return perm;
	}
	
	public static String nextPermS(int[] perm) {
		int[] fin = nextPerm(perm);
		String t = "";
		
		for(int x = 0; x < fin.length-1; x++) {
			t += fin[x] + ", ";
		}
		
		t += fin[fin.length-1];
		
		return t;
	}
	
	public static String nextBitS(int[] perm) {
		int[] fin = nextBit(perm);
		String t = "";
		
		for(int x = 0; x < fin.length-1; x++) {
			t += fin[x] + ", ";
		}
		
		t += fin[fin.length-1];
		
		return t;
	}
	
	public static int[] nextBit(int[] bits) {
		int i = bits.length-1;
		while(bits[i] == 1) {
			bits[i] = 0;
			i --;
			System.out.println(i);
		}
		System.out.println(i);
		bits[i] = 1;
		return bits;
	}
	
	public static int[] nextRCombination(int[] subset, int[] set) {
		int r = subset.length - 1;
		int i = r;
		int n = set.length;
		while(subset[i] == (n - r + i)) {
			i --;
		}
		
		subset[i] = (subset[i] + 1);
		for(int j = i + 1; j <= r; j++) {
			subset[j] = (subset[i] + j - i);
		}
		
		return subset;
	}
	
	public static String nextRComboS(int[] sub, int[] se) {
		int[] fin = nextRCombination(sub, se);
		String t = "";
		
		for(int x = 0; x < fin.length-1; x++) {
			t += fin[x] + ", ";
		}
		
		t += fin[fin.length-1];
		
		return t;
	}
	
	public static void main(String[] args) {
		int[] permutation = {1, 2, 3, 6, 5, 4};
		int[] nextPermutation = nextPerm(permutation);
		for(int x : nextPermutation) {
			System.out.print(x + ", ");
		}
		
		System.out.println();
		
		int[] bits = {0, 1, 1, 0, 1};
		int[] nextBits = nextBit(bits);
		for(int x : nextBits) {
			System.out.print(x + ", ");
		}
		
		System.out.println();
		
		int[] rcombo = {1, 2, 3};
		int[] combo = {1, 2, 3, 4, 5};
		int[] nextrCombo = nextRCombination(rcombo, combo);
		for(int x : nextrCombo) {
			System.out.print(x + ", ");
		}
		
	}
}
