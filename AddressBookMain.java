package AddressBook;

import java.util.*;
import java.util.stream.Stream;

public class AddressBookMain {

	static Scanner sc = new Scanner(System.in);
	
	private HashMap<String, ArrayList<Contacts>> addressBook;
	private static ArrayList<Contacts> record;
	private static HashMap<String, Contacts> person_cityMap;
	private static HashMap<String, Contacts> person_stateMap;

	public AddressBookMain() {
		record=new ArrayList<Contacts>();
		addressBook = new HashMap<String, ArrayList<Contacts>>();
		person_cityMap=new HashMap<String, Contacts>();
		person_stateMap=new HashMap<String, Contacts>();
	}

	public void display() {
		for (Contacts obj:record) {
			obj.display();
		}
	}
	
	public void display(Contacts obj) {
		obj.display();
	}

	public void addToRecord(Contacts obj,String bookname) {
		try {
			record.add(new Contacts(obj.firstName, obj.lastName, obj.address,
					obj.city,obj.state, obj.zipCode, obj.phoneNo, obj.email));
			addBookMap(bookname, record);
		}catch(NullPointerException e) {}
	}

	public void addBookMap(String bookName, ArrayList<Contacts> obj) {
		addressBook.put(bookName, obj);
	}

	public static Contacts add() {

		//method for adding new entries.
		String firstName;					//Attributes to be added
		String lastName;
		String address;
		String city;
		String state;
		long zipCode;
		String phoneNo;
		String email;

		//asking user input
		System.out.println("Please enter details to be added.");
		System.out.print("First Name: ");
		firstName=sc.next();
		System.out.print("Last Name: ");
		lastName=sc.next();

		//Checking for duplicates
		if (record.stream().anyMatch(obj -> obj.firstName.equals(firstName))
				|| record.stream().anyMatch(obj -> obj.lastName.equals(lastName))) {
			System.out.println("This contact already exists. Resetting");
			add();
			return null;
		}

		System.out.print("Address: ");
		address=sc.next();
		System.out.print("City: ");
		city=sc.next();
		System.out.print("State: ");
		state=sc.next();
		System.out.print("ZipCode: ");
		zipCode=sc.nextLong();
		System.out.print("Phone No.: ");
		phoneNo=sc.next();
		System.out.print("Email: ");
		email=sc.next();

		//saving as new entry
		Contacts entry=new Contacts(firstName,lastName,
				address,city,state,zipCode,phoneNo,email);
		person_cityMap.put(city,entry);
		person_cityMap.put(state,entry);
		return entry;					//returning entry to main
	}

	public static ArrayList<Contacts> edit(ArrayList<Contacts> list, String name) {

		//method for edit
		boolean flag=false;
		name.replaceAll("\\P{Print}","");
		String lower_name=name.toLowerCase();
		for (Contacts obj:list) {
			String firstName=obj.firstName.toLowerCase();
			String lastName=obj.lastName.toLowerCase();
			if (firstName.equals(lower_name) ||
					lastName.equals(lower_name)) {
				flag=true;
				System.out.println("Please enter new details.");
				System.out.print("Address: ");
				obj.address=sc.next();
				System.out.print("City: ");
				obj.city=sc.next();
				System.out.print("State: ");
				obj.state=sc.next();
				System.out.print("ZipCode: ");
				obj.zipCode=sc.nextLong();
				System.out.print("Phone No.: ");
				obj.phoneNo=sc.next();
				System.out.print("Email: ");
				obj.email=sc.next();

				System.out.println("Record updated.");
				break;
			}
		}
		if (flag==false) {
			System.out.println("No entry found for "+name);
		}
		return list;
	}

	public static ArrayList<Contacts> delete(ArrayList<Contacts> list, String name) {

		//method for delete
		boolean flag=false;
		name.replaceAll("\\P{Print}","");
		String lower_name=name.toLowerCase();

		try {
			for (Contacts obj:list) {
				String firstName=obj.firstName.toLowerCase();
				String lastName=obj.lastName.toLowerCase();
				if (firstName.equals(lower_name) ||
						lastName.equals(lower_name)) {
					flag=true;

					System.out.println("Record deleted for "+obj.firstName+" "+obj.lastName);
					list.remove(obj);			//delete entry from record
					System.out.println("Record updated.");
				}
			}
		}catch(Exception e) {}
		if (flag==false) {
			System.out.println("No entry found for "+name);
		}
		return list;
	}

	public void searchContactAll(String contactFirstName,
			String contactLastName, String LocationName) {

		record.stream().filter(obj -> (
				((obj.city.equals(LocationName)) || (obj.state.equals(LocationName)))	//checking for city/state match
				&&(obj.firstName.equals(contactFirstName))								//checking for first name match
				&&(obj.lastName.equals(contactLastName))								//checking for last name match
				))

		.forEach(
				this::display
		);
	}

	public void viewByCityorState(String location, Stream<Contacts> stream) {
		stream.filter(obj ->
				
				((obj.city).equals(location) ||
						(obj.state).equals(location))
		
				).forEach(
						
						this::display
						
						);
	}

	public Stream<Contacts> createStreamfromMap(HashMap<String, Contacts> map) {
		LinkedList<Contacts> contactlist = new LinkedList<Contacts>();
		for(Map.Entry mapElement : map.entrySet()) {
			contactlist.add((Contacts)mapElement.getValue());
		}
		Stream<Contacts> stream=contactlist.stream();
		return stream;
	}
	
	public void sortedStreamDisplay() {
		Stream<Contacts> sorted_stream = record.stream();
		sorted_stream.sorted(

				Comparator.comparing(Contacts::getFirstName)
		
				).forEach(this::display);
	}
	
	public static void main(String[] args) {

		AddressBookMain buildObj=new AddressBookMain();

		//Creating first entry
		Contacts entry1=new Contacts("Tanmay", "Jain",
				"Mahaveer Nagar", "Jaipur", "Raj", 302011, "9765485884",
				"mail.tanmay@gmail.com");
		buildObj.addToRecord(entry1,"AddressBook1");				//Adding entry to record
		System.out.println(entry1);
		person_cityMap.put("New Delhi",entry1);
		person_stateMap.put("Delhi",entry1);

		//Creating second entry
		Contacts entry2=new Contacts("Narendra", "Modi",
				"PMO", "New Delhi", "Delhi", 114102, "9765422564",
				"pmo@office.com");
		buildObj.addToRecord(entry2,"AddressBook1");				//Adding entry to record
		System.out.println(entry2);
		person_cityMap.put("Jaipur",entry2);
		person_stateMap.put("Raj",entry2);

		//initiating user functions of entries

		String user_input="1";
		while((user_input.equals("1") || user_input.equals("2") || user_input.equals("3") || user_input.equals("4") || user_input.equals("5") || user_input.equals("6") || user_input.equals("7") || user_input.equals("8"))) {

			// Checking in address list is present in hashmap
			System.out.print("Enter the Name of the Address Book: ");
			String bookName = sc.next();
			if (buildObj.addressBook.containsKey(bookName)) {
				buildObj.record = buildObj.addressBook.get(bookName);
			}

			else {
				System.out.println("Address book with name " + bookName + " not found. Creating a new entry");
				buildObj.addBookMap(bookName,new ArrayList<Contacts>());
			}

			System.out.println("Record "+bookName+" loaded.");

			System.out.println();				//Waiting for user input
			System.out.println("What now?");
			System.out.println("1. Add a new contact.");
			System.out.println("2. Edit an existing contact.");
			System.out.println("3. Delete an existing contact.");
			System.out.println("4. Search all.");
			System.out.println("5. View by city/state");
			System.out.println("6. Count contacts in City");
			System.out.println("7. Count contacts in State");
			System.out.println("8. View alphabetical list of Contacts");
			System.out.println("9. Switch Directory");
			System.out.println("Logout");
			user_input=sc.next();

			switch(user_input) {

			case "1": {
				Contacts entry=buildObj.add();		//calling function to make new entry
				buildObj.addToRecord(entry,bookName);			//Adding entry to record
				System.out.println(entry);
				break;
			}
			case "2": {
				System.out.println("Please enter First/Last name of entry to be edited.");
				String name=sc.next();
				ArrayList<Contacts> list = buildObj.edit(record,name);
				break;
			}
			case "3": {
				System.out.println("Please enter First/Last name of entry to be deleted.");
				String name=sc.next();
				record=delete(record,name);
				break;
			}
			case "4": {
				System.out.println("Please enter details to be searched.");
				System.out.print("First Name: ");
				String firstName=sc.next();
				System.out.print("Last Name: ");
				String lastName=sc.next();
				System.out.print("City/State: ");
				String location=sc.next();
				buildObj.searchContactAll(firstName, lastName, location);
				break;
			}
			case "5": {
				System.out.print("City/State Name: ");
				String location=sc.next();
				Stream<Contacts> stream_city=buildObj.createStreamfromMap(person_cityMap);
				Stream<Contacts> stream_state=buildObj.createStreamfromMap(person_stateMap);
				buildObj.viewByCityorState(location, stream_city);
				buildObj.viewByCityorState(location, stream_state);
				break;
			}
			case "6": {
				int count=0;
				System.out.print("City Name: ");
				String location=sc.next();
				Stream<Contacts> stream_city=buildObj.createStreamfromMap(person_cityMap);
				count=count+
						(int)stream_city.filter(obj -> (obj.city).equals(location)).count();
				System.out.println("The no. of people in "+location+" are "+count);
				break;
			}
			case "7": {
				int count=0;
				System.out.print("State Name: ");
				String location=sc.next();
				Stream<Contacts> stream_state=buildObj.createStreamfromMap(person_stateMap);
				count=count+
						(int)stream_state.filter(obj -> (obj.state).equals(location)).count();
				System.out.println("The no. of people in "+location+" are "+count);
				break;
			}
			case "8": {
				buildObj.sortedStreamDisplay();
				break;
			}
			case "9": {
				user_input="1";
				continue;
			}
			default:
				break;
			}
		}

		//displaying all entries
		buildObj.display();
	}
}
