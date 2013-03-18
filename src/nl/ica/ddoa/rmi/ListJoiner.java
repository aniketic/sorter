package nl.ica.ddoa.rmi;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListJoiner implements Observer {
	private ArrayList<ArrayList<Comparable>> allLists;
	private int numberOfSorters;
	private int threadsDone;
	
	/**
	 * Deze methode return zijn eigen object.
	 * @return Dit object.
	 */
	public ListJoiner getListJoiner(){
		return this;
	}
	
	/**
	 * De constructor voor de ListJoiner; allLists wordt geinitialiseerd en numberOfSorters krijgt een gegeven waarde.
	 * @param numberOfSorters Het aantal sorteerders in de client.
	 */
	public ListJoiner(int numberOfSorters){
		this.numberOfSorters = numberOfSorters;
		allLists = new ArrayList<ArrayList<Comparable>>();
		initiateAllLists();
	}
	
	/**
	 * In deze methode krijgt allList zoveel nieuwe (lege) ArrayList<Comparable> als numberOfSorters.
	 */
	public void initiateAllLists(){		
		for(int i=0; i<numberOfSorters; i++){
			allLists.add(new ArrayList<Comparable>());
		}
	}
	
	/**
	 * Deze methode krijgt een SplicedList en de waarde van die lijst wordt in allLists gestopt.
	 * De index van de lijst is de listNumber van de SplicedList.
	 * Wanneer het aantal threadsDone evenveel is als numberOfSorters, is allLists compleet en wordt deze geprint.
	 * @param sp De SplicedList die geplaatst moet worden.
	 */
	public void join(SplicedList sp){
		Comparable[] list = sp.list();
		int listNumber = sp.getListNumber();

		for(int i=0; i<list.length; i++){
			allLists.get(listNumber).add(list[i]);
		}
		
		threadsDone++;
		
		if(threadsDone == numberOfSorters)
			printJoinedList();
	}
	
	/**
	 * Deze methode print allLists, dus alle waardes.
	 */
	public void printJoinedList(){
		System.out.println(allLists);
	}

	/**
	 * Deze methode krijgt een thread en een object binnen.
	 * Wanneer het gegeven object een SplicedList is, wordt de join functie geroepen met de gegeven SplicedList.
	 */
	@Override
	public void update(Observable thread, Object list) {
		// TODO Auto-generated method stub
		if(list instanceof SplicedList){
			join((SplicedList)list);
		}
	}
}
