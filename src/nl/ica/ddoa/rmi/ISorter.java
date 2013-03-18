package nl.ica.ddoa.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISorter extends Remote {	
	public Comparable[] sort(Comparable[] comparable) throws RemoteException;
}
