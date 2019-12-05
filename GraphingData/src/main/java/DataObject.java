import java.util.ArrayList;

/*
 * DataObject -- This is a Object that contains data and what that data is
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class DataObject {
	private String dataString;
	
	private static ArrayList<String> dataContents = new ArrayList<String>();
	private ArrayList<String> dataList = new ArrayList<String>();
	private DataObject object2;
	private String data;
	
	/**
	 * Constructor to make an empty DataObject
	 */
	public DataObject() {}
	
	/**
	 * Constructor that takes in a string of data and uses it to fill up this DataObject with data
	 * @param data takes in a string to decompile and use for data
	 */
	public DataObject(String data) {
		this.data = data;
		setupData(data);
	}
	
	/**
	 * Constructor that takes in a an Object
	 * @param object is a DataObject to be used to compare to this DataObject
	 */
	public DataObject(DataObject object) {
		setupData(object.getData());
	}
	
	/**
	 * @return returns the data that was inputed for this object
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * @param object is a DataObject that this object will use for later
	 */
	public void addObject(DataObject object) {
		object2 = object;
	}
	
	/**
	 * @return this returns the data Object that is contained in this Object
	 */
	public DataObject getObject2() {
		return object2;
	}
	
	/**
	 * @param dataContent is an array list of Strings in which is used to set the dataContents of this object
	 */
	public static void setDataContents(ArrayList<String> dataContent){
		dataContents = dataContent;
	}
	
	/**
	 * This sets creates a list of the type of data
	 * @param data takes in a string of data and uses it to create the data type
	 */
	public static void setupStartData(String data) {
		String[] dataList = data.split(",,");
		for(int scan = 0; scan < dataList.length; scan++) {
			dataContents.add(dataList[scan]);
		}
	}
	
	/**
	 * @return the data in this object
	 */
	public ArrayList<String> getDataList(){
		return dataList;
	}
	
	/**
	 * @return the static list of the type of data these objects will be holding
	 */
	public static ArrayList<String> getDataContents(){
		return dataContents;
	}
	
	/**
	 * This method sets up the data for this object to hold
	 * @param stringData this takes in a string and uses it to create a list of data
	 */
	public void setupData(String stringData) {
		this.dataString = stringData;
		String[] tempdataList = dataString.split(",,");
		
		for(int scan = 0; scan < tempdataList.length; scan++) {
			if(tempdataList[scan].equals("")) {
				dataList.add("0");
			}
			else
				dataList.add(tempdataList[scan]);
		}
		for(int scan = dataList.size(); scan < dataContents.size(); scan++) {
			dataList.add("0");
		}
	}
	
	/**
	 * This method returns this objects representation in a string foramt
	 */
	public String toString() {
		String[] dataList = dataString.split(",,");
		String result = "";
		for(int scan = 0; scan < dataList.length; scan++) {
			result += dataList[scan] + ", ";
		}
		
		if(object2 == null) {
			return result.substring(0, result.length()-2);
		}
		else {
			if(result.length() >= 20 && object2.toString().length() >= 20) {
				return result.substring(0, 20) + "... to " + object2.toString().substring(0, 20) + "...";
			}
			else if(result.length() >= 20 && object2.toString().length() <= 20) {
				return result.substring(0, 20) + "... to " + object2.toString() + "...";
			}
			else
				return result + "... to " + object2.toString() + "...";
			
		}
		
		
	}
	
	/**
	 * This method creates a unique hashcode for this DataObject
	 * There is instances though that there will be duplicate hashcodes for different DataObjects but thats ok
	 * because it is better than Object using its defaul hashcode method.
	 * DataObjects that are the same will have the same hashcode.
	 */
	public int hashCode() {
		if(object2 == null) {
			int result = 1;
			for(int scan = 0; scan < dataString.length(); scan++) {
				if(dataString.charAt(scan) != 0) {
					result += dataString.charAt(scan);
				}
			}
			return result;
		}
		else {
			int result = 1;
			for(int scan = 0; scan < dataString.length(); scan++) {
				if(dataString.charAt(scan) != 0) {
					result += dataString.charAt(scan);
				}
			}
			return result + object2.hashCode();
		}
	}
}
