package nl.ica.ddoa.rmi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class TestListSplicer {
	ListSplicer sp;
	int numberOfSorters;
	Comparable[] c;
	ArrayList<ArrayList<Comparable>> listContainer;
	
	@Before
	public void setup(){
		numberOfSorters = 3;
		c = new Comparable[] {4, 5, 6, 3, 1, -7};
		sp = new ListSplicer(c, numberOfSorters);
	}
	
	@Test
	public void testIfNumberOfListIsSameAsNumberOfSorters(){
		int numberOfLists = 0;
		listContainer = sp.getListContainer();
		
		for(int i=0; i<listContainer.size(); i++){
			numberOfLists++;
		}
		
		assertEquals(numberOfSorters, numberOfLists);
	}
	
	@Test
	public void testIfNumberOfListIsSameAsNumberOfSortersWhenFive(){
		numberOfSorters = 5;
		sp = new ListSplicer(c, numberOfSorters);
		int numberOfLists = 0;
		listContainer = sp.getListContainer();
		
		for(int i=0; i<listContainer.size(); i++){
			numberOfLists++;
		}
		
		assertEquals(numberOfSorters, numberOfLists);
	}
	
	@Test
	public void testIfNumberOfListIsSameAsNumberOfSortersWhenHundred(){
		numberOfSorters = 5;
		sp = new ListSplicer(c, numberOfSorters);
		int numberOfLists = 0;
		listContainer = sp.getListContainer();
		
		for(int i=0; i<listContainer.size(); i++){
			numberOfLists++;
		}
		
		assertEquals(numberOfSorters, numberOfLists);
	}
	
	@Test
	public void testIfSplicerCanHandleZeroSorters(){
		numberOfSorters = 0;
		sp = new ListSplicer(c, numberOfSorters);
	}
	
	@Test
	public void testIfSplicerCanHandleNegativeNumberOfSorters(){
		numberOfSorters = -3;
		sp = new ListSplicer(c, numberOfSorters);
	}
}
