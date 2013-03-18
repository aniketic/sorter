package nl.ica.ddoa.rmi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBubbleSort.class , TestInsertionSort.class, TestPresident.class, TestSortFactory.class, TestSortClient.class, TestListSplicer.class })
public class AllTests {

}