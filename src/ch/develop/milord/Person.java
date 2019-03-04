package ch.develop.milord;

public class Person {

	protected String id;
	
	protected String firstName;

	protected String lastName;
	
	protected Gender sex;

	private int password;

	public Person(String lastName, String firstName, String id, int i) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		//TODO what is this int for? was the int meant to be the id?
	}
	
	//FIXME id:int     password:String    ? makes no sense!

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}
	
}
