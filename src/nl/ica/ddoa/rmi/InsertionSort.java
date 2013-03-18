package nl.ica.ddoa.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InsertionSort extends UnicastRemoteObject implements ISorter {
	protected InsertionSort() throws RemoteException {
		super();
	}

	public Comparable[] sort(Comparable[] comparable) {
		Comparable temp;

		for (int i = 1; i < comparable.length; i++) {
			while(i > 0 && comparable[i - 1].compareTo(comparable[i]) > 0){
				temp = comparable[i];
				comparable[i] = comparable[i-1];
				comparable[i-1] = temp;
				i--;
			}
		}

		return comparable;
	}
}
