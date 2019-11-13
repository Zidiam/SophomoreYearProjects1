import java.util.Scanner;
/*
 * Compressor.java --  Main class to compress a file into a simpler form(bit strings) that only the decompressor can read
 * Jason Melnik
 * 
 */
public class Compressor {
	public static void main(String[] args) {
		String fileName, fileLocation, again;

		Scanner in = new Scanner(System.in);

		do
		{  
			System.out.println("Enter the location of the file you want to compress: (src)");
			fileLocation = in.nextLine();
			
			System.out.println("Enter the name of the file you want to compress: (test.txt)");
			fileName = in.nextLine();
			
			HuffmanCompressor encode = new HuffmanCompressor(fileLocation, fileName);
			encode.saveEncodedFile();
			
			System.out.print("Evaluate another expression [Y/N]? ");
			again = in.nextLine();
			System.out.println();
		}
		while (again.equalsIgnoreCase("y"));
		
	}
}
