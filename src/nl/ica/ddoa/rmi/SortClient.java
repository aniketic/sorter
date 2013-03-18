package nl.ica.ddoa.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class SortClient {
	private static final int numberOfSorters = 3;

	/**
	 * De main methode van het programma; Er wordt een nieuw SortClient object
	 * gemaakt en de functie sort wordt geroepen.
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		SortClient sc = new SortClient();
		sc.sort();
	}

	/**
	 * Deze methode maakt een willekeurige lijst van een bepaald type en zorgt
	 * ervoor dat deze gedeeld wordt in een aantal lijsten zoveel
	 * numberOfSorters is. Deze lijsten worden met behulp van een ListJoiner
	 * object multi-threaded gesorteerd en aan elkaar geplakt in de goede
	 * volgorde.
	 * 
	 * @throws ParseException
	 *             Deze exception komt als het maken van de randomList niet goed
	 *             gaat.
	 */
	public void sort() throws ParseException {
		RandomListGenerator rndLG = new RandomListGenerator();
		Comparable[] randomList = rndLG.generateRandomList(100, Integer.class);

		ListSplicer splicer = new ListSplicer(randomList, numberOfSorters);
		ArrayList<ArrayList<Comparable>> listContainer = splicer
				.getListContainer();
		int numberOfLists = splicer.getNumberOfLists();

		ListJoiner lj = new ListJoiner(numberOfLists);
		for (int i = 0; i < listContainer.size(); i++) {
			Comparable[] result = getResult(listContainer, i);
			SorterThread sorterThread = new SorterThread(result, lj);
		}
	}

	/**
	 * Deze methode neemt een lijst uit de listContainer met de listNumber als
	 * index. Die lijst wordt verandert in een Comparable array.
	 * 
	 * @param listContainer
	 *            Een ArrayList die de verschillende lijsten bevat.
	 * @param listNumber
	 *            De index van de lijst die in een Comparable array moet
	 *            veranderen.
	 * @return Een Comparable array met alle waardes van de gegeven lijst.
	 */
	public Comparable[] getResult(
			ArrayList<ArrayList<Comparable>> listContainer, int listNumber) {
		Comparable[] result = new Comparable[] {};
		
		if (listContainer.size() > 0) {
			result = new Comparable[listContainer.get(listNumber).size()];

			for (int i = 0; i < listContainer.get(listNumber).size(); i++) {
				result[i] = listContainer.get(listNumber).get(i);
			}
		} else {

		}

		return result;
	}
}