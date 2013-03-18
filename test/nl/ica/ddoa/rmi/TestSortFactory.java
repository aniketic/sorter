package nl.ica.ddoa.rmi;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.*;

public class TestSortFactory {
	SortFactory sf;

	@Before
	public void setup() throws RemoteException {
		sf = new SortFactory();
	}

	@Test
	public void testReturnOfCreateSorterIsNotNULL() throws RemoteException {
		assertNotNull(sf.createSorter());
	}

	@Test
	public void testReturnOfCreateSorterIsISorter() throws RemoteException {
		if (!(sf.createSorter() instanceof ISorter))
			fail();
	}
}
