import java.util.Scanner;
/*
 * Decompressor.java -- Main class to decompress a file into readable form.
 * Jason Melnik
 * 
 */
public class Decompressor {
	public static void main(String[] args) {
		String fileName, fileLocation, again;

		Scanner in = new Scanner(System.in);

				
		do
		{  
			System.out.println("Enter the location of the file you want to decompress: (src)");
			fileLocation = in.nextLine();
			
			System.out.println("Enter the name of the file you want to decompress: (test.txt)");
			fileName = in.nextLine();
			
			HuffmanDecompressor decode = new HuffmanDecompressor(fileLocation, fileName);
			decode.saveDecodedFile();
			
			System.out.print("Evaluate another expression [Y/N]? ");
			again = in.nextLine();
			System.out.println();
		}
		while (again.equalsIgnoreCase("y"));
	}
}
