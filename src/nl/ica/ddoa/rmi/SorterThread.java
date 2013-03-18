package nl.ica.ddoa.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;

public class SorterThread extends Observable implements Runnable {
	private static final String HOST = "localhost";
	private static int lastThreadNumber = 0;
	private int threadNumber;
	private ListJoiner joiner;
	private Comparable[] c;
	
	/**
	 * De constructor voor SorterThread krijgt een Comparable array en een ListJoiner object.
	 * De threadNumber wordt de lastThreadNumber en daarna wordt de lastThreadNumber verhoogt met 1.
	 * Het gegeven ListJoiner object wordt toegevoegd als observer.
	 * De start methode wordt geroepen zodat de SorterThread begint met runnen.
	 * @param c Een Comparable array die gesorteerd moet worden.
	 * @param joiner Een ListJoiner object die de SorterThread zal observeren.
	 */
	public SorterThread(Comparable[] c, ListJoiner joiner){
		this.joiner = joiner;
		this.threadNumber = lastThreadNumber++;
		this.c = c;
		addObserver(joiner);
		(new Thread(this)).start();
	}

	/**
	 * Deze methode zoekt eerst de ISortFactory in de registry op.
	 * Daarna wordt een nieuwe sorter gemaakt.
	 * De gegeven Comparable array wordt met deze sorter gesorteerd.
	 * Een nieuwe SplicedList object wordt gemaakt van de ThreadNumber en de gesorteerde Comparable array.
	 * Daarna geeft de thread aan dat hij verandert is en geeft de SplicedList object aan de observer.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ISortFactory myServer = (ISortFactory) Naming.lookup("rmi://"
					+ HOST + "/SortFactory");
			ISorter sorter = myServer.createSorter();
			c = sorter.sort(c);
			SplicedList sp = new SplicedList(threadNumber, c);
			
			setChanged();
			notifyObservers(sp);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
