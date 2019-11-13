import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import jsjf.*;
/*
 * HuffmanCompressor.java -- This class compresses text into bit strings to make the file take up less space.
 * Jason Melnik
 * 10/20/2019
 * 
 */
public class HuffmanCompressor{
	private Map<Character, String> dictionary;
	private String fileString;
	private String compressedString;
	ArrayList<HuffElement> sortedHuffArray;
	File file;
	String filesName;
	String fileLocation;
	
	/**
	 * Creates a HuffmanCompressor object that takes in a fileLocation and fileName
	 * @param fileLocation which is a String that will help find the location of the file to compress
	 * @param fileName which is a String that will help find the name of the file to compress
	 */
	public HuffmanCompressor(String fileLocation, String fileName){
		file = new File(fileLocation+"//"+fileName);
		filesName = fileName;
		this.fileLocation = fileLocation;
		fileString = getFileString();
		sortedHuffArray = getHuffArray();
		setupDictionary();
		compressedString = getCompressedString();
	}
	
	/**
	 * This puts the entire text file into one long string
	 * This method uses file
	 * @return String that contains the entire files text
	 */
	private String getFileString() {
		String temp = "";
		try {
			FileInputStream inputStream = new FileInputStream(file);
			
			int num;
			while((num = inputStream.read()) != -1) {
				char tem = (char) num;
				temp += tem;
			}
			
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return temp;
	}
	
	/**
	 * This method uses the String of characters and makes a map with the key being a character and the value being the amount of times that character appeared
	 * @return a Map with a character and integer(the total amount of occurrences of that character)
	 */
	private Map<Character, Integer> getMap() {
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		
		for(int x = 0; x < fileString.length(); x++) {
			if(!map.containsKey(fileString.charAt(x))) {
				map.put(fileString.charAt(x), 1); 
			}
			else {
				map.replace(fileString.charAt(x), map.get(fileString.charAt(x)) + 1);
			}
		}
		
		return map;
	}
	
	/**
	 * This method creates a ordered list of HuffElements based on their values(occurrences)
	 * This is a helper method for getEncodedTree()
	 * @return ArrayList of HuffElements
	 */
	private ArrayList<HuffElement> getHuffArray(){
		Set< Map.Entry<Character,Integer> > st = getMap().entrySet(); 
		
		ArrayList<HuffElement> list = new ArrayList<HuffElement>();
		
		for (Map.Entry<Character,Integer> me:st) 
	    { 
			list.add(new HuffElement(me.getKey() + "", me.getValue()));
	    } 
		
		list.sort(null);
		
		return list;
	}
	
	
	/**
	 * This makes a string of 0s and 1s depending on the dictionary and adding that value to the string
	 * @return String that contains 0s and 1s to represent the file's text
	 */
	private String getCompressedString() {
		String result = "";
		
		//this makes the string of 0s and 1s corresponding to their character value
		for(int scan = 0; scan < fileString.length(); scan++) {
			result += dictionary.get(fileString.charAt(scan));
		}
		return result;
	}
	
	/**
	 * This method saves the compressed String into a new file as well as its bit string.
	 * It prints the dictionary first and then the bits
	 */
	public void saveEncodedFile() {
		try {
			File f = new File(fileLocation + "//Compressed_" + filesName);
			
			PrintStream writer = new PrintStream(new FileOutputStream(f), true);	
			BitOutputStream stream = new BitOutputStream(writer);
			
			String result = fileString.length() + ",";
			//this saves the dictionary in matters of 0's and 1's
			for (Entry<Character, String> entry : dictionary.entrySet()) {
				result = result + (entry.getKey() + ":" + entry.getValue().toString() + ",");
			}
			
			writer.print(result.toString() + "   ");
			
			for(int x = 0; x < compressedString.length(); x++) {
				stream.writeBits(1, Integer.parseUnsignedInt(compressedString.charAt(x) + "", 2));
			}
			
			stream.close();
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method makes the tree of HuffElements
	 * @return LinkedBinaryTree that contains HuffElements. This tree is the root which contains all the HuffElements needed
	 */
	private LinkedBinaryTree<HuffElement> getEncodedTree(){
		ArrayList<LinkedBinaryTree<HuffElement>> list = makeArrayIntoTree(sortedHuffArray);
		
		if(list.size() == 1) {
			return new LinkedBinaryTree<HuffElement>(list.get(0).getRootElement());
		}
		
		return makeTree(list);
	}
	
	/**
	 * This method called makeDictionary() to create the dictionary
	 */
	private void setupDictionary() {
		dictionary = new HashMap<Character, String>();
		makeDictionary(getEncodedTree(), "");
	}
	
	/**
	 * recursive which adds to the dictionary until there are no more leaves left;
	 * @param node is the tree node that will be traversed
	 * @param result is the binary code that this recursive method will use to get the final code of a letter
	 * @return String that contains a result of 0s and 1s depending on which side of the tree it is traversing
	 */
	private String makeDictionary(LinkedBinaryTree<HuffElement> node, String result) {
		if(node != null) {
			if(node.getLeft() == null && node.getRight() == null) {
				if(result.equals("")) {
					dictionary.put(node.getRootElement().getName().charAt(0), "0");
				}
				else
					dictionary.put(node.getRootElement().getName().charAt(0), result);
			}
			
			makeDictionary(node.getLeft(), result + "0");
			makeDictionary(node.getRight(), result + "1");
		}
		
		return result;
		
	}
	
	/**
	 * So this makes each HuffElement become a node with two empty children
	 * @param array which is an ArrayList of HuffElements which we need to make an arrayList of LinkedBinaryTrees
	 * @return ArrayList<LinkedBinaryTree<HuffElement>> which is a sorted ArrayList on the value of the HuffElements
	 */
	private ArrayList<LinkedBinaryTree<HuffElement>> makeArrayIntoTree(ArrayList<HuffElement> array){
		ArrayList<HuffElement> temp = array;
		ArrayList<LinkedBinaryTree<HuffElement>> treeList = new ArrayList<LinkedBinaryTree<HuffElement>>();
		
		for(int x = 0; x < temp.size(); x++) {
			LinkedBinaryTree<HuffElement> tree = new LinkedBinaryTree<HuffElement>(temp.get(x));
			treeList.add(tree);
		}
		
		return treeList;
		
	}
	
	/**
	 * This creates the tree of HuffElements using recursive calls.
	 * @param array is an ArrayList of linkedBinaryTree that contain HuffElements
	 * @return the root node with all the huffElements in the right places
	 */
	private LinkedBinaryTree<HuffElement> makeTree(ArrayList<LinkedBinaryTree<HuffElement>> array) {
		ArrayList<LinkedBinaryTree<HuffElement>> list = array;
		
		HuffElement combined = new HuffElement(list.get(0), list.get(1));
		LinkedBinaryTree<HuffElement> tree = new LinkedBinaryTree<HuffElement>(combined, list.get(0), list.get(1));
		list.remove(0);
		list.remove(0);
		
		ArrayList<LinkedBinaryTree<HuffElement>> temp = new ArrayList<LinkedBinaryTree<HuffElement>>();
		boolean found = false;
		for(int scan = 0; scan < list.size(); scan++) {
			if(list.get(scan).getRootElement().getValue() > combined.getValue() && found == false) {
				temp.add(tree);
				temp.add(list.get(scan));
				found = true;
			}
			else{
				temp.add(list.get(scan));
			}
		}
		
		if(found == false) {
			temp.add(tree);
		}

		list = temp;
		
		if(list.size() == 1) {
			return list.get(0);
		}
		else
			return makeTree(list);
	}
	
	
	
	
}
