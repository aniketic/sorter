package nl.ica.ddoa.rmi;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.*;

public class TestPresident {
	President washington;
	President randomPresident;

	@Before
	public void setup() throws Exception {
		washington = new President("Washington", "George",
				washingtonBirthDate());
		randomPresident = new President();
	}

	public Calendar washingtonBirthDate() throws ParseException {
		Calendar dateOfBirth = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/2/1732");
		dateOfBirth.setTime(date);

		return dateOfBirth;
	}
	
	//Comparing dates
	@Test
	public void testIfCompareDatesGivesZeroWhenGivenSameDate() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/2/1732");
		dob.setTime(date);
		
		assertEquals(0, washington.compareDates(dob, "yyyy"));
	}
	
	@Test
	public void testIfCompareDatesGivesMinusOneWhenGivenNewerYear() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/2/1733");
		dob.setTime(date);
		
		assertEquals(-1, washington.compareDates(dob, "yyyy"));
	}
	
	@Test
	public void testIfCompareDatesGivesOneWhenGivenOlderYear() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/2/1731");
		dob.setTime(date);
		
		assertEquals(1, washington.compareDates(dob, "yyyy"));
	}
	
	@Test
	public void testIfCompareDatesGivesMinusOneWhenGivenNewerMonth() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/3/1732");
		dob.setTime(date);
		
		assertEquals(-1, washington.compareDates(dob, "MM"));
	}
	
	@Test
	public void testIfCompareDatesGivesOneWhenGivenOlderMonth() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("22/1/1732");
		dob.setTime(date);
		
		assertEquals(1, washington.compareDates(dob, "MM"));
	}
	
	@Test
	public void testIfCompareDatesGivesMinusOneWhenGivenNewerDay() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("23/2/1732");
		dob.setTime(date);
		
		assertEquals(-1, washington.compareDates(dob, "dd"));
	}
	
	@Test
	public void testIfCompareDatesGivesOneWhenGivenOlderDay() throws ParseException{
		Calendar dob = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = formatter.parse("21/2/1732");
		dob.setTime(date);
		
		assertEquals(1, washington.compareDates(dob, "dd"));
	}

	// Washington tests

	@Test
	public void testWashingtonIsNotNULL() throws Exception {
		assertNotNull(washington);
	}

	@Test
	public void testWashingtonLastNameIsSameAsInput() throws Exception {
		assertEquals("Washington", washington.getLastName());
	}

	@Test
	public void testWashingtonFirstNameIsSameAsInput() throws Exception {
		assertEquals("George", washington.getFirstName());
	}

	@Test
	public void testWashingtonDateIsSameAsInput() throws Exception {
		assertEquals(washingtonBirthDate(), washington.getDateOfBirth());
	}

	// Random President tests

	@Test
	public void testRandomPresidentIsNotNull() {
		assertNotNull(randomPresident);
	}

	@Test
	public void testRandomPresidentLastNameIsNotNull() {
		assertNotNull(randomPresident.getLastName());
	}

	@Test
	public void testRandomPresidentFirstNameIsNotNull() {
		assertNotNull(randomPresident.getFirstName());
	}

	@Test
	public void testRandomPresidentDateOfBirthIsNotNull() {
		assertNotNull(randomPresident.getDateOfBirth());
	}
}
