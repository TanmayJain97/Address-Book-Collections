package AddressBook;

public class Contacts {

	public String firstName;		//Obj Attributes
	public String lastName;
	public String address;
	public String city;
	public String state;
	public long zipCode;
	public String phoneNo;
	public String email;
	
	public Contacts(String firstName, String lastName, String address,
			String city, String state, long zipCode,
			String phoneNo, String email) {
		this.firstName = firstName;				//Constructor
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public void display() {				//Method for displaying all details
		
		System.out.println("------------------------------------------------------");
		System.out.println("Name: "+firstName+" "+lastName);
		System.out.println("Address: "+address);
		System.out.println("City: "+city);
		System.out.println("State: "+state);
		System.out.println("Zip: "+zipCode);
		System.out.println("Phone No.: "+phoneNo);
		System.out.println("Email: "+email);
		System.out.println("------------------------------------------------------");
		System.out.println();
	}
	
	@Override
	public String toString() {
		System.out.println();
		return "Created entry for "+firstName+" "+lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	
	public String pushDataToFile() {
		return firstName+":"+lastName+":"+address+":"+city+":"+state
				+":"+zipCode+":"+phoneNo+":"+email;
	}
}
