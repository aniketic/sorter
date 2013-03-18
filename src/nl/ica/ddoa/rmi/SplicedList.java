package nl.ica.ddoa.rmi;

public class SplicedList {
	private int listNumber;
	private Comparable[] list;
	
	SplicedList(int listNumber, Comparable[] list){
		this.listNumber = listNumber;
		this.list = list;
	}
	
	public int getListNumber(){
		return listNumber;
	}
	
	public Comparable[] list(){
		return list;
	}
}
