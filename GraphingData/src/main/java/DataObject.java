import java.util.ArrayList;

public class DataObject {
	private String dataString;
	
	private static ArrayList<String> dataContents = new ArrayList<String>();
	private ArrayList<String> dataList = new ArrayList<String>();
	private DataObject object2;
	private String data;
	
	public DataObject() {}
	public DataObject(String data) {
		this.data = data;
		setupData(data);
	}
	
	public DataObject(DataObject object) {
		setupData(object.getData());
	}
	
	public String getData() {
		return data;
	}
	
	public void addObject(DataObject object) {
		object2 = object;
	}
	
	public DataObject getObject2() {
		return object2;
	}
	
	public static void setDataContents(ArrayList<String> dataContent){
		dataContents = dataContent;
	}
	
	public static void setupStartData(String data) {
		String[] dataList = data.split(",,");
		for(int scan = 0; scan < dataList.length; scan++) {
			dataContents.add(dataList[scan]);
		}
	}
	
	public ArrayList<String> getDataList(){
		return dataList;
	}
	
	public static ArrayList<String> getDataContents(){
		return dataContents;
	}
	
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
	
	public String toString() {
		String[] dataList = dataString.split(",,");
		String result = "";
		for(int scan = 0; scan < dataList.length; scan++) {
			result += dataList[scan] + ", ";
		}
		
		if(object2 == null) {
			return result.substring(0, result.length()-2);
		}
		else
			return result.substring(0, result.length()-2) + object2;
	}
	
	public int HashCode() {
		return Integer.parseInt(dataList.get(0));
	}
}
