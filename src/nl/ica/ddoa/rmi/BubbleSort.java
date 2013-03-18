package nl.ica.ddoa.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BubbleSort extends UnicastRemoteObject implements ISorter{
	protected BubbleSort() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comparable[] sort(Comparable[] comparable) {
		Comparable temp;

		for (int i = 0; i < comparable.length; i++) {
			for (int j = 1; j < comparable.length - i; j++) {
				if (comparable[j - 1].compareTo(comparable[j]) > 0) {
					temp = comparable[j];
					comparable[j] = comparable[j-1];
					comparable[j-1] = temp;
				}
			}

		}

		return comparable;
	}
}
