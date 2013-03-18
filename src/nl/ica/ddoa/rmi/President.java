package nl.ica.ddoa.rmi;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class President implements Comparable, Serializable {
	private String lastName;
	private String firstName;
	private Calendar dateOfBirth;

	public President(String lastName, String firstName, Calendar dateOfBirth) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public President() throws ParseException {
		this.lastName = randomName("lastName");
		this.firstName = randomName("firstName");
		this.dateOfBirth = randomDateOfBirth();
	}

	public int compareTo(Object o) {
		if (o instanceof President) {
			President obj = (President) o;

			if (lastName.compareTo(obj.lastName) == 0) {
				if (compareDates(obj.dateOfBirth, "yyyy") == 0) {
					if (compareDates(obj.dateOfBirth, "MM") == 0) {
						return compareDates(obj.dateOfBirth, "dd");
					}
					return compareDates(obj.dateOfBirth, "MM");
				}
				return compareDates(obj.dateOfBirth, "yyyy");
			}
			return lastName.compareTo(obj.lastName);

		} else {
			return -1;
		}
	}

	public int compareDates(Calendar dob, String form) {
		if(testDateForm(form)){
		SimpleDateFormat df = new SimpleDateFormat();

		df.applyPattern(form);

		int currentDate = Integer.parseInt(df.format(dateOfBirth.getTime()));
		int otherDate = Integer.parseInt(df.format(dob.getTime()));
		int difference = ((Integer) currentDate).compareTo((Integer) otherDate);

		return difference;
		} else {
			return 0;
		}
	}
	
	public boolean testDateForm(String form){
		switch (form) {
		case "yyyy":
		case "MM":
		case "dd":
			return true;
		default:
			return false;
		}
	}

	public String randomName(String nameType) {
		Random rand = new Random();
		String name = "";

		switch (nameType) {
		case "lastName":
			name = lastNameList(rand.nextInt(10));
			break;
		case "firstName":
			name = firstNameList(rand.nextInt(10));
		}

		return name;
	}

	public String lastNameList(int index) {
		String lastName = "";

		switch (index) {
		case 1:
			lastName = "Washington";
			break;
		case 2:
			lastName = "Adams";
			break;
		case 3:
			lastName = "Jefferson";
			break;
		case 4:
			lastName = "Madison";
			break;
		case 5:
			lastName = "Monroe";
			break;
		case 6:
			lastName = "Adams";
			break;
		case 7:
			lastName = "Jackson";
			break;
		case 8:
			lastName = "Buren van";
			break;
		case 9:
			lastName = "Harrison";
			break;
		default:
			lastName = "Tyler";
		}
		
		return lastName;
	}

	public String firstNameList(int index) {
		String lastName = "";

		switch (index) {
		case 1:
			lastName = "George";
			break;
		case 2:
			lastName = "John";
			break;
		case 3:
			lastName = "Thomas";
			break;
		case 4:
			lastName = "James";
			break;
		case 5:
			lastName = "James";
			break;
		case 6:
			lastName = "John";
			break;
		case 7:
			lastName = "Andrew";
			break;
		case 8:
			lastName = "Martin";
			break;
		case 9:
			lastName = "William";
			break;
		default:
			lastName = "John";
		}

		return lastName;
	}

	public Calendar randomDateOfBirth() throws ParseException {
		Calendar dateOfBirth = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		Random rand = new Random();
		int year = rand.nextInt(200) + 1789;
		int month = rand.nextInt(12) + 1;
		int day = rand.nextInt(31) + 1;

		Date date = formatter.parse("" + year + "/" + month + "/" + day);
		dateOfBirth.setTime(date);

		return dateOfBirth;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		return lastName + ", " + df.format(dateOfBirth.getTime());
	}
}
