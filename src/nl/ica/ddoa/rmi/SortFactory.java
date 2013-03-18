package nl.ica.ddoa.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SortFactory extends UnicastRemoteObject implements ISortFactory {
	protected SortFactory() throws RemoteException {
		super();
	}

	public ISorter createSorter() throws RemoteException{
		return new BubbleSort();
	}
}
