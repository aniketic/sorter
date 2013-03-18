package nl.ica.ddoa.rmi;

import java.text.ParseException;
import java.util.Random;

public class RandomListGenerator {
	public RandomListGenerator(){
	}
	
	public Comparable[] generateRandomIntegers(int length) {
		Random rand = new Random();
		Comparable[] comparable = new Comparable[length];

		for (int i = 0; i < length; i++) {
			comparable[i] = rand.nextInt(200) - 99;
		}

		return comparable;
	}

	public Comparable[] generateRandomStrings(int length) {
		Random rand = new Random();
		Comparable[] comparable = new Comparable[length];

		for (int i = 0; i < length; i++) {
			comparable[i] = new Character((char) rand.nextInt(256)).toString();
		}

		return comparable;
	}
	
	public Comparable[] generateRandomPresidents(int length) throws ParseException {
		Random rand = new Random();
		Comparable[] comparable = new Comparable[length];

		for (int i = 0; i < length; i++) {
			comparable[i] = new President();
		}

		return comparable;
	}

	public Comparable[] generateRandomList(int length, Class type) throws ParseException {
		if (type.equals(Integer.class)) {
			return generateRandomIntegers(length);
		} else if (type.equals(String.class)) {
			return generateRandomStrings(length);
		} else if (type.equals(President.class)) {
			return generateRandomPresidents(length);
		} else {
			System.out
					.println("SortClient: Type given in generateRandomList(int length, Class type) is not supported: '"
							+ type.getName()
							+ "'; returning empty Comparable[].");
			return new Comparable[] {};
		}
	}
}
