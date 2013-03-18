package nl.ica.ddoa.rmi;

import java.util.ArrayList;

public class ListSplicer {
	private Comparable[] comparable;
	private int numberOfLists;
	private ArrayList<ArrayList<Comparable>> listContainer;
	private ListBalancer lb;

	/**
	 * De constructor voor de ListSplicer krijgt een ongesorteerde lijst binnen
	 * en het aantal sorteerders dat er zijn. Er wordt een twee dimensionale
	 * ArrayList gemaakt (listContainer) om de verschillende lijsten te
	 * bevatten. De gegeven lijst wordt omgezet naar een ArrayList.
	 * listContainer krijgt evenveel lijsten als numberOfSorters. Deze lijsten
	 * worden gesorteerd in listContainer. Een nieuwe ListBalancer object wordt
	 * gemaakt. Als laatste word comparablePlacer geroepen om alle Comparables
	 * ui de gegeven lijst te plaatsen.
	 * 
	 * @param comparable
	 *            Een (ongesorteerde) Comparable array.
	 * @param numberOfSorters
	 *            Het aantal sorteerders.
	 */
	public ListSplicer(Comparable[] comparable, int numberOfSorters) {
		listContainer = new ArrayList<ArrayList<Comparable>>();
		this.comparable = comparable;
		this.numberOfLists = numberOfSorters;
		ArrayList<Comparable> comparableCopy = createComparableCopy(comparable);

		if (numberOfLists > comparable.length)
			numberOfLists = comparable.length;

		fillListContainer(comparableCopy);

		if (numberOfLists > 0) {
			sortListContainer();
			lb = new ListBalancer(numberOfLists);
			comparablePlacer(comparableCopy);
		}else{
			System.out.println("Number of sorters given is zero or less.");
		}

	}

	/**
	 * Deze methode maakt een ArrayList van een Comparable array.
	 * 
	 * @param comparable
	 *            De Comparable array die in een ArrayList moet veranderen.
	 * @return De gemaakte ArrayList.
	 */
	public ArrayList<Comparable> createComparableCopy(Comparable[] comparable) {
		ArrayList<Comparable> comparableCopy = new ArrayList<Comparable>();

		for (int i = 0; i < comparable.length; i++) {
			comparableCopy.add(comparable[i]);
		}

		return comparableCopy;
	}

	/**
	 * Deze methode krijgt een ArrayList mee die comparable heet. ListContainer
	 * krijgt evenveel ArrayLists als numberOfSorters. Deze ArrayLists krijgen
	 * ieder de eerste waarde van de gegeven ArrayList 'comparable' en daarna
	 * wordt die waarde verwijdert uit comparable.
	 * 
	 * @param comparable
	 *            Een ArrayList van ongesorteerde Comparables.
	 */
	public void fillListContainer(ArrayList<Comparable> comparable) {
		int numberOfRemoved = 0;

		for (int i = 0; i < numberOfLists; i++) {
			listContainer.add(new ArrayList<Comparable>());
			listContainer.get(i).add(comparable.get(i - numberOfRemoved));
			comparable.remove(i - numberOfRemoved);
			numberOfRemoved++;
		}

	}

	/**
	 * Deze methode maakt een nieuwe twee dimensionale ArrayList
	 * 'newListContainer'. Elke lijst in listContainer moet één waarde bevatten
	 * op dit moment. De lijst met de kleinste waarde wordt aan newListContainer
	 * toegevoed en dan wordt deze verwijdert in listContainer. Dit gaat door
	 * totdat listContainer niks meer bevat. Daarna wordt listContainer
	 * newListContainer en zijn de lijsten gesorteerd.
	 */
	public void sortListContainer() {
		ArrayList<ArrayList<Comparable>> newListContainer = new ArrayList<ArrayList<Comparable>>();

		while (listContainer.size() > 0) {
			Comparable min = listContainer.get(0).get(0);
			int indexNumber = 0;

			for (int i = 0; i < listContainer.size(); i++) {
				if (listContainer.get(i).size() > 0) {
					if (min.compareTo(listContainer.get(i).get(0)) > 0) {
						min = listContainer.get(i).get(0);
						indexNumber = i;
					}
				}
			}
			newListContainer.add(listContainer.get(indexNumber));
			listContainer.remove(indexNumber);
		}

		listContainer = newListContainer;
	}

	/**
	 * Deze methode plaatst alle waardes van de gegeven ArrayList 'comparable'
	 * in de juiste lijst. De eerste waarde van comparable gaat naar elke lijst
	 * in listContainer kijken of hij kleiner is dan de hoogste waarde in die
	 * lijst. Zo ja, dan wordt die waarde in die lijst geplaatst. Als de waarde
	 * groter blijkt te zijn dan alle andere waardes, dan wordt deze in de
	 * laatste lijst geplaatst. Omdat de lijsten gesorteerd zijn begint het
	 * vergelijken vanaf de eerste index. Na het plaatsten van de nieuwe waarde
	 * in een lijst wordt gekeken of de groottes van de lijsten gebalanceerd
	 * moeten worden. Als laatste wordt de waarde die geplaatst is verwijdert
	 * uit comparable. Dit proces gaat door totdat comparable geen waardes meer
	 * bevat.
	 * 
	 * @param comparable
	 *            Een ArrayList met ongesorteerde Comparables.
	 */
	public void comparablePlacer(ArrayList<Comparable> comparable) {
		while (comparable.size() > 0) {
			Comparable newComparable = comparable.get(0);
			int targetList = -1;

			// Kijk welke lijst een hogere max heeft dan de nieuwe comparable
			for (int i = 0; i < listContainer.size(); i++) {
				if (newComparable.compareTo(lb.getMaxOrMin(
						listContainer.get(i), true)) <= 0) {
					targetList = i;
					i = listContainer.size();
				}
			}

			// Controleer dat de nieuwe comparable niet de nieuwe hoogste waarde
			// heeft
			if (targetList < 0) {
				targetList = listContainer.size() - 1;
				listContainer.get(targetList).add(newComparable);
			} else {
				listContainer.get(targetList).add(newComparable);
			}

			// Zorg ervoor dat de lijst groottes niet ongebalanceerd worden
			// checkListSize();
			if (numberOfLists > 1)
				listContainer = lb.startBalancer(listContainer);

			comparable.remove(0);
		}

	}

	/**
	 * Deze methode geeft de twee dimensionale ArrayList listContainer.
	 * 
	 * @return listContainer, een twee dimensionale ArrayList.
	 */
	public ArrayList<ArrayList<Comparable>> getListContainer() {
		return listContainer;
	}

	public int getNumberOfLists() {
		return numberOfLists;
	}
}