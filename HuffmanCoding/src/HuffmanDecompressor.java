import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
/*
 * HuffmanDecompressor.java -- Class that converts decompressed text from a file and converts it back to readable text for humans
 * Jason Melnik
 * 10/25/2019
 */
public class HuffmanDecompressor{
	private Map<Character, String> dictionary;
	private String decodedString;
	private File file;
	private String fileString;
	private String bitString;
	private String fileLocation;
	private String fileName;
	private int amount;
	private boolean decompressed;
	
	/**
	 * Creates a HuffmanDecompressor that will decompress a compressed file into readable form
	 * @param fileLocation which is a String used to find the location of the compressed file
	 * @param fileName which is a String used to find the name of the compressed file
	 */
	public HuffmanDecompressor(String fileLocation, String fileName) {
		decompressed = false;
		file = new File(fileLocation + "//" + fileName);
		amount = 0;
		this.fileLocation = fileLocation;
		this.fileName = fileName;
		fileString = getFileString();
		dictionary = getDictionary();
		decodedString = getDecodedString();
	}
	
	/**
	 * Saves the file but before it does it has to check if it is already decompressed.
	 */
	public void saveDecodedFile() {
		if(!decompressed) {
			saveFile();
		}
	}
	
	/**
	 * This gets all the characters from the file and returns a string of all of them put together
	 * This method also sets bitString up for the rest of the object to use
	 * bitString is the 0s and 1s that the file had
	 * @return String that is the dictionary of the compressed file
	 */
	private String getFileString() {
		String temp = "";
		try {
			BitInputStream stream = new BitInputStream(file);
			
			String noComma = "";
			int fileRead;
			while(!noComma.equals(",   ") && (fileRead = stream.read()) != -1) {
				char character = (char) fileRead;
				if(noComma.length() < 4) {
					noComma += character + "";
				}
				else {
					noComma = noComma.substring(1, 4) + character + "";
				}
				temp += character;
			}
			
			
			String bits = "";
			int byts;
			while((byts = stream.readBits(1)) != -1) {
				bits += byts;
			}
			
			bitString = bits;
			
			stream.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return temp;
	}
	
	/**
	 * This saves the decodedString into a new file
	 * @throws Exception if something goes wrong with finding the file
	 */
	public void saveFile() {
		try {
			PrintWriter writer = new PrintWriter(fileLocation + "//Decompressed_" + fileName);
			writer.print(decodedString);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	/**
	 * We get the decoded String from this method
	 * Reads the bitString and compares the next combination of bits to a key in the map
	 * @return String which is the decoded String that people can read
	 */
	private String getDecodedString() {
		//The code below just swaps the map from <Character, String> to <String, Character>
		Map<String, Character> swapped = dictionary.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
		
		String result = "";
		String temp = "";
		int letters = 0;
		for(int scan = 0; scan < bitString.length(); scan++) {
			if(letters < amount) {
				temp += bitString.charAt(scan);
				if(swapped.get(temp) != null) {
					result += swapped.get(temp);
					temp = "";
					letters++;
				}
			}
			
		}
		return result;
	}
	
	/**
	 * This method we get the dictionary of the encoded file
	 * helper method for getdecodedString()
	 * @return Map which is the dictionary of the compressed file
	 */
	private Map<Character, String> getDictionary() {
		Map<Character, String> map = new HashMap<Character, String>();
		
		String amountOfCharacters = "";
		for(int scan = 0; scan < fileString.length(); scan++) {
			if(fileString.charAt(scan) != ',') {
				amountOfCharacters += fileString.charAt(scan);
			}
			else {
				fileString = fileString.substring(scan, fileString.length());
				break;
			}
		}
		try {
			amount = new Integer(amountOfCharacters);
		}catch(Exception e) {
			System.err.println("File already decompressed");
			decompressed = true;
		}
		if(!decompressed) {
			String part = "";
			boolean foundComma = false;
			for(int scan = 0; scan < fileString.length(); scan++) {
				if(foundComma == true) {
					part += fileString.charAt(scan);
				}
				if(fileString.charAt(scan) == ',' && !part.equals(",")) {
					foundComma = !foundComma;
				}
				if(foundComma == false) {
					map.put(part.charAt(0), part.substring(2, part.length()-1));
					part = "";
					foundComma = true;
				}
			}
		}
		
		return map;
		
	}
}

