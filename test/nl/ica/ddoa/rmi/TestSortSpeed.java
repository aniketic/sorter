package nl.ica.ddoa.rmi;

import java.rmi.RemoteException;
import java.text.ParseException;

import org.junit.*;

public class TestSortSpeed {
	BubbleSort bs;
	InsertionSort is;
	RandomListGenerator rlg;
	Comparable[] c;

	@Before
	public void setup() throws RemoteException, ParseException {
		bs = new BubbleSort();
		is = new InsertionSort();
		rlg = new RandomListGenerator();
	}
	
	@Test
	public void testRuntime() throws RemoteException, ParseException{
		getResults(10);
		getResults(100);
		getResults(1000);
		getResults(10000);
	}
	
	public void getResults(int numberOfSorts) throws RemoteException, ParseException{
		System.out.println("*** Getting average runtime with " + numberOfSorts +  " runs ***");
		testCompareRuntimeWithIntegers(10, numberOfSorts);
		testCompareRuntimeWithIntegers(100, numberOfSorts);
		testCompareRuntimeWithIntegers(1000, numberOfSorts);
		System.out.println();
	}
	
	public void testCompareRuntimeWithIntegers(int numberOfComparables, int numberOfSorts) throws ParseException, RemoteException{
		c =  rlg.generateRandomList(numberOfComparables, Integer.class);
		System.out.println("The average runtime for the bubbelsorter with " + numberOfComparables + " integers is: " + getRuntimeAverage(bs, c, numberOfSorts) + "ms.");
		System.out.println("The average runtime for the insertionsorter with " + numberOfComparables + " integers is: " + getRuntimeAverage(is, c, numberOfSorts) + "ms.");
	}
	
	public double getRuntimeAverage(ISorter sorter, Comparable[] c, int numberOfRuns) throws RemoteException{
		double average = 0;
		
		for(int i=0; i<numberOfRuns; i++){
			average += getSortTime(sorter, c);
		}
		
		average = average / numberOfRuns;
		
		return average;
	}
	
	public long getSortTime(ISorter sorter, Comparable[] c) throws RemoteException{
		long runTime = System.currentTimeMillis();
		
		sorter.sort(c);
		
		runTime = System.currentTimeMillis() - runTime;
		return runTime;
	}
}
