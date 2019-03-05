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
		this.password = i;
	}
	
	//FIXME id:String     password:int    ? makes no sense!

	public boolean checkPassword(int pass) {
		return password == pass;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + password;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}
	
	/**
	 * Overloaded Method to allow equals based on the ID 
	 * @param obj The ID of the Object
	 * @return true if the ID matches the description
	 */
	public boolean equals(String obj) {
		return id.equals(obj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password != other.password)
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}
	
}
