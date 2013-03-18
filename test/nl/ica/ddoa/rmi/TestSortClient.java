package nl.ica.ddoa.rmi;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.*;

public class TestSortClient {

	SortClient sc;
	
	@Before
	public void setup(){
		sc = new SortClient();
	}
	
	@Test
	public void testIfSortClientIsNotNull(){
		assertNotNull(sc);
	}
	
	@Test
	public void testIfGetResultIsNotNull(){
		ArrayList<ArrayList<Comparable>> listContainer = new ArrayList<ArrayList<Comparable>>();		
		assertNotNull(sc.getResult(listContainer, 0));
	}
	
	@Test
	public void testIfSortHasNoErrors() throws ParseException{		
		sc.sort();
	}
}
