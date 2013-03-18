package nl.ica.ddoa.rmi;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestSorter {
	ISorter sorter;
	
	public TestSorter(ISorter sorter){
		this.sorter = sorter;
	}
	
	@Test
	public void sortInputIsZero() throws Exception {
		Comparable[] comparable = {0};
		assertArrayEquals(new Comparable[]{0} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsOne() throws Exception {
		Comparable[] comparable = {1};
		assertArrayEquals(new Comparable[]{1} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsTwoZeros() throws Exception {
		Comparable[] comparable = {0,0};
		assertArrayEquals(new Comparable[]{0, 0} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsZeroOne() throws Exception {
		Comparable[] comparable = {0,1};
		assertArrayEquals(new Comparable[]{0, 1} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsOneZero() throws Exception {
		Comparable[] comparable = {1,0};
		assertArrayEquals(new Comparable[]{0, 1} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputHasNegativeNumber() throws Exception {
		Comparable[] comparable = {1, 0, -9};
		assertArrayEquals(new Comparable[]{-9, 0, 1} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputSamePositiveNumbers() throws Exception {
		Comparable[] comparable = {1, 0, 11, 11};
		assertArrayEquals(new Comparable[]{0, 1, 11, 11} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputSameNegativeNumbers() throws Exception {
		Comparable[] comparable = {1, 0, -11, -11};
		assertArrayEquals(new Comparable[]{-11, -11, 0, 1} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsOneCharacter() throws Exception {
		Comparable[] comparable = {'a'};
		assertArrayEquals(new Comparable[]{'a'} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsTwoCharacters() throws Exception {
		Comparable[] comparable = { 'b', 'a'};
		assertArrayEquals(new Comparable[]{'a', 'b'} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsOneString() throws Exception {
		Comparable[] comparable = {"woord"};
		assertArrayEquals(new Comparable[]{"woord"} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsTwoStrings() throws Exception {
		Comparable[] comparable = {"woord2", "woord1"};
		assertArrayEquals(new Comparable[]{"woord1", "woord2"} , sorter.sort(comparable));
	}
	
	@Test
	public void sortInputIsOnePresident() throws Exception {
		President p = new President();
		Comparable[] comparable = {p};
		assertArrayEquals(new Comparable[]{p} , sorter.sort(comparable));
	}
}
