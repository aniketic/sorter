package nl.ica.ddoa.rmi;

import java.util.ArrayList;

public class ListBalancer {
	private int numberOfSorters;
	private ArrayList<ArrayList<Comparable>> listContainer;
	private ListSplicer ls;

	/**
	 * De constructor voor ListBlancer krijgt het aantal sorteerders mee.
	 * 
	 * @param numberOfSorters
	 *            Het aantal sorteerders.
	 */
	public ListBalancer(int numberOfSorters) {
		this.numberOfSorters = numberOfSorters;
	}

	/**
	 * Deze methode wordt geroepen om het balanceren van de gegeven lijsten te
	 * starten. Een twee dimensionale ArrayList listContainer wordt meegegeven
	 * en deze wordt 'opgeslagen' voor verder gebruik. De methode om de groottes
	 * van de lijsten te checken wordt geroepen en daarna wordt de listContainer
	 * gegeven.
	 * 
	 * @param listContainer
	 *            De gegeven listContainer.
	 * @return De listConainer na het balanceren.
	 */
	public ArrayList<ArrayList<Comparable>> startBalancer(
			ArrayList<ArrayList<Comparable>> listContainer) {
		this.listContainer = listContainer;
		checkListSize();
		return listContainer;
	}

	/**
	 * Deze methode roept makeSizeList om een lijst te maken van de groottes van
	 * elke lijst in listContainer. Daarna wordt geteld hoeveel groottes uniek
	 * zijn. Als minsten één grootte uniek is, wordt de methode changeListSize
	 * geroepen.
	 */
	public void checkListSize() {
		// Maak een lijst van de size van alle lijsten...
		ArrayList<Integer> sizeList = makeSizeList();

		ArrayList<Integer> uniqueSizeIndexList = countNumberOfUniqueListSizes(sizeList);

		if (uniqueSizeIndexList.size() > 0) {
			changeListSize(sizeList, uniqueSizeIndexList);
		}
	}

	/**
	 * Deze methode maakt een lijst van de groottes van elke lijst in
	 * listContainer.
	 * 
	 * @return Een lijst van de groottes van elke lijst in listContainer.
	 */
	public ArrayList<Integer> makeSizeList() {
		ArrayList<Integer> sizeList = new ArrayList<Integer>();
		for (int i = 0; i < listContainer.size(); i++) {
			sizeList.add(listContainer.get(i).size());
		}

		return sizeList;
	}

	/**
	 * Deze methode krijgt een lijst met een aantal groottes. Dan wordt er bij
	 * elke grootte gekeken of die waarde uniek is. Als het uniek is, wordt de
	 * index hiervan aan een aparte ArrayList 'uniqueSizeIndexList' toegevoegd.
	 * 
	 * @param sizeList
	 *            Een ArrayList die de groottes bevat van alle lijsten in
	 *            listContainer.
	 * @return Een ArrayList met de index posities van alle unieke groottes
	 */
	public ArrayList<Integer> countNumberOfUniqueListSizes(
			ArrayList<Integer> sizeList) {
		ArrayList<Integer> uniqueSizeIndexList = new ArrayList<Integer>();
		for (int i = 0; i < sizeList.size(); i++) {
			boolean uniqueSize = compareListSizes(i, sizeList);
			if (uniqueSize) {
				uniqueSizeIndexList.add(i);
			}
		}

		return uniqueSizeIndexList;
	}

	/**
	 * Deze methode krijgt de ArrayList sizeList binnen en de index die
	 * vergeleken moet worden. De waarde die op de gegeven index staat wordt met
	 * alle andere waardes vergeleken. Als de waarde hetzelfde is als één of
	 * meerde andere waardes is hij niet uniek.
	 * 
	 * @param index
	 *            De index waar de waarde staat die vergeleken moet worden.
	 * @param sizeList
	 *            De ArrayList waarin vergeleken wordt.
	 * @return true = de waarde is uniek, false = de waarde is niet uniek.
	 */
	public boolean compareListSizes(int index, ArrayList<Integer> sizeList) {
		boolean uniqueSize = true;

		int currentListSize = sizeList.get(index);
		for (int i = 0; i < sizeList.size(); i++) {
			if (i != index) {
				int otherListSize = sizeList.get(i);
				if ((currentListSize - otherListSize) == 0) {
					uniqueSize = false;
					i = sizeList.size();
				}
			}
		}

		return uniqueSize;
	}

	/**
	 * Deze methode kijkt of een lijst te groot of te klein is en daarna wordt
	 * de passende maatregel gehanteerd. Eerst wordt gekeken op welke positie de
	 * kleinste en de grootste lijsten staan. Dan wordt bepaald of de waardes
	 * naar links of naar rechts moeten opschuiven. Als er slechts één lijst is
	 * met een unieke grootte: - Als numberOfSorters meer is dan 3, dan wordt
	 * checkIfMoreThanTwoSizes groepen om te kijken of er niet meer dan 2
	 * soorten groottes zijn. - Als er niet meer dan 2 soorten groottes zijn,
	 * dan wordt het verschil gemeten tussen de unieke lijst en de rest. Als dit
	 * verschil meer dan 1 is, dan moet de lijst kleiner of groter worden met de
	 * methode balanceList. - Als er meer dan 2 soorten groottes zijn, dan wordt
	 * de methode repositionValue geroepen. Als er meerde lijsten zijn met
	 * unieke groottes: De methode repositionValue wordt geroepen.
	 * 
	 * @param sizeList
	 *            Een lijst met alle groottes van de lijsten.
	 * @param uniqueSizeIndexList
	 *            Een lijst met alle groottes die uniek zijn.
	 */
	public void changeListSize(ArrayList<Integer> sizeList,
			ArrayList<Integer> uniqueSizeIndexList) {
		// Welke positie heeft de kleinste size
		int minIndex = getMaxOrMinSizePosition(sizeList, false);
		// Welke positie heeft de grootste size
		int maxIndex = getMaxOrMinSizePosition(sizeList, true);

		// De grootste moet kleiner worden
		// Welke op moet er verandering komen
		boolean moveLeft = ((minIndex - maxIndex) < 0);

		if (uniqueSizeIndexList.size() == 1) {
			boolean moreThanTwoSizes = false;

			if (numberOfSorters > 3) {
				// Controleer of er niet meer dan 2 verschillende sizes zijn...
				moreThanTwoSizes = checkIfMoreThanTwoSizes(sizeList);
			}

			if (!moreThanTwoSizes) {
				int listToChange = uniqueSizeIndexList.get(0);
				// Is het verschil meer dan één?
				int sizeDifference = getSizeDifference(sizeList,
						uniqueSizeIndexList);

				// Is de lijst te klein of te groot
				if (sizeDifference > 1) {
					balanceList(true, listToChange);
				} else if (sizeDifference < -1) {
					balanceList(false, listToChange);
				}
			} else {
				repositionValue(maxIndex, moveLeft);
			}

		} else {
			// verschuif een grensgetal van de grootste lijst richting de
			// kleinste lijst
			repositionValue(maxIndex, moveLeft);
		}
	}

	/**
	 * Deze methode pakt het verschil tussen 2 verschillende groottes. Wanneer
	 * deze methode geroepen wordt heeft uniqueSizeList altijd slechts één
	 * waarde. De waarde van deze grootte wordt vergeleken met een andere
	 * grootte uit de lijst.
	 * 
	 * @param sizeList
	 *            De lijst met alle groottes.
	 * @param uniqueSizeIndexList
	 *            Een lijst met de index van alle unieke groottes.
	 * @return Het verschil tussen de groottes.
	 */
	public int getSizeDifference(ArrayList<Integer> sizeList,
			ArrayList<Integer> uniqueSizeIndexList) {
		int sizeDifference = 0;
		int index = uniqueSizeIndexList.get(0);

		if (index == 0) {
			sizeDifference = sizeList.get(index) - sizeList.get(index + 1);
		} else {
			sizeDifference = sizeList.get(index) - sizeList.get(index - 1);
		}

		return sizeDifference;
	}

	/**
	 * Deze methode controleerd of er niet meer dan 2 soorten groottes zijn in
	 * de gegeven sizeList.
	 * 
	 * @param sizeList
	 *            Een lijst met alle groottes.
	 * @return false = niet meer dan 2 soorten groottes, true = meer dan 2
	 *         soorten groottes.
	 */
	public boolean checkIfMoreThanTwoSizes(ArrayList<Integer> sizeList) {
		boolean moreThanTwoSizes = false;
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		sizes.add(sizeList.get(0));

		for (int i = 1; i < sizeList.size(); i++) {
			boolean isInList = false;
			for (int j = 0; j < sizes.size(); j++) {
				if (j != i) {
					if (sizeList.get(i) == sizes.get(j)) {
						isInList = true;
						j = sizes.size();
					}
				}
			}

			if (!isInList)
				sizes.add(sizeList.get(i));
		}

		if (sizes.size() > 2)
			moreThanTwoSizes = true;

		return moreThanTwoSizes;
	}

	/**
	 * Deze lijst pakt de grootste of kleinste waarde uit een lijst en stopt
	 * deze in een lijst ernaast. Is de gegeven lijst te groot: -Als de index 0
	 * is, stop de hoogst waarde in de volgende lijst. -Als de index niet 0 is,
	 * stop de laagste waarde in de vorige lijst. Is de gegeven lijst te klein:
	 * -Als de index 0 is, stop de laagste waarde van de volgende lijst in de
	 * gegeven lijst. -Als de index niet 0 is, stop de hoogste waarde van de
	 * vorige lijst in de gegeven lijst. Als laatste wordt checkListSize weer
	 * geroepen om te kijken of er opnieuw gebalanceerd moet worden.
	 * 
	 * @param tooBig
	 *            true = de lijst is te groot, false = de lijst is te klein
	 * @param index
	 *            De index van de lijst die moet veranderen.
	 */
	public void balanceList(boolean tooBig, int index) {
		if (tooBig) {
			if (index == 0) {
				int indexMax = getIndexMaxOrMin(listContainer.get(index), true);
				Comparable valueMax = getMaxOrMin(listContainer.get(index),
						true);
				listContainer.get(index).remove(indexMax);
				listContainer.get(index + 1).add(valueMax);
			} else {
				int indexMin = getIndexMaxOrMin(listContainer.get(index), false);
				Comparable valueMin = getMaxOrMin(listContainer.get(index),
						false);
				listContainer.get(index).remove(indexMin);
				listContainer.get(index - 1).add(valueMin);
			}
		} else {
			if (index == 0) {
				int indexMin = getIndexMaxOrMin(listContainer.get(index + 1),
						false);
				Comparable valueMin = getMaxOrMin(listContainer.get(index + 1),
						false);
				listContainer.get(index + 1).remove(indexMin);
				listContainer.get(index).add(valueMin);
			} else {
				int indexMax = getIndexMaxOrMin(listContainer.get(index - 1),
						true);
				Comparable valueMax = getMaxOrMin(listContainer.get(index - 1),
						true);
				listContainer.get(index - 1).remove(indexMax);
				listContainer.get(index).add(valueMax);
			}
		}

		checkListSize();
	}

	/**
	 * Deze methode verplaatst een waarde van een lijst naar een andere lijst.
	 * De index van de te veranderen lijst wordt meegegeven en ook of de waarde
	 * naar links of rechts moet verplaatsen. Als de waarde naar links moet, dan
	 * wordt de kleinste waarde gepakt en in de vorige lijst gestopt. Als de
	 * waarde naar rechts moet, dan wordt de hoogste waarde gepakt en in de
	 * volgende lijst gestopt.
	 * 
	 * @param index
	 *            De positie van de lijst die moet veranderen.
	 * @param moveLeft
	 *            true = de waarde moet naar links, false = de waarde moet naar
	 *            rechts.
	 */
	public void repositionValue(int index, boolean moveLeft) {
		if (moveLeft) {
			int indexMin = getIndexMaxOrMin(listContainer.get(index), false);
			Comparable valueMin = getMaxOrMin(listContainer.get(index), false);
			listContainer.get(index).remove(indexMin);
			listContainer.get(index - 1).add(valueMin);
		} else {
			int indexMax = getIndexMaxOrMin(listContainer.get(index), true);
			Comparable valueMax = getMaxOrMin(listContainer.get(index), true);
			listContainer.get(index).remove(indexMax);
			listContainer.get(index + 1).add(valueMax);
		}

		if (numberOfSorters > 2)
			checkListSize();
	}

	/**
	 * Deze methode geeft de maximale of minale waarde uit een gegeven
	 * ArrayList. De gegeven boolean wantMax geeft aan of de maximale of
	 * minimale waarde gewenst is.
	 * 
	 * @param comparable
	 *            Een ArrayList met Comparable waardes.
	 * @param wantMax
	 *            true = de maximale waarde is gewenst, false = de minimale
	 *            waarde is gewenst.
	 * @return De maximale of minimale waarde uit de gegeven ArrayList.
	 */
	public Comparable getMaxOrMin(ArrayList<Comparable> comparable,
			boolean wantMax) {
		Comparable temp = comparable.get(0);

		for (int i = 1; i < comparable.size(); i++) {
			if ((comparable.get(i).compareTo(temp) > 0 && wantMax)
					|| (comparable.get(i).compareTo(temp) < 0 && !wantMax)) {
				temp = comparable.get(i);
			}
		}

		return temp;
	}

	/**
	 * Deze methode pakt de index van de maximale of minimale waarde van een
	 * gegeven ArrayList met Comparable waardes.
	 * 
	 * @param comparable
	 *            Een ArrayList met Comparable waardes.
	 * @param wantMax
	 *            true = de index van de maximale waarde is gewenst, false = de
	 *            index van de minimale waarde is gewenst
	 * @return De index van de maximale of minimale waarde.
	 */
	public int getIndexMaxOrMin(ArrayList<Comparable> comparable,
			boolean wantMax) {
		Comparable temp = comparable.get(0);
		int index = 0;

		for (int i = 1; i < comparable.size(); i++) {
			if ((comparable.get(i).compareTo(temp) > 0 && wantMax)
					|| (comparable.get(i).compareTo(temp) < 0 && !wantMax)) {
				temp = comparable.get(i);
				index = i;
			}
		}

		return index;
	}

	/**
	 * Deze methode pakt de index van de maximale of minimale waarde van een
	 * gegeven ArrayList met Integer waardes.
	 * 
	 * @param sizeList
	 *            Een ArrayList met Integer waardes.
	 * @param wantMax
	 *            true = de index van de maximale waarde is gewenst, false = de
	 *            index van de minimale waarde is gewenst
	 * @return De index van de maximale of minimale waarde.
	 */
	public int getMaxOrMinSizePosition(ArrayList<Integer> sizeList,
			boolean wantMax) {
		int temp = sizeList.get(0);
		int index = 0;

		for (int i = 1; i < sizeList.size(); i++) {
			if ((sizeList.get(i).compareTo(temp) > 0 && wantMax)
					|| (sizeList.get(i).compareTo(temp) < 0 && !wantMax)) {
				temp = sizeList.get(i);
				index = i;
			}
		}

		return index;
	}

	/**
	 * Deze methode geeft de maximale waarde uit een ArrayList met Integers.
	 * 
	 * @param sizeList
	 *            Een ArrayList met Integers.
	 * @return De maximale waarde uit de lijst.
	 */
	public int getMaxSize(ArrayList<Integer> sizeList) {
		int max = sizeList.get(0);

		for (int i = 0; i < sizeList.size(); i++) {
			if (max < sizeList.get(i))
				max = sizeList.get(i);
		}

		return max;
	}
}