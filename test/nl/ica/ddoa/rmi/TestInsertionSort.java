package nl.ica.ddoa.rmi;

import static org.junit.Assert.assertArrayEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class TestInsertionSort extends TestSorter {

	public TestInsertionSort() throws RemoteException {
		super(new InsertionSort());
		// TODO Auto-generated constructor stub
	}
}
