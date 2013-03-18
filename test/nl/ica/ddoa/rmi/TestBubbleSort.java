package nl.ica.ddoa.rmi;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.*;

public class TestBubbleSort extends TestSorter {	
	public TestBubbleSort() throws RemoteException {
		super(new BubbleSort());
		// TODO Auto-generated constructor stub
	}
}
